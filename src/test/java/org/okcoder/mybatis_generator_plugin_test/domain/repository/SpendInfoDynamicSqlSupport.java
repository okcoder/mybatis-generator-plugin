package org.okcoder.mybatis_generator_plugin_test.domain.repository;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SpendInfoDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    public static final SpendInfo spendInfo = new SpendInfo();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.SPEND_TYPE_ID")
    public static final SqlColumn<String> spendTypeId = spendInfo.spendTypeId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.GOODS_TYPE_ID")
    public static final SqlColumn<String> goodsTypeId = spendInfo.goodsTypeId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.SPEND_TYPE_NAME")
    public static final SqlColumn<String> spendTypeName = spendInfo.spendTypeName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.GOODS_TYPE_NAME")
    public static final SqlColumn<String> goodsTypeName = spendInfo.goodsTypeName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.VERSION")
    public static final SqlColumn<Long> version = spendInfo.version;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.CREATE_TIME")
    public static final SqlColumn<LocalDateTime> createTime = spendInfo.createTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.UPDATE_TIME")
    public static final SqlColumn<LocalDateTime> updateTime = spendInfo.updateTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    public static final class SpendInfo extends SqlTable {
        public final SqlColumn<String> spendTypeId = column("SPEND_TYPE_ID", JDBCType.CHAR);

        public final SqlColumn<String> goodsTypeId = column("GOODS_TYPE_ID", JDBCType.CHAR);

        public final SqlColumn<String> spendTypeName = column("SPEND_TYPE_NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> goodsTypeName = column("GOODS_TYPE_NAME", JDBCType.VARCHAR);

        public final SqlColumn<Long> version = column("VERSION", JDBCType.BIGINT);

        public final SqlColumn<LocalDateTime> createTime = column("CREATE_TIME", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> updateTime = column("UPDATE_TIME", JDBCType.TIMESTAMP);

        public SpendInfo() {
            super("SPEND_INFO");
        }
    }
}