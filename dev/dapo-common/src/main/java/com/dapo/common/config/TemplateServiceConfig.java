package com.dapo.common.config;

import com.dapo.common.service.FreeMakerTemplateService;
import com.dapo.common.service.TemplateService;
import freemarker.template.Configuration;
import org.springframework.context.annotation.Bean;

public abstract class TemplateServiceConfig {

    @Bean
    TemplateService freeMakerTemplateService(Configuration configuration){
        return new FreeMakerTemplateService(configuration);
    }
}
