package org.okcoder.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.runtime.dynamic.sql.elements.AbstractMethodGenerator;
import org.mybatis.generator.runtime.dynamic.sql.elements.Utils;

import java.util.List;
import java.util.Objects;

public class DeleteByPrimaryKeyOptimisticLockingPlugin extends PluginAdapter {

	@Override
	public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		deleteByPrimaryKeyVersion(method, interfaze, introspectedTable);

		return super.clientDeleteByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
	}

	private void deleteByPrimaryKeyVersion(Method ori, Interface interfaze, IntrospectedTable introspectedTable) {

		if (!Utils.generateDeleteByPrimaryKey(introspectedTable)) {
			return;
		}

		String versionColumnName = this.properties.getOrDefault("columnName", "VERSION").toString();

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
		Parameter versionParameter = new Parameter(versionColumn.getFullyQualifiedJavaType(),
				versionColumn.getJavaProperty() + "_");
		newMethod.addParameter(versionParameter);
		interfaze.addImportedType(versionColumn.getFullyQualifiedJavaType());
		interfaze.addImportedType(
				new FullyQualifiedJavaType("org.springframework.dao.OptimisticLockingFailureException"));

		newMethod.setReturnType(ori.getReturnType().get());

		newMethod.addBodyLine("int count = delete(c ->");

		String prefix = "    c.where(";
		for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
			String fieldName = AbstractMethodGenerator.calculateFieldName("", column);
			newMethod.addBodyLine(prefix + fieldName + ", isEqualTo(" + fieldName + "_))");
			prefix = "    .and(";
		}
		{
			String fieldName = AbstractMethodGenerator.calculateFieldName("", versionColumn);
			newMethod
					.addBodyLine(prefix + fieldName + ", isEqualTo(" + versionColumn.getJavaProperty() + "_))");
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
