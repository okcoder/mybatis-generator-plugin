package org.okcoder.mybatis_generator_plugin_test.domain.repository;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.okcoder.mybatis_generator_plugin_test.domain.repository.SpendInfoDynamicSqlSupport.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.insert.render.InsertSelectStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.okcoder.mybatis_generator_plugin_test.domain.entity.SpendInfo;
import org.springframework.dao.OptimisticLockingFailureException;

@Mapper
public interface SpendInfoMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<SpendInfo>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    BasicColumn[] selectList = BasicColumn.columnList(spendTypeId, goodsTypeId, spendTypeName, goodsTypeName, version, createTime, updateTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SpendInfoResult", value = {
        @Result(column="SPEND_TYPE_ID", property="spendTypeId", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="GOODS_TYPE_ID", property="goodsTypeId", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="SPEND_TYPE_NAME", property="spendTypeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="GOODS_TYPE_NAME", property="goodsTypeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="VERSION", property="version", jdbcType=JdbcType.BIGINT),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SpendInfo> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SpendInfoResult")
    Optional<SpendInfo> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, spendInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, spendInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default int deleteByPrimaryKeyVersion(String spendTypeId_, String goodsTypeId_, Long version_) {
        int count = delete(c ->
            c.where(spendTypeId, isEqualTo(spendTypeId_))
            .and(goodsTypeId, isEqualTo(goodsTypeId_))
            .and(version, isEqualTo(version_))
        );
        if (count == 0){
            throw new OptimisticLockingFailureException("");
        }
        return count;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default int deleteByPrimaryKey(String spendTypeId_, String goodsTypeId_) {
        return delete(c -> 
            c.where(spendTypeId, isEqualTo(spendTypeId_))
            .and(goodsTypeId, isEqualTo(goodsTypeId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default int insertOrUpdate(SpendInfo row) {
        int count = this.updateByPrimaryKey(row);
        if (count > 0) {
            return count;
        } else {
            return this.insert(row);
        }
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertSelect")
    int insertSelect(InsertSelectStatementProvider insertSelectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default int insertHistory(String spendTypeId_, String goodsTypeId_) {
        WhereApplier where = dsl -> {
            dsl.where(spendTypeId, isEqualTo(spendTypeId_));
            if (spendTypeId_ != null) {
                dsl.and(spendTypeId, isEqualTo(spendTypeId_));
            }
        };
        
        String historyTableName = "history.".concat(spendInfo.tableNameAtRuntime());
        SqlTable historyTable = new SqlTable(historyTableName) {
        };
        SqlColumn<?>[] columnList = Arrays.stream(selectList).map(c -> (SqlColumn<?>) c).toArray(n -> new SqlColumn<?>[n]);
        InsertSelectStatementProvider insertSelectStatement = SqlBuilder.insertInto(historyTable)
                .withColumnList(columnList)
                .withSelectStatement(SqlBuilder.select(columnList).from(spendInfo).applyWhere(where))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        
        return insertSelect(insertSelectStatement);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default int insert(SpendInfo row) {
        return MyBatis3Utils.insert(this::insert, row, spendInfo, c ->
            c.map(spendTypeId).toProperty("spendTypeId")
            .map(goodsTypeId).toProperty("goodsTypeId")
            .map(spendTypeName).toProperty("spendTypeName")
            .map(goodsTypeName).toProperty("goodsTypeName")
            .map(version).toProperty("version")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default int insertMultiple(Collection<SpendInfo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, spendInfo, c ->
            c.map(spendTypeId).toProperty("spendTypeId")
            .map(goodsTypeId).toProperty("goodsTypeId")
            .map(spendTypeName).toProperty("spendTypeName")
            .map(goodsTypeName).toProperty("goodsTypeName")
            .map(version).toProperty("version")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default int insertSelective(SpendInfo row) {
        return MyBatis3Utils.insert(this::insert, row, spendInfo, c ->
            c.map(spendTypeId).toPropertyWhenPresent("spendTypeId", row::getSpendTypeId)
            .map(goodsTypeId).toPropertyWhenPresent("goodsTypeId", row::getGoodsTypeId)
            .map(spendTypeName).toPropertyWhenPresent("spendTypeName", row::getSpendTypeName)
            .map(goodsTypeName).toPropertyWhenPresent("goodsTypeName", row::getGoodsTypeName)
            .map(version).toPropertyWhenPresent("version", row::getVersion)
            .map(createTime).toPropertyWhenPresent("createTime", row::getCreateTime)
            .map(updateTime).toPropertyWhenPresent("updateTime", row::getUpdateTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default Optional<SpendInfo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, spendInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default List<SpendInfo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, spendInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default List<SpendInfo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, spendInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default SpendInfo selectByPrimaryKeyVersion(String spendTypeId_, String goodsTypeId_, Long version_) {
        Optional<SpendInfo> record = selectOne(c ->
            c.where(spendTypeId, isEqualTo(spendTypeId_))
            .and(goodsTypeId, isEqualTo(goodsTypeId_))
            .and(version, isEqualTo(version_))
        );
        if (!record.isPresent()){
            throw new OptimisticLockingFailureException("");
        }
        return record.get();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default Optional<SpendInfo> selectByPrimaryKey(String spendTypeId_, String goodsTypeId_) {
        return selectOne(c ->
            c.where(spendTypeId, isEqualTo(spendTypeId_))
            .and(goodsTypeId, isEqualTo(goodsTypeId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, spendInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    static UpdateDSL<UpdateModel> updateAllColumns(SpendInfo row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(spendTypeId).equalTo(row::getSpendTypeId)
                .set(goodsTypeId).equalTo(row::getGoodsTypeId)
                .set(spendTypeName).equalTo(row::getSpendTypeName)
                .set(goodsTypeName).equalTo(row::getGoodsTypeName)
                .set(version).equalTo(row::getVersion)
                .set(createTime).equalTo(row::getCreateTime)
                .set(updateTime).equalTo(row::getUpdateTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SpendInfo row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(spendTypeId).equalToWhenPresent(row::getSpendTypeId)
                .set(goodsTypeId).equalToWhenPresent(row::getGoodsTypeId)
                .set(spendTypeName).equalToWhenPresent(row::getSpendTypeName)
                .set(goodsTypeName).equalToWhenPresent(row::getGoodsTypeName)
                .set(version).equalToWhenPresent(row::getVersion)
                .set(createTime).equalToWhenPresent(row::getCreateTime)
                .set(updateTime).equalToWhenPresent(row::getUpdateTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default int updateByPrimaryKeyVersion(SpendInfo row) {
        int count = update(c ->
            c.set(spendTypeName).equalTo(row::getSpendTypeName)
            .set(goodsTypeName).equalTo(row::getGoodsTypeName)
            .set(version).equalTo(row::getVersion)
            .set(createTime).equalTo(row::getCreateTime)
            .set(updateTime).equalTo(row::getUpdateTime)
            .where(spendTypeId, isEqualTo(row::getSpendTypeId))
            .and(goodsTypeId, isEqualTo(row::getGoodsTypeId))
            .and(version, isEqualTo(row.getVersion() - 1))
        );
        if (count == 0){
            throw new OptimisticLockingFailureException("");
        }
        return count;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default int updateByPrimaryKeyVersionIncludes(SpendInfo row, SqlColumn<?> ... includes) {
        int count = update(c -> {
            Set<SqlColumn<?>> columns = new HashSet<>(Arrays.asList(includes));
            if (columns.contains(spendTypeName)) {
                c.set(spendTypeName).equalTo(row::getSpendTypeName);
            }
            if (columns.contains(goodsTypeName)) {
                c.set(goodsTypeName).equalTo(row::getGoodsTypeName);
            }
            c.set(version).equalTo(row::getVersion);
            if (columns.contains(createTime)) {
                c.set(createTime).equalTo(row::getCreateTime);
            }
            if (columns.contains(updateTime)) {
                c.set(updateTime).equalTo(row::getUpdateTime);
            }
            c.where(spendTypeId, isEqualTo(row::getSpendTypeId))
            .and(goodsTypeId, isEqualTo(row::getGoodsTypeId))
            .and(version, isEqualTo(row.getVersion() - 1));
            return c;
        });
        if (count == 0){
            throw new OptimisticLockingFailureException("");
        }
        return count;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default int updateByPrimaryKeyIncludes(SpendInfo row, SqlColumn<?> ... includes) {
        return update(c -> {
            Set<SqlColumn<?>> columns = new HashSet<>(Arrays.asList(includes));
            if (columns.contains(spendTypeName)) {
                c.set(spendTypeName).equalTo(row::getSpendTypeName);
            }
            if (columns.contains(goodsTypeName)) {
                c.set(goodsTypeName).equalTo(row::getGoodsTypeName);
            }
            if (columns.contains(version)) {
                c.set(version).equalTo(row::getVersion);
            }
            if (columns.contains(createTime)) {
                c.set(createTime).equalTo(row::getCreateTime);
            }
            if (columns.contains(updateTime)) {
                c.set(updateTime).equalTo(row::getUpdateTime);
            }
            c.where(spendTypeId, isEqualTo(row::getSpendTypeId))
            .and(goodsTypeId, isEqualTo(row::getGoodsTypeId))
            ;
            return c;
        });
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default int updateByPrimaryKey(SpendInfo row) {
        return update(c ->
            c.set(spendTypeName).equalTo(row::getSpendTypeName)
            .set(goodsTypeName).equalTo(row::getGoodsTypeName)
            .set(version).equalTo(row::getVersion)
            .set(createTime).equalTo(row::getCreateTime)
            .set(updateTime).equalTo(row::getUpdateTime)
            .where(spendTypeId, isEqualTo(row::getSpendTypeId))
            .and(goodsTypeId, isEqualTo(row::getGoodsTypeId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    default int updateByPrimaryKeySelective(SpendInfo row) {
        return update(c ->
            c.set(spendTypeName).equalToWhenPresent(row::getSpendTypeName)
            .set(goodsTypeName).equalToWhenPresent(row::getGoodsTypeName)
            .set(version).equalToWhenPresent(row::getVersion)
            .set(createTime).equalToWhenPresent(row::getCreateTime)
            .set(updateTime).equalToWhenPresent(row::getUpdateTime)
            .where(spendTypeId, isEqualTo(row::getSpendTypeId))
            .and(goodsTypeId, isEqualTo(row::getGoodsTypeId))
        );
    }
}