package com.uestc.naldo.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MyWebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //添加自定义图片路径
        registry.addResourceHandler("/image/**").addResourceLocations("file:///D:/PSMIMAGE/");

        super.addResourceHandlers(registry);
    }
}
