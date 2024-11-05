package com.slost_only1.slost_only1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaAuditConfig {

    @Bean
    public SecurityAuditorAware auditorAware() {
        return new SecurityAuditorAware();
    }
}
