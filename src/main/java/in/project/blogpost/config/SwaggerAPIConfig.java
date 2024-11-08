package in.project.blogpost.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

@Configuration
public class SwaggerAPIConfig {
	
	public static final String AUTHORIZATION_HEADER="Authorization";
	
	private ApiKey apikeys()
	{
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "Header");	
	}

	private List<SecurityContext> securityContexts()
	{
		return Arrays.asList(SecurityContext.builder().securityReferences(securityReference()).build());
	}
		
	
	private List<SecurityReference> securityReference(){
		AuthorizationScope scopes= new AuthorizationScope("global","accessEverything");
		 return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scopes}));
	}
	
//	this is main configuration bean wth help of which all the details of the API is there
	@Bean Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.securityContexts(securityContexts())
				.securitySchemes(Arrays.asList(apikeys()))	
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.any())
				.build();
	}
	
	
	@Bean
	public UiConfiguration uiConfiguration() {
	    return UiConfigurationBuilder//
	            .builder()//
	            .defaultModelsExpandDepth(-1)//
	            .build();//
	}
	
	private ApiInfo apiInfo() {
		// TODO Auto-generated method stub
		return new ApiInfo("BlogPost Application", "BlogPost REST API Creation using springboot", "version-2", "terms of service", new Contact("Anurodh Singh", "anurodhsingh.io", "anurodhsinghmp@gmail.com"),"License of Creation", "https://license.com", Collections.emptyList());}
}
