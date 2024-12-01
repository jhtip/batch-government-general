package net.danal.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "net.danal.batch")
@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
public class TestBatchConfig {
}
