package com.nikomac.FitApi.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket v1() {
		String ver = "v1";
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("API "+ ver)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.nikomac.FitApi.Controllers"))
				.paths( x -> { return x.startsWith("/"+ver+"/");})
				.build()
				.apiInfo(new ApiInfoBuilder()
					.version(ver)
					.title("Fitness API")
					.description("Documentation Fitness API "+ver)
					.build()
				);
	}
	
	@Bean
	public Docket v2() {
		String ver = "v2";
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("API "+ ver)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.nikomac.FitApi.Controllers"))
				.paths( x -> { return x.startsWith("/"+ver+"/");})
				.build()
				.apiInfo(new ApiInfoBuilder()
					.version(ver)
					.title("Fitness API")
					.description("Documentation Fitness API "+ver)
					.build()
				);
	}
	
	

}