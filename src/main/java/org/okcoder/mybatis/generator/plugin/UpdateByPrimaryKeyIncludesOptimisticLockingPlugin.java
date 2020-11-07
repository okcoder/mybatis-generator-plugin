package org.okcoder.mybatis.generator.plugin;

import java.util.List;
import java.util.Objects;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.internal.util.JavaBeansUtil;
import org.mybatis.generator.runtime.dynamic.sql.elements.AbstractMethodGenerator;
import org.mybatis.generator.runtime.dynamic.sql.elements.v2.Utils;

public class UpdateByPrimaryKeyIncludesOptimisticLockingPlugin extends PluginAdapter {

	@Override
	public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		updateByPrimaryKeyVersionIncludes(method, interfaze, introspectedTable);
		return super.clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(method, interfaze, introspectedTable);
	}

	private void updateByPrimaryKeyVersionIncludes(Method ori, Interface interfaze, IntrospectedTable introspectedTable) {

		if (!Utils.generateUpdateByPrimaryKey(introspectedTable)) {
			return;
		}

		String versionColumnName = this.getProperties().getOrDefault("columnName", "VERSION").toString();
		String newVersionMethod = this.getProperties().getOrDefault("newVersionMethod", "default").toString();

		IntrospectedColumn versionColumn = introspectedTable.getBaseColumns().stream()
				.filter(c -> c.getActualColumnName().equalsIgnoreCase(versionColumnName))//
				.findFirst()//
				.orElse(null);
		if (versionColumn == null) {
			return;
		}

		Method newMethod = new Method(ori.getName().concat("VersionIncludes")); //$NON-NLS-1$
		context.getCommentGenerator().addGeneralMethodAnnotation(newMethod, introspectedTable,
				interfaze.getImportedTypes());
		newMethod.setDefault(true);
		newMethod.addParameter(ori.getParameters().get(0)); // $NON-NLS-1$
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("org.mybatis.dynamic.sql.SqlColumn");
		type.addTypeArgument(new FullyQualifiedJavaType("?"));
		Parameter includes = new Parameter(type, "includes", true);
		interfaze.addImportedType(type);
		interfaze.addImportedType(new FullyQualifiedJavaType("java.util.Set"));
		interfaze.addImportedType(new FullyQualifiedJavaType("java.util.HashSet"));
		interfaze.addImportedType(new FullyQualifiedJavaType("java.util.Arrays"));
		newMethod.addParameter(includes);
		newMethod.setReturnType(ori.getReturnType().get());
		newMethod.addBodyLine("int count = update(c -> {");
		newMethod.addBodyLine("Set<SqlColumn<?>> columns = new HashSet<>(Arrays.asList(includes));");
		introspectedTable.getNonPrimaryKeyColumns().forEach(column -> {
			String fieldName = AbstractMethodGenerator.calculateFieldName("", column);
			String methodName = JavaBeansUtil.getGetterMethodName(column.getJavaProperty(),
					column.getFullyQualifiedJavaType());

			if (!column.equals(versionColumn)) {
				newMethod.addBodyLine("if (columns.contains(" + fieldName + ")) {");
				// record.getVersion
				methodName = "record::"+methodName;
			}else {
				if (newVersionMethod.equalsIgnoreCase("default")) {
					// record.getVersion()+1
					methodName = "record."+methodName+"() + 1";
				} else {
					// record.getNewVersion
					methodName = "record::"+newVersionMethod;
				}
			}
			newMethod.addBodyLine("c.set(" + fieldName + ").equalTo(" + methodName + ");");
			if (!column.equals(versionColumn)) {
				newMethod.addBodyLine("}");
			}
		});

		String prefix = "c.where(";
		for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
			String fieldName = AbstractMethodGenerator.calculateFieldName("", column);
			String methodName = JavaBeansUtil.getGetterMethodName(column.getJavaProperty(),
					column.getFullyQualifiedJavaType());
			newMethod.addBodyLine(prefix + fieldName + ", isEqualTo(record::" + methodName + "))");
			prefix = ".and(";
		}
		{
			String fieldName = AbstractMethodGenerator.calculateFieldName("", versionColumn);
			String methodName = JavaBeansUtil.getGetterMethodName(versionColumn.getJavaProperty(),
					versionColumn.getFullyQualifiedJavaType());
			newMethod.addBodyLine(prefix + fieldName + ", isEqualTo(record::" + methodName + "));");
		}

		newMethod.addBodyLine("return c;");
		newMethod.addBodyLine("});");
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
