package br.com.ivanfsilva.webfood.core.openapi;

import br.com.ivanfsilva.webfood.api.exceptionhandler.Problem;
import br.com.ivanfsilva.webfood.api.model.CozinhaModel;
import br.com.ivanfsilva.webfood.api.openapi.model.CozinhasModelOpenApi;
import br.com.ivanfsilva.webfood.api.openapi.model.PageableModelOpenApi;
import com.fasterxml.classmate.TypeResolver;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;

import springfox.documentation.spi.DocumentationType;

import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

    @Bean
    public Docket apiDocket() {
        var typeResolver = new TypeResolver();

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("br.com.ivanfsilva.webfood.api"))
                    .paths(PathSelectors.any())
    //					.paths(PathSelectors.ant("/restaurantes/*"))
                    .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
                .globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
                .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
                .additionalModels(typeResolver.resolve(Problem.class))
                .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(Page.class, CozinhaModel.class),
                        CozinhasModelOpenApi.class))
                .apiInfo(apiInfo())
//                .tags(new Tag("Cidades", "Gerencia as cidades"));
                .tags(tags()[0], tags());
    }

    private List<ResponseMessage> globalGetResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Erro interno do servidor")
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.NOT_ACCEPTABLE.value())
                        .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
                        .build()
        );
    }

    private List<ResponseMessage> globalPostPutResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Requisição inválida (erro do cliente)")
                        .responseModel(new ModelRef("Problema"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Erro interno no servidor")
                        .responseModel(new ModelRef("Problema"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.NOT_ACCEPTABLE.value())
                        .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                        .message("Requisição recusada porque o corpo está em um formato não suportado")
                        .responseModel(new ModelRef("Problema"))
                        .build()
        );
    }

    private List<ResponseMessage> globalDeleteResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Requisição inválida (erro do cliente)")
                        .responseModel(new ModelRef("Problema"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Erro interno no servidor")
                        .responseModel(new ModelRef("Problema"))
                        .build()
        );
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("WebFood API")
                .description("API aberta para clientes e restaurantes")
                .version("1")
                .contact(new Contact("WebFood", "https://www.webfood.com", "contato@webfood.com"))
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private Tag[] tags() {
        return new Tag[] {
                new Tag("City", "Manage the cities"),
                new Tag("Cuisine", "Manage the cuisines"),
                new Tag("Group", "Manage the groups"),
                new Tag("Group permission", "Manage the groups permissions"),
                new Tag("Order", "Manage the orders"),
                new Tag("Order status", "Manage the orders status"),
                new Tag("Payment method", "Manage the payment methods"),
                new Tag("Product photo", "Manage the products photos"),
                new Tag("Restaurant", "Manage the restaurants"),
                new Tag("Restaurant payment method", "Manage the restaurants payment methods"),
                new Tag("Restaurant product", "Manage the restaurants products"),
                new Tag("Restaurant user", "Manage the restaurants owners"),
                new Tag("State", "Manage the states"),
                new Tag("Statistic", "Manage the statistics"),
                new Tag("User", "Manage the users"),
                new Tag("User group", "Manage the users groups")
        };
    }

}
