package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>功 能：Swagger UI配置</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年5月4日 下午9:47:51</p>
 * @author WangJian
 * @version 1.0
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {
	/** 接口文档说明 */
	private String title;
	/** 接口文档版本 */
	private String version;

	/**
	 * 启用 Swagger UI静态资源访问
	 */
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	/**
	 * 设置Swagger UI生成的接口目录
	 * @return
	 */
	@Bean
	public Docket api() {
		/*
		 * 文档分组名 groupName("full")
		 */
		return new Docket(DocumentationType.SWAGGER_2).forCodeGeneration(true).apiInfo(apiInfo());
	}

	/**
	 * 设置Swagger UI生成的接口文档
	 * @return
	 */
	private ApiInfo apiInfo() {
		/*
		 * 文档描述 description("@2017 Copyrignt. Powered by WangJian.") 文档作者 contact(new Contact("WangJian", "https://github.com/wjtree", "21wjtree@163.com")) 文档协议
		 * licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
		 */
		return new ApiInfoBuilder().title(title).version(version).build();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}