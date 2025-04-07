package site.hb.order.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import site.hb.order.utils.HybridIdGenerator;

@Configuration
public class HybridIdGeneratorConfig {

    @Bean
    public HybridIdGenerator hybridUuidGenerator() throws Exception {
        return new HybridIdGenerator();
    }

}
