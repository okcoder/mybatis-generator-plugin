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

public class UpdateByPrimaryKeyIncludesPlugin extends PluginAdapter {

	@Override
	public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		updateByPrimaryKeyIncludes(method, interfaze, introspectedTable);
		return super.clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(method, interfaze, introspectedTable);
	}

	private void updateByPrimaryKeyIncludes(Method ori, Interface interfaze, IntrospectedTable introspectedTable) {

		if (!Utils.generateUpdateByPrimaryKey(introspectedTable)) {
			return;
		}
		Method updateByPrimaryKeyIncludes = new Method(ori.getName().concat("Includes")); //$NON-NLS-1$
		context.getCommentGenerator().addGeneralMethodAnnotation(updateByPrimaryKeyIncludes, introspectedTable,
				interfaze.getImportedTypes());
		updateByPrimaryKeyIncludes.setDefault(true);
		updateByPrimaryKeyIncludes.addParameter(ori.getParameters().get(0)); // $NON-NLS-1$
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("org.mybatis.dynamic.sql.SqlColumn");
		type.addTypeArgument(new FullyQualifiedJavaType("?"));
		Parameter includes = new Parameter(type, "includes", true);
		interfaze.addImportedType(type);
		interfaze.addImportedType(new FullyQualifiedJavaType("java.util.Set"));
		interfaze.addImportedType(new FullyQualifiedJavaType("java.util.HashSet"));
		interfaze.addImportedType(new FullyQualifiedJavaType("java.util.Arrays"));
		updateByPrimaryKeyIncludes.addParameter(includes);
		updateByPrimaryKeyIncludes.setReturnType(ori.getReturnType().get());
		updateByPrimaryKeyIncludes.setDefault(true);
		updateByPrimaryKeyIncludes.addBodyLine("return update(c -> {");
		updateByPrimaryKeyIncludes.addBodyLine("Set<SqlColumn<?>> columns = new HashSet<>(Arrays.asList(includes));");
		introspectedTable.getNonPrimaryKeyColumns().forEach(column -> {
			String fieldName = AbstractMethodGenerator.calculateFieldName("", column);
			String methodName = JavaBeansUtil.getGetterMethodName(column.getJavaProperty(),
					column.getFullyQualifiedJavaType());

			updateByPrimaryKeyIncludes.addBodyLine("if (columns.contains(" + fieldName + ")) {");
			updateByPrimaryKeyIncludes.addBodyLine("c.set(" + fieldName + ").equalTo(record::" + methodName + ");");
			updateByPrimaryKeyIncludes.addBodyLine("}");
		});

		String prefix = "c.where(";
		for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
			String fieldName = AbstractMethodGenerator.calculateFieldName("", column);
			String methodName = JavaBeansUtil.getGetterMethodName(column.getJavaProperty(),
					column.getFullyQualifiedJavaType());
			updateByPrimaryKeyIncludes.addBodyLine(prefix + fieldName + ", isEqualTo(record::" + methodName + "))");
			prefix = ".and(";
		}
		updateByPrimaryKeyIncludes.addBodyLine(";");

		updateByPrimaryKeyIncludes.addBodyLine("return c;");
		updateByPrimaryKeyIncludes.addBodyLine("});");
		interfaze.addMethod(updateByPrimaryKeyIncludes);

	}

	/*-
	
	https://github.com/mybatis/generator/blob/5c2da8eeaf5c20e9ec54109ce9e057be7cec5591/core/mybatis-generator-core/src/main/java/org/mybatis/generator/runtime/dynamic/sql/elements/v2/UpdateByPrimaryKeySelectiveMethodGeneratorV2.java
	
	@Generated(value = "org.mybatis.generator.api.MyBatisGenerator", comments = "Source Table: PUBLIC.SPEND_INFO")
	default int updateByPrimaryKeyIncludes(SpendInfo record, SqlColumn<?>... includes) {
		return update(c -> {
			Set<SqlColumn<?>> columns = new HashSet<>(Arrays.asList(includes));
	
			if (columns.contains(spendTypeName)) {
				c.set(spendTypeName).equalTo(record::getSpendTypeName);
			}
			if (columns.contains(spendTypeName)) {
				c.set(goodsTypeName).equalTo(record::getGoodsTypeName);
			}
			if (columns.contains(spendTypeName)) {
				c.set(createTime).equalTo(record::getCreateTime);
			}
			if (columns.contains(spendTypeName)) {
				c.set(updateTime).equalTo(record::getUpdateTime);
			}
			c.where(spendTypeId, isEqualTo(record::getSpendTypeId))
			.and(goodsTypeId, isEqualTo(record::getGoodsTypeId));
			return c;
		});
	}
	
	@Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
	default int updateByPrimaryKey(SpendInfo record) {
	        return update(c ->
	        c.set(spendTypeName).equalToWhenPresent(record::getSpendTypeName)
	        .set(goodsTypeName).equalToWhenPresent(record::getGoodsTypeName)
	        .set(createTime).equalToWhenPresent(record::getCreateTime)
	        .set(updateTime).equalToWhenPresent(record::getUpdateTime)
	        .where(spendTypeId, isEqualTo(record::getSpendTypeId))
	        .and(goodsTypeId, isEqualTo(record::getGoodsTypeId))
	    );
	}
	  */

	public boolean validate(List<String> warnings) {
		String targetRuntime = this.context.getTargetRuntime();
		return targetRuntime == null || Objects.equals(targetRuntime, "MyBatis3DynamicSql");
	}

}
