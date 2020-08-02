package org.okcoder.mybatis.generator.plugin;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.okcoder.mybatis_generator_plugin_test.domain.entity.SpendInfo;
import org.okcoder.mybatis_generator_plugin_test.domain.repository.SpendInfoDynamicSqlSupport;
import org.okcoder.mybatis_generator_plugin_test.domain.repository.SpendInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BeforeTests.TestApp.class)
public class UpdateByPrimaryKeyIncludesPluginTests {

	@Autowired
	private SpendInfoMapper mapper;

	@Test
	public void testUpdateByPrimaryKeyIncludes() {
		SpendInfo spendInfo = new SpendInfo();
		spendInfo.setSpendTypeId("X");
		spendInfo.setGoodsTypeId("ST1");
		spendInfo.setSpendTypeName("spendTypeName1");
		spendInfo.setGoodsTypeName("GoodsTypeName1");
		spendInfo.setCreateTime(LocalDateTime.now().plusYears(-1).truncatedTo(ChronoUnit.SECONDS));
		mapper.insert(spendInfo);

		SpendInfo spendInfo1 = new SpendInfo();
		BeanUtils.copyProperties(spendInfo, spendInfo1);
		spendInfo1.setSpendTypeName("GpendTypeName2");
		spendInfo1.setGoodsTypeName("GoodsTypeName2");
		spendInfo1.setCreateTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		spendInfo1.setUpdateTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

		mapper.updateByPrimaryKeyIncludes(spendInfo1, SpendInfoDynamicSqlSupport.goodsTypeName,
				SpendInfoDynamicSqlSupport.updateTime);

		Optional<SpendInfo> updatedRecord = mapper.selectByPrimaryKey(spendInfo.getSpendTypeId(),
				spendInfo.getGoodsTypeId());
		
		assertAll(() -> assertEquals(spendInfo1.getSpendTypeId(), updatedRecord.get().getSpendTypeId()),
				() -> assertEquals(spendInfo1.getGoodsTypeId(), updatedRecord.get().getGoodsTypeId()),
				() -> assertEquals(spendInfo.getSpendTypeName(), updatedRecord.get().getSpendTypeName()),
				() -> assertEquals(spendInfo1.getGoodsTypeName(), updatedRecord.get().getGoodsTypeName()),
				() -> assertEquals(spendInfo.getCreateTime(), updatedRecord.get().getCreateTime()),
				() -> assertEquals(spendInfo1.getUpdateTime(), updatedRecord.get().getUpdateTime()));
	}
}
