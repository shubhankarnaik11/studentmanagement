package com.prat.student;

import com.prat.student.config.ValidationsConfig;
import org.hibernate.annotations.SortComparator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value={"validation.properties", "application.properties"})
public class StudentApplication {
	public static void main(String[] args) {
		ApplicationContext apc = SpringApplication.run(StudentApplication.class, args);
		for(String s : apc.getBeanDefinitionNames()){
			System.out.println(s);
		}
	}

}
