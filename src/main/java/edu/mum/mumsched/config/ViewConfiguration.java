package edu.mum.mumsched.config;

import ch.mfrey.thymeleaf.extras.with.WithDialect;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ViewConfiguration {

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    @Bean
    public WithDialect withDialect() {
        return new WithDialect();
    }
}
