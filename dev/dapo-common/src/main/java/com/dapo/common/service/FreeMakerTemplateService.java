package com.dapo.common.service;

import com.dapo.common.error.ErrorCodes;
import com.dapo.common.exception.TemplateEngineException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created by dimomass on 10.10.18.
 */

@Service
public class FreeMakerTemplateService implements TemplateService {

    private Configuration freeMarkerConfig;

    @Autowired
    public FreeMakerTemplateService(Configuration freeMarkerConfig) {
        this.freeMarkerConfig = freeMarkerConfig;
    }

    @Override
    public String merge(String templateName, String lang, Map<String,Object> model) throws TemplateEngineException {
        try {
            Template template = freeMarkerConfig.getTemplate(String.format("%s/%s.ftl", lang, templateName));
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (IOException | TemplateException e) {
            throw new TemplateEngineException(ErrorCodes.template_parse_exception);
        }
    }
}
