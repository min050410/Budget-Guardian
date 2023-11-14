package com.wanted.budget.guardian;

import com.wanted.budget.guardian.common.config.properties.JwtProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
@EnableConfigurationProperties({ JwtProperties.class })
@EnableJpaAuditing
@SpringBootApplication
public class BudgetGuardianApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudgetGuardianApplication.class, args);
    }

}
