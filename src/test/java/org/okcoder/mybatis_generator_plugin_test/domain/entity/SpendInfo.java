package org.okcoder.mybatis_generator_plugin_test.domain.entity;

import java.time.LocalDateTime;
import javax.annotation.Generated;

public class SpendInfo {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.SPEND_TYPE_ID")
    private String spendTypeId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.GOODS_TYPE_ID")
    private String goodsTypeId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.SPEND_TYPE_NAME")
    private String spendTypeName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.GOODS_TYPE_NAME")
    private String goodsTypeName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.VERSION")
    private Long version;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.CREATE_TIME")
    private LocalDateTime createTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.UPDATE_TIME")
    private LocalDateTime updateTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.SPEND_TYPE_ID")
    public String getSpendTypeId() {
        return spendTypeId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    public SpendInfo withSpendTypeId(String spendTypeId) {
        this.setSpendTypeId(spendTypeId);
        return this;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.SPEND_TYPE_ID")
    public void setSpendTypeId(String spendTypeId) {
        this.spendTypeId = spendTypeId == null ? null : spendTypeId.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.GOODS_TYPE_ID")
    public String getGoodsTypeId() {
        return goodsTypeId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    public SpendInfo withGoodsTypeId(String goodsTypeId) {
        this.setGoodsTypeId(goodsTypeId);
        return this;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.GOODS_TYPE_ID")
    public void setGoodsTypeId(String goodsTypeId) {
        this.goodsTypeId = goodsTypeId == null ? null : goodsTypeId.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.SPEND_TYPE_NAME")
    public String getSpendTypeName() {
        return spendTypeName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    public SpendInfo withSpendTypeName(String spendTypeName) {
        this.setSpendTypeName(spendTypeName);
        return this;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.SPEND_TYPE_NAME")
    public void setSpendTypeName(String spendTypeName) {
        this.spendTypeName = spendTypeName == null ? null : spendTypeName.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.GOODS_TYPE_NAME")
    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    public SpendInfo withGoodsTypeName(String goodsTypeName) {
        this.setGoodsTypeName(goodsTypeName);
        return this;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.GOODS_TYPE_NAME")
    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName == null ? null : goodsTypeName.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.VERSION")
    public Long getVersion() {
        return version;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    public SpendInfo withVersion(Long version) {
        this.setVersion(version);
        return this;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.VERSION")
    public void setVersion(Long version) {
        this.version = version;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.CREATE_TIME")
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    public SpendInfo withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.CREATE_TIME")
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.UPDATE_TIME")
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    public SpendInfo withUpdateTime(LocalDateTime updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: PUBLIC.SPEND_INFO.UPDATE_TIME")
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: PUBLIC.SPEND_INFO")
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", spendTypeId=").append(spendTypeId);
        sb.append(", goodsTypeId=").append(goodsTypeId);
        sb.append(", spendTypeName=").append(spendTypeName);
        sb.append(", goodsTypeName=").append(goodsTypeName);
        sb.append(", version=").append(version);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}