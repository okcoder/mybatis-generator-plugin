package org.okcoder.mybatis.generator.plugin;

import java.util.List;
import java.util.Objects;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.internal.util.JavaBeansUtil;
import org.mybatis.generator.runtime.dynamic.sql.elements.AbstractMethodGenerator;
import org.mybatis.generator.runtime.dynamic.sql.elements.Utils;

public class UpdateByPrimaryKeyOptimisticLockingPlugin extends PluginAdapter {

	@Override
	public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		updateByPrimaryKeyVersion(method, interfaze, introspectedTable);

		return super.clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(method, interfaze, introspectedTable);
	}

	private void updateByPrimaryKeyVersion(Method ori, Interface interfaze, IntrospectedTable introspectedTable) {

		if (!Utils.generateUpdateByPrimaryKey(introspectedTable)) {
			return;
		}

		String versionColumnName = this.properties.getOrDefault("columnName", "VERSION").toString();
		String currentVersionMethod = this.properties.getOrDefault("currentVersionMethod", "default").toString();

		IntrospectedColumn versionColumn = introspectedTable.getBaseColumns().stream()
				.filter(c -> c.getActualColumnName().equalsIgnoreCase(versionColumnName))//
				.findFirst()//
				.orElse(null);
		if (versionColumn == null) {
			return;
		}

		Method newMethod = new Method(ori.getName().concat("Version")); //$NON-NLS-1$
		context.getCommentGenerator().addGeneralMethodAnnotation(newMethod, introspectedTable,
				interfaze.getImportedTypes());
		newMethod.setDefault(true);
		ori.getParameters().forEach(newMethod::addParameter);
		interfaze.addImportedType(
				new FullyQualifiedJavaType("org.springframework.dao.OptimisticLockingFailureException"));
		newMethod.setReturnType(ori.getReturnType().get());

		newMethod.addBodyLine("int count = update(c ->");

		String recordParamName= ori.getParameters().get(0).getName();

		String prefix = "    c.set(";
		for (IntrospectedColumn column : introspectedTable.getNonPrimaryKeyColumns()) {
			String fieldName = AbstractMethodGenerator.calculateFieldName("", column);
			String methodName = JavaBeansUtil.getGetterMethodName(column.getJavaProperty(),
					column.getFullyQualifiedJavaType());
			newMethod.addBodyLine(prefix + fieldName + ").equalTo("+recordParamName+"::" + methodName + ")");
			prefix = "    .set(";
		}

		prefix = "    .where(";
		for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
			String fieldName = AbstractMethodGenerator.calculateFieldName("", column);
			String methodName = JavaBeansUtil.getGetterMethodName(column.getJavaProperty(),
					column.getFullyQualifiedJavaType());
			newMethod.addBodyLine(prefix + fieldName + ", isEqualTo("+recordParamName+"::" + methodName + "))");
			prefix = "    .and(";
		}
		{
			String fieldName = AbstractMethodGenerator.calculateFieldName("", versionColumn);
			String methodName = JavaBeansUtil.getGetterMethodName(versionColumn.getJavaProperty(),
					versionColumn.getFullyQualifiedJavaType());
			if (currentVersionMethod.equalsIgnoreCase("default")) {
				// record.getVersion()+1
				methodName = recordParamName+"."+methodName+"() - 1";
			} else {
				// record.getcurrentVersion
				methodName = recordParamName+"::"+currentVersionMethod;
			}
			newMethod.addBodyLine(prefix + fieldName + ", isEqualTo(" + methodName + "))");
		}
		newMethod.addBodyLine(");");
		newMethod.addBodyLine("if (count == 0){");
		newMethod.addBodyLine("throw new OptimisticLockingFailureException(\"\");");
		newMethod.addBodyLine("}");
		newMethod.addBodyLine("return count;");
		interfaze.addMethod(newMethod);

	}

	public boolean validate(List<String> warnings) {
		String targetRuntime = this.context.getTargetRuntime();
		return targetRuntime == null || Objects.equals(targetRuntime, "MyBatis3DynamicSql");
	}

}
