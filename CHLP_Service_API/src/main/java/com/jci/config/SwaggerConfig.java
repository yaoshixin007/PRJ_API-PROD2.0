package com.jci.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.google.common.base.Predicate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
public class SwaggerConfig {
	
	
	@Value("${info.description}")
	private String infoDescription; 
	
	@Value("${info.description.MDG}")
	private String infoDescriptionMDG; 
	
	@Value("${info.description.QAD}")
	private String infoDescriptionQAD; 
	
	@Value("${info.description.Commercial}")
	private String infoDescriptionCommercial; 
	
	@Value("${info.description.SN}")
	private String infoDescriptionSN; 
	
	@Value("${info.description.SNOperation}")
	private String infoDescriptionSNOperation; 
	
	@Value("${info.version}")
	private String infoVersion; 
	
	@Value("${info.title}")
	private String infoTitle; 
	
	@Value("${info.termsOfService}")
	private String infoTermsOfService; 
	
	@Value("${info.license}")
	private String infoLicense; 
	
	@Value("${info.license.url}")
	private String infoLicenseUrl; 
	
	@Value("${info.schemes}")
	private String infoschemes;
	
	@Value("${info.jci.homepage}")
	private String infoHomepage;
	
	@Bean
	public Docket apiDefault() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("All API Rest Points")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.jci.controller"))
				.paths(PathSelectors.any()).build()
		        .apiInfo(apiInfoDefault());
	}
	
	@Bean
	public Docket apiProductQAD() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Product(QAD)")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.jci.controller"))
				.paths(productQADPaths())
                .build()
			    .apiInfo(apiInfoQAD())
                ;
	}
	
	@Bean
	public Docket apiProductCommercial() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Product(Commercial)")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.jci.controller"))
				.paths(productCommercialPaths())
                .build()
			    .apiInfo(apiInfoCommercial())
                ;
	}
	
	@Bean
	public Docket apiBusinessPartner() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Business Partner (MDG)")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.jci.controller"))
				.paths(businessPartnerPaths())
                .build()
			    .apiInfo(apiInfoMDG())
                ;
	}
	
	@Bean
	public Docket apiBatterySN() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Battery SN")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.jci.controller"))
				.paths(batterySNPaths())
                .build()
			    .apiInfo(apiInfoSN())
                ;
	}
	
	@Bean
	public Docket apiBatterySnOperation() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Battery SN Operation")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.jci.controller"))
				.paths(batterySNOperationPaths())
                .build()
			    .apiInfo(apiInfoSNOps())
                ;
	}
	


	private ApiInfo apiInfoDefault() {
		return new ApiInfo(infoTitle,
				infoDescription,
				infoVersion,
				infoTermsOfService,
				new Contact("jci-admin", infoHomepage, "jci-admin@jci.com"),
				infoLicense, infoLicenseUrl, Collections.emptyList());
	}
	private ApiInfo apiInfoMDG() {
		return new ApiInfo(" Business Partner (MDG) ",
				infoDescriptionMDG,
				"",
				"",
				new Contact("", infoHomepage, ""),
				"", "", Collections.emptyList());
	}
	
	private ApiInfo apiInfoQAD() {
		return new ApiInfo("Product (QAD)",
				infoDescriptionQAD,
				"",
				"",
				new Contact("", infoHomepage, ""),
				"", "", Collections.emptyList());
	}
	
	private ApiInfo apiInfoCommercial() {
		return new ApiInfo("Product (Commercial) Interface",
				infoDescriptionCommercial,
				"",
				"",
				new Contact("", infoHomepage, ""),
				"", "", Collections.emptyList());
	}
	
		
	private ApiInfo apiInfoSN() {
		return new ApiInfo(" Battery SN Interface",
				infoDescriptionSN,
				"",
				"",
				new Contact("", infoHomepage, ""),
				"", "", Collections.emptyList());
	}
	
	private ApiInfo apiInfoSNOps() {
		return new ApiInfo("Battery SN Operation Interface",
				infoDescriptionSNOperation,
				"",
				"",
				new Contact("", infoHomepage, ""),
				"", "", Collections.emptyList());
	}
	
	@SuppressWarnings("unchecked")
	private Predicate<String> productQADPaths() {
	      return or(
	          regex(".*QADProducts.*"));
	}
	@SuppressWarnings("unchecked")
	private Predicate<String> productCommercialPaths() {
		return or(regex(".*CMRLProducts.*"));
	}
	@SuppressWarnings("unchecked")
	private Predicate<String> businessPartnerPaths() {
	      return or(
	          regex(".*MDGBusinessPartner.*"));
	}
	@SuppressWarnings("unchecked")
	private Predicate<String> batterySNPaths() {
	      return or(
	          regex(".*SN"));
	}
	
	@SuppressWarnings("unchecked")
	private Predicate<String> batterySNOperationPaths() {
	      return or(
	          regex(".*BatterySNOperation.*"));
	}
}