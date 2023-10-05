package com.prat.student;

import com.prat.student.config.ValidationsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableConfigurationProperties(ValidationsConfig.class)
@PropertySource(value={"validation.properties", "application.properties"})
public class StudentApplication {
	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

}
