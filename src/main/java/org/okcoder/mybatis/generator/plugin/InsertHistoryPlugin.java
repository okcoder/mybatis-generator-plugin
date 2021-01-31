package org.okcoder.mybatis.generator.plugin;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.internal.util.JavaBeansUtil;

public class InsertHistoryPlugin extends PluginAdapter {

	//private Log log = LogFactory.getLog(InsertHistoryPlugin.class);

	@Override
	public boolean clientInsertMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		insertSelect(method, interfaze, introspectedTable);
		insertHistory(method, interfaze, introspectedTable);

		return super.clientInsertMethodGenerated(method, interfaze, introspectedTable);
	}

	private void insertSelect(Method ori, Interface interfaze, IntrospectedTable introspectedTable) {
		Method insertSelect = new Method("insertSelect"); 
		context.getCommentGenerator().addGeneralMethodAnnotation(insertSelect, introspectedTable,
				interfaze.getImportedTypes());
		insertSelect.addAnnotation("@InsertProvider(type = SqlProviderAdapter.class, method = \"insertSelect\")");
		FullyQualifiedJavaType insertSelectStatementProviderType = new FullyQualifiedJavaType("org.mybatis.dynamic.sql.insert.render.InsertSelectStatementProvider");
		interfaze.getImportedTypes().add(insertSelectStatementProviderType);
		Parameter insertSelectStatement=new Parameter(insertSelectStatementProviderType, "insertSelectStatement");
		insertSelect.addParameter(insertSelectStatement); 
		insertSelect.setReturnType(ori.getReturnType().get());
		insertSelect.setAbstract(true);
		interfaze.addMethod(insertSelect);
		
	}
	private void insertHistory(Method ori, Interface interfaze, IntrospectedTable introspectedTable) {
		Method insertHistory = new Method("insertHistory"); 
		context.getCommentGenerator().addGeneralMethodAnnotation(insertHistory, introspectedTable,
				interfaze.getImportedTypes());
		insertHistory.setDefault(true);
		introspectedTable.getPrimaryKeyColumns().forEach(column->{
			insertHistory.addParameter(new Parameter(column.getFullyQualifiedJavaType(), column.getJavaProperty().concat("_"))); 
		});
		insertHistory.setReturnType(new FullyQualifiedJavaType("int"));

		/*
        WhereApplier where = dsl -> {
            dsl.where(invoiceId, isEqualTo(invoiceId_));
            if (invoiceDtlId_ != null) {
                dsl.and(invoiceDtlId, isEqualTo(invoiceDtlId_));
            }
            return dsl;
        };
        */
		
		interfaze.getImportedTypes().add(new FullyQualifiedJavaType("org.mybatis.dynamic.sql.where.WhereApplier"));
		insertHistory.addBodyLine("WhereApplier where = dsl -> {");
		IntStream.range(0, introspectedTable.getPrimaryKeyColumns().size()).forEach(idx->{
			String keyColumnName = introspectedTable.getPrimaryKeyColumns().get(0).getJavaProperty();
			if (idx==0) {
				insertHistory.addBodyLine(MessageFormat.format("dsl.where({0}, isEqualTo({0}_));",keyColumnName));				
			}else {
				insertHistory.addBodyLine(MessageFormat.format("if ({0}_ != null) '{'",keyColumnName));				
				insertHistory.addBodyLine(MessageFormat.format("dsl.and({0}, isEqualTo({0}_));",keyColumnName));				
				insertHistory.addBodyLine("}");
			}
		});
		insertHistory.addBodyLine("return dsl;");
		insertHistory.addBodyLine("};");
		
/*
        String historyTableName = "history.".concat(TInvoiceDtl.tableNameAtRuntime());
        SqlTable historyTable = new SqlTable(historyTableName) {
        };
        SqlColumn<?>[] columnList = Arrays.stream(selectList).map(c -> (SqlColumn<?>) c).toArray(n -> new SqlColumn<?>[n]);
        InsertSelectStatementProvider insertSelectStatement = SqlBuilder.insertInto(historyTable)
                .withColumnList(columnList)
                .withSelectStatement(SqlBuilder.select(columnList).from(TInvoiceDtl).applyWhere(where))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        return insertSelect(insertSelectStatement);
*/
		String tableFieldName  = JavaBeansUtil.getValidPropertyName(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
		interfaze.getImportedTypes().add(new FullyQualifiedJavaType("org.mybatis.dynamic.sql.SqlTable"));
		interfaze.getImportedTypes().add(new FullyQualifiedJavaType("org.mybatis.dynamic.sql.SqlBuilder"));
		interfaze.getImportedTypes().add(new FullyQualifiedJavaType("org.mybatis.dynamic.sql.render.RenderingStrategies"));
		insertHistory.addBodyLine("");
		insertHistory.addBodyLine(MessageFormat.format("String historyTableName = \"history.\".concat({0}.tableNameAtRuntime());",tableFieldName));
		insertHistory.addBodyLine("SqlTable historyTable = new SqlTable(historyTableName) {");
		insertHistory.addBodyLine("};");
		insertHistory.addBodyLine("SqlColumn<?>[] columnList = Arrays.stream(selectList).map(c -> (SqlColumn<?>) c).toArray(n -> new SqlColumn<?>[n]);");
		insertHistory.addBodyLine("InsertSelectStatementProvider insertSelectStatement = SqlBuilder.insertInto(historyTable)");
		insertHistory.addBodyLine("        .withColumnList(columnList)");
		insertHistory.addBodyLine(MessageFormat.format("        .withSelectStatement(SqlBuilder.select(columnList).from({0}).applyWhere(where))",tableFieldName));
		insertHistory.addBodyLine("        .build()");
		insertHistory.addBodyLine("        .render(RenderingStrategies.MYBATIS3);");
		insertHistory.addBodyLine("");
		insertHistory.addBodyLine("return insertSelect(insertSelectStatement);");

		interfaze.addMethod(insertHistory);
	}

	public boolean validate(List<String> warnings) {
		String targetRuntime = this.context.getTargetRuntime();
		return targetRuntime == null || Objects.equals(targetRuntime, "MyBatis3DynamicSql")
				|| Objects.equals(targetRuntime, "MyBatis3")//
				|| Objects.equals(targetRuntime, "MyBatis3Simple");
	}

}
