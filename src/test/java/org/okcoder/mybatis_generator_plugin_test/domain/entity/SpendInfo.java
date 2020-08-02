package org.okcoder.mybatis_generator_plugin_test.domain.entity;

import java.time.LocalDateTime;
import javax.annotation.Generated;

public class SpendInfo {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.538927+09:00", comments="Source field: PUBLIC.SPEND_INFO.SPEND_TYPE_ID")
    private String spendTypeId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.540714+09:00", comments="Source field: PUBLIC.SPEND_INFO.GOODS_TYPE_ID")
    private String goodsTypeId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.540885+09:00", comments="Source field: PUBLIC.SPEND_INFO.SPEND_TYPE_NAME")
    private String spendTypeName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.541063+09:00", comments="Source field: PUBLIC.SPEND_INFO.GOODS_TYPE_NAME")
    private String goodsTypeName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.541265+09:00", comments="Source field: PUBLIC.SPEND_INFO.CREATE_TIME")
    private LocalDateTime createTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.541451+09:00", comments="Source field: PUBLIC.SPEND_INFO.UPDATE_TIME")
    private LocalDateTime updateTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.540337+09:00", comments="Source field: PUBLIC.SPEND_INFO.SPEND_TYPE_ID")
    public String getSpendTypeId() {
        return spendTypeId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.540648+09:00", comments="Source field: PUBLIC.SPEND_INFO.SPEND_TYPE_ID")
    public void setSpendTypeId(String spendTypeId) {
        this.spendTypeId = spendTypeId == null ? null : spendTypeId.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.540772+09:00", comments="Source field: PUBLIC.SPEND_INFO.GOODS_TYPE_ID")
    public String getGoodsTypeId() {
        return goodsTypeId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.540832+09:00", comments="Source field: PUBLIC.SPEND_INFO.GOODS_TYPE_ID")
    public void setGoodsTypeId(String goodsTypeId) {
        this.goodsTypeId = goodsTypeId == null ? null : goodsTypeId.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.54094+09:00", comments="Source field: PUBLIC.SPEND_INFO.SPEND_TYPE_NAME")
    public String getSpendTypeName() {
        return spendTypeName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.541008+09:00", comments="Source field: PUBLIC.SPEND_INFO.SPEND_TYPE_NAME")
    public void setSpendTypeName(String spendTypeName) {
        this.spendTypeName = spendTypeName == null ? null : spendTypeName.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.541121+09:00", comments="Source field: PUBLIC.SPEND_INFO.GOODS_TYPE_NAME")
    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.5412+09:00", comments="Source field: PUBLIC.SPEND_INFO.GOODS_TYPE_NAME")
    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName == null ? null : goodsTypeName.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.541334+09:00", comments="Source field: PUBLIC.SPEND_INFO.CREATE_TIME")
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.541397+09:00", comments="Source field: PUBLIC.SPEND_INFO.CREATE_TIME")
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.541512+09:00", comments="Source field: PUBLIC.SPEND_INFO.UPDATE_TIME")
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-02T15:08:55.541568+09:00", comments="Source field: PUBLIC.SPEND_INFO.UPDATE_TIME")
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}