package com.example.thang_sample_code.configuration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;

public class LocalResolver  extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getHeader("Accept-Language");
        return StringUtils.hasLength(lang) ? Locale.lookup(Locale.LanguageRange.parse(lang), List.of(new Locale("en"), new Locale("fr"))) : Locale.getDefault();
    }

    public ResourceBundleMessageSource getMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(3600);
        return messageSource;
    }
}
