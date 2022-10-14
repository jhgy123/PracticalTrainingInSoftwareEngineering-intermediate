package com.example.elmserver.configuration.properies;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@Data
@ConfigurationProperties(prefix = "sqlserver.jdbc")
public class MySecurityProperites {
    private String dburl;
}
