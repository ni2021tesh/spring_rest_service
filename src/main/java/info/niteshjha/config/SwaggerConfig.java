// Copyright (c) 2018 Travelex Ltd

package info.niteshjha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    private static final Contact DEFAULT_CONTACT = new Contact("Nitesh Jha", "https://www.niteshjha.info", "niteshjha2021@gmail.com");
    private static final ApiInfo DEFAULT = new ApiInfo("Rest service for managing user and their post",
            "The Api provide api end points to create new user and to create post by that particular user", "1.0", "urn:tos", DEFAULT_CONTACT,
            "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());

    private static final Set<String> PRODUCES_AND_CONSUMES_FORMAT = new HashSet<>(Arrays.asList("application/json", "application/xml"));

    @Bean
    public Docket api() {             //@formatter:off
        return new Docket(DocumentationType.SWAGGER_2)
                        .produces(PRODUCES_AND_CONSUMES_FORMAT)
                        .consumes(PRODUCES_AND_CONSUMES_FORMAT).select()
                        .apis(RequestHandlerSelectors.basePackage("info.niteshjha.controller"))
                        .paths(PathSelectors.any())
                        .build()
                        .apiInfo(DEFAULT)
                        .tags(new Tag("users resource","Operations pertaining to user management."),
                        new Tag("posts resource","Operations pertaining to users post management.")
                        ,new Tag("greet i18n resource","Operations related to i18n based on locale"));
        //@formatter:on
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
