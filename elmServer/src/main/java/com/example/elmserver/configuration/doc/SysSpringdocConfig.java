package com.example.elmserver.configuration.doc;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class SysSpringdocConfig    {
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI().info(new Info().title("xxx管理系统API").version("1.0")
//                .description("Knife4j集成springdoc-openapi示例").termsOfService("http://doc.xiaominfo.com")
//                .license(new License().name("Apache 2.0").url("http://doc.xiaominfo.com")));
//    }
//    @Bean
//    public GroupedOpenApi sysPlatformApiDoc() {
//        return GroupedOpenApi.builder().group("系统管理-平台")
//                .packagesToScan("edu.yun.elm.controllers")
//                //  .addOpenApiCustomiser(jwtHeaderOpenApiCustomiser())
//                .build();
//
//    }
//
//}
