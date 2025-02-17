package io.rsg.cqrs;

import io.rsg.cqrs.repository.OrderRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,  DataSourceAutoConfiguration.class })
@EnableElasticsearchRepositories(basePackageClasses = OrderRepository.class)
public class CqrsQueryApp {

	public static void main(String[] args) {
		SpringApplication.run(CqrsQueryApp.class, args);
	}

}
