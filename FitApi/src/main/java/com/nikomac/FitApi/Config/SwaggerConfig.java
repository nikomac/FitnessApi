package com.nikomac.FitApi.Config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * Configuracion necesaria para montar swagger y swagger UI para las dos versiones de la API en la segunda version 
 * he especificado el uso de un header para la authenticacion
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket v1() {
		return GetDocket("v1");
	}

	@Bean
	public Docket v2() {
		
		ParameterBuilder aParameterBuilder = new ParameterBuilder();
		aParameterBuilder.name("API_KEY").modelRef(new ModelRef("string")).parameterType("header")
				.defaultValue("testkey").required(true).build();

		List<Parameter> aParameters = new ArrayList<>();
		aParameters.add(aParameterBuilder.build());

		return GetDocket("v2").globalOperationParameters(aParameters);
	}

	private Docket GetDocket(String ver) {
		return new Docket(DocumentationType.SWAGGER_2).groupName("API " + ver).select()
				.apis(RequestHandlerSelectors.basePackage("com.nikomac.FitApi.Controllers")).paths(x -> {
					return x.startsWith("/api/" + ver + "/");
				}).build().apiInfo(new ApiInfoBuilder().version(ver).title("Fitness API")
						.description("Documentation Fitness API " + ver).build());
	}

}