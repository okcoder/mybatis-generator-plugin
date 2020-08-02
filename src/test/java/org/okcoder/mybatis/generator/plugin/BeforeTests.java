package org.okcoder.mybatis.generator.plugin;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


public class BeforeTests {

	@SpringBootApplication
	@MapperScan("org.okcoder.mybatis_generator_plugin_test.domain.repository")
	public static class TestApp {

		public static void main(String[] args) {
			SpringApplication.run(TestApp.class, args);
		}
	}
	@Test
	public void generate() throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		InputStream configFile = this.getClass().getClassLoader().getResourceAsStream("generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
	
}
