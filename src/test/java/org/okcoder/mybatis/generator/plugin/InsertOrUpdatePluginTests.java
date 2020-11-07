package org.okcoder.mybatis.generator.plugin;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.okcoder.mybatis_generator_plugin_test.domain.entity.SpendInfo;
import org.okcoder.mybatis_generator_plugin_test.domain.repository.SpendInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BeforeTests.TestApp.class)
public class InsertOrUpdatePluginTests {

	@Autowired
	private SpendInfoMapper mapper;
	@Test
	public void testInsertOrUpdate() {
		SpendInfo spendInfo = new SpendInfo();
		spendInfo.setSpendTypeId("I");
		spendInfo.setGoodsTypeId("ST1");
		spendInfo.setSpendTypeName("spendTypeName1");
		spendInfo.setGoodsTypeName("GoodsTypeName1");
		spendInfo.setVersion(0L);
		spendInfo.setCreateTime(LocalDateTime.now().plusYears(-1).truncatedTo(ChronoUnit.SECONDS));
		mapper.insertOrUpdate(spendInfo);

		Optional<SpendInfo> inserted = mapper.selectByPrimaryKey(spendInfo.getSpendTypeId(),
				spendInfo.getGoodsTypeId());
		assertTrue(inserted.isPresent());
		assertAll(() -> assertEquals(spendInfo.getSpendTypeId(), inserted.get().getSpendTypeId()),
				() -> assertEquals(spendInfo.getGoodsTypeId(), inserted.get().getGoodsTypeId()),
				() -> assertEquals(spendInfo.getSpendTypeName(), inserted.get().getSpendTypeName()),
				() -> assertEquals(spendInfo.getGoodsTypeName(), inserted.get().getGoodsTypeName()),
				() -> assertEquals(spendInfo.getCreateTime(), inserted.get().getCreateTime()),
				() -> assertEquals(spendInfo.getUpdateTime(), inserted.get().getUpdateTime()));


		SpendInfo spendInfo1 = new SpendInfo();
		BeanUtils.copyProperties(spendInfo, spendInfo1);
		spendInfo1.setSpendTypeName("GpendTypeName2");
		spendInfo1.setGoodsTypeName("GoodsTypeName2");
		spendInfo1.setCreateTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		spendInfo1.setUpdateTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

		mapper.insertOrUpdate(spendInfo1);
		Optional<SpendInfo> updated = mapper.selectByPrimaryKey(spendInfo.getSpendTypeId(),
				spendInfo.getGoodsTypeId());
		assertTrue(updated.isPresent());
		assertAll(() -> assertEquals(spendInfo1.getSpendTypeId(), updated.get().getSpendTypeId()),
				() -> assertEquals(spendInfo1.getGoodsTypeId(), updated.get().getGoodsTypeId()),
				() -> assertEquals(spendInfo1.getSpendTypeName(), updated.get().getSpendTypeName()),
				() -> assertEquals(spendInfo1.getGoodsTypeName(), updated.get().getGoodsTypeName()),
				() -> assertEquals(spendInfo1.getCreateTime(), updated.get().getCreateTime()),
				() -> assertEquals(spendInfo1.getUpdateTime(), updated.get().getUpdateTime()));
	}

}
