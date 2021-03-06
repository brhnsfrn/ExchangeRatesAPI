package com.brhnsfrn.exchangerate;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class ExchangerateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangerateApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.brhnsfrn.exchangerate.api.controllers"))        
          .build()
          .apiInfo(apiInfos());                                           
    }
	
	private ApiInfo apiInfos() {
		return new ApiInfoBuilder()
				.title("Exchange Rates API")
				.description("This pages documents Exchange Rates RESTful Web Service endpoints")
				.contact(new Contact("Burhan Safran", "", "brhnsfrn@gmail.com"))
				.license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
				.version("1.0")
				.build();
	}
	
	@PostConstruct
	  public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Istanbul"));
	  }

}
