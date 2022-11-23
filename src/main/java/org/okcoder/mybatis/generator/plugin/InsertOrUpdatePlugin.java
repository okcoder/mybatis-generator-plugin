package org.okcoder.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.logging.Log;
import org.mybatis.generator.logging.LogFactory;
import org.mybatis.generator.runtime.dynamic.sql.elements.Utils;

import java.util.List;
import java.util.Objects;

public class InsertOrUpdatePlugin extends PluginAdapter {

	private Log log = LogFactory.getLog(InsertOrUpdatePlugin.class);

	@Override
	public boolean clientInsertMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		insertOrUpdate(method, interfaze, introspectedTable);

		return super.clientInsertMethodGenerated(method, interfaze, introspectedTable);
	}

	private void insertOrUpdate(Method ori, Interface interfaze, IntrospectedTable introspectedTable) {
		if (!Utils.generateUpdateByPrimaryKey(introspectedTable)) {
			log.error("!Utils.generateUpdateByPrimaryKey(introspectedTable)");
			return;
		}
		// interfaze.addImportedType(new FullyQualifiedJavaType("interfaze"));
		Method insertOrUpdate = new Method("insertOrUpdate"); //$NON-NLS-1$
		context.getCommentGenerator().addGeneralMethodAnnotation(insertOrUpdate, introspectedTable,
				interfaze.getImportedTypes());
		insertOrUpdate.setDefault(true);
		insertOrUpdate.addParameter(ori.getParameters().get(0)); // $NON-NLS-1$
		insertOrUpdate.setReturnType(ori.getReturnType().get());
		insertOrUpdate.setDefault(true);
		String recordParamName= ori.getParameters().get(0).getName();
		insertOrUpdate.addBodyLine("int count = this.updateByPrimaryKey("+recordParamName+");");
		insertOrUpdate.addBodyLine("if (count > 0) {");
		insertOrUpdate.addBodyLine("return count;");
		insertOrUpdate.addBodyLine("} else {");
		insertOrUpdate.addBodyLine("return this.insert("+recordParamName+");");
		insertOrUpdate.addBodyLine("}");
		interfaze.addMethod(insertOrUpdate);
	}

	public boolean validate(List<String> warnings) {
		String targetRuntime = this.context.getTargetRuntime();
		return targetRuntime == null || Objects.equals(targetRuntime, "MyBatis3DynamicSql")
				|| Objects.equals(targetRuntime, "MyBatis3")
				|| Objects.equals(targetRuntime, "MyBatis3Simple");
	}

}
