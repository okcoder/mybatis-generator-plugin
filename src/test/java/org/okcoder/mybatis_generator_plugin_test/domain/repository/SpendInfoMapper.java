package org.okcoder.mybatis_generator_plugin_test.domain.repository;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.okcoder.mybatis_generator_plugin_test.domain.repository.SpendInfoDynamicSqlSupport.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;
import org.okcoder.mybatis_generator_plugin_test.domain.entity.SpendInfo;

@Mapper
public interface SpendInfoMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.554537+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    BasicColumn[] selectList = BasicColumn.columnList(spendTypeId, goodsTypeId, spendTypeName, goodsTypeName, createTime, updateTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.545859+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.546533+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.546923+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<SpendInfo> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.547448+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<SpendInfo> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.547925+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SpendInfoResult")
    Optional<SpendInfo> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.54831+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SpendInfoResult", value = {
        @Result(column="SPEND_TYPE_ID", property="spendTypeId", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="GOODS_TYPE_ID", property="goodsTypeId", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="SPEND_TYPE_NAME", property="spendTypeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="GOODS_TYPE_NAME", property="goodsTypeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SpendInfo> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.549503+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.549905+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, spendInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.550283+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, spendInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.55088+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    default int deleteByPrimaryKey(String spendTypeId_, String goodsTypeId_) {
        return delete(c -> 
            c.where(spendTypeId, isEqualTo(spendTypeId_))
            .and(goodsTypeId, isEqualTo(goodsTypeId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.552662+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    default int insertOrUpdate(SpendInfo record) {
        int count = this.updateByPrimaryKey(record);
        if (count > 0) {
            return count;
        } else {
            return this.insert(record);
        }
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.551608+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    default int insert(SpendInfo record) {
        return MyBatis3Utils.insert(this::insert, record, spendInfo, c ->
            c.map(spendTypeId).toProperty("spendTypeId")
            .map(goodsTypeId).toProperty("goodsTypeId")
            .map(spendTypeName).toProperty("spendTypeName")
            .map(goodsTypeName).toProperty("goodsTypeName")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.553161+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    default int insertMultiple(Collection<SpendInfo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, spendInfo, c ->
            c.map(spendTypeId).toProperty("spendTypeId")
            .map(goodsTypeId).toProperty("goodsTypeId")
            .map(spendTypeName).toProperty("spendTypeName")
            .map(goodsTypeName).toProperty("goodsTypeName")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.55359+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    default int insertSelective(SpendInfo record) {
        return MyBatis3Utils.insert(this::insert, record, spendInfo, c ->
            c.map(spendTypeId).toPropertyWhenPresent("spendTypeId", record::getSpendTypeId)
            .map(goodsTypeId).toPropertyWhenPresent("goodsTypeId", record::getGoodsTypeId)
            .map(spendTypeName).toPropertyWhenPresent("spendTypeName", record::getSpendTypeName)
            .map(goodsTypeName).toPropertyWhenPresent("goodsTypeName", record::getGoodsTypeName)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.555271+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    default Optional<SpendInfo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, spendInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.555627+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    default List<SpendInfo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, spendInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.555983+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    default List<SpendInfo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, spendInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.556326+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    default Optional<SpendInfo> selectByPrimaryKey(String spendTypeId_, String goodsTypeId_) {
        return selectOne(c ->
            c.where(spendTypeId, isEqualTo(spendTypeId_))
            .and(goodsTypeId, isEqualTo(goodsTypeId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.556671+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, spendInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.557021+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    static UpdateDSL<UpdateModel> updateAllColumns(SpendInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(spendTypeId).equalTo(record::getSpendTypeId)
                .set(goodsTypeId).equalTo(record::getGoodsTypeId)
                .set(spendTypeName).equalTo(record::getSpendTypeName)
                .set(goodsTypeName).equalTo(record::getGoodsTypeName)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.55747+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SpendInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(spendTypeId).equalToWhenPresent(record::getSpendTypeId)
                .set(goodsTypeId).equalToWhenPresent(record::getGoodsTypeId)
                .set(spendTypeName).equalToWhenPresent(record::getSpendTypeName)
                .set(goodsTypeName).equalToWhenPresent(record::getGoodsTypeName)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.558102+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    default int updateByPrimaryKeyIncludes(SpendInfo record, SqlColumn<?> ... includes) {
        return update(c -> {
            Set<SqlColumn<?>> columns = new HashSet<>(Arrays.asList(includes));
            if (columns.contains(spendTypeName)) {
                c.set(spendTypeName).equalTo(record::getSpendTypeName);
            }
            if (columns.contains(goodsTypeName)) {
                c.set(goodsTypeName).equalTo(record::getGoodsTypeName);
            }
            if (columns.contains(createTime)) {
                c.set(createTime).equalTo(record::getCreateTime);
            }
            if (columns.contains(updateTime)) {
                c.set(updateTime).equalTo(record::getUpdateTime);
            }
            c.where(spendTypeId, isEqualTo(record::getSpendTypeId))
            .and(goodsTypeId, isEqualTo(record::getGoodsTypeId))
            ;
            return c;
        });
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.557897+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    default int updateByPrimaryKey(SpendInfo record) {
        return update(c ->
            c.set(spendTypeName).equalTo(record::getSpendTypeName)
            .set(goodsTypeName).equalTo(record::getGoodsTypeName)
            .set(createTime).equalTo(record::getCreateTime)
            .set(updateTime).equalTo(record::getUpdateTime)
            .where(spendTypeId, isEqualTo(record::getSpendTypeId))
            .and(goodsTypeId, isEqualTo(record::getGoodsTypeId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.558898+09:00", comments="Source Table: PUBLIC.SPEND_INFO")
    default int updateByPrimaryKeySelective(SpendInfo record) {
        return update(c ->
            c.set(spendTypeName).equalToWhenPresent(record::getSpendTypeName)
            .set(goodsTypeName).equalToWhenPresent(record::getGoodsTypeName)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .set(updateTime).equalToWhenPresent(record::getUpdateTime)
            .where(spendTypeId, isEqualTo(record::getSpendTypeId))
            .and(goodsTypeId, isEqualTo(record::getGoodsTypeId))
        );
    }
}