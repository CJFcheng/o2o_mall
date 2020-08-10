package niuliu.cheng.demo.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class addPictureResour extends WebMvcConfigurerAdapter {
    @Override//增加资源路径
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:C://inetpub//wwwroot//ToARD/");
        super.addResourceHandlers(registry);

    }

}