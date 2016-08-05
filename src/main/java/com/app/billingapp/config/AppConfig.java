/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.config;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 *
 * @author node
 */
@Configuration
@EnableWebMvc
@MultipartConfig
@ComponentScan(basePackages = "com.app.billingapp")
@Import({HibernateConfig.class})
public class AppConfig extends WebMvcConfigurerAdapter{
    @Bean
    public ViewResolver viewResolver() {
        
        ContentNegotiatingViewResolver bean = new ContentNegotiatingViewResolver();
        List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();

        // Apache Tiles view resolver...
        UrlBasedViewResolver tilesResolver = new UrlBasedViewResolver();
        tilesResolver.setViewClass(TilesView.class);
        tilesResolver.setOrder(0);
        viewResolvers.add(tilesResolver);

        // Default view resolver as a fallback. Allow view names to be served with just 
        // .jsp extension rather than a tiles id. This is primarily for AJAX calls that 
        // request a JSP resource...
        InternalResourceViewResolver defaultResolver = new InternalResourceViewResolver();
        defaultResolver.setPrefix(new String("/WEB-INF/views/"));
        defaultResolver.setSuffix(new String(".jsp"));
        defaultResolver.setOrder(1);
        viewResolvers.add(defaultResolver);

        bean.setViewResolvers(viewResolvers);

        return bean;
    }
    
    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        final String[] definitions = {"/WEB-INF/tiles.xml"};
        tilesConfigurer.setDefinitions(definitions);
        return tilesConfigurer;
    }
   
    
//    @Bean
//    public ViewResolver tilesViewResolver() {
//        TilesViewResolver resolver = new TilesViewResolver();
//        resolver.setViewClass(JstlView.class);
//        resolver.setPrefix("/WEB-INF/views/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }
     @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
    
}
