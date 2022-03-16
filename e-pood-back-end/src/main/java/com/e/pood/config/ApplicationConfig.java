package com.e.pood.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan(basePackages = {"com.e.pood"})
@ConfigurationPropertiesScan("com.e.pood.config.properties")
public class ApplicationConfig {
}
