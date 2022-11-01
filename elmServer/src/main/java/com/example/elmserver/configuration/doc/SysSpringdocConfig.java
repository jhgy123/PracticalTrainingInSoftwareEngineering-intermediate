package com.example.elmserver.configuration.doc;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SysSpringdocConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("饿了么管理系统API").version("1.0")
                .contact(new Contact().name("龚尹鸿杰"))
                .description("软件工程实现-中级-饿了么api文档")
                .termsOfService("http://doc.xiaominfo.com")
                .license(new License().name("Apache 2.0").url("http://doc.xiaominfo.com")));
    }

    // @Bean
    // public GroupedOpenApi orderApi() {
    //     return GroupedOpenApi.builder()
    //             .group("系统管理-订单")
    //             .packagesToScan("edu.yun.elm.orders")
    //             //  .addOpenApiCustomiser(jwtHeaderOpenApiCustomiser())
    //             .build();

    // }
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("系统管理-管理员")
                .packagesToScan("com.example.elmserver.controllers.admin")
//                  .addOpenApiCustomiser(jwtHeaderOpenApiCustomiser())
                .build();

    }
//    @Bean
//    public GroupedOpenApi menuApi() {
//        return GroupedOpenApi.builder()
//                .group("系统管理-test")
//                .packagesToScan("com.example.elmserver.controllers.oldcontrollers")
////                  .addOpenApiCustomiser(jwtHeaderOpenApiCustomiser())
//                .build();
//
//    }

}
