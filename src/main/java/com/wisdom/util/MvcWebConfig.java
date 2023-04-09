package com.wisdom.util;


import org.omg.PortableInterceptor.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.Charset;
import java.util.List;

/**
 *  implements WebMvcConfigurer
 *  一些教程使用的WebMvcConfigurationSupport，
 *  我使用后发现，classpath:/META/resources/，classpath:/resources/，classpath:/static/，classpath:/public/）不生效。
 *  具体可以原因，大家可以看下源码因为：WebMvcAutoConfiguration上有个条件注解： @ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
 *
 */

@Configuration
public class MvcWebConfig extends WebMvcConfigurationSupport {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //和页面有关的静态目录都放在项目的static目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //上传的图片在D盘下的storage目录下，访问路径如：http://localhost:8081/storage/1.jpg
        //其中image表示访问的前缀。"file:D:/storage/"是文件真实的存储路径
        registry.addResourceHandler("/image/**").addResourceLocations("file:F:/image/");
        super.addResourceHandlers(registry);

    }

}
