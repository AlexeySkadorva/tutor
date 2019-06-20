package by.bsu.tutor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class StaticConfig extends WebMvcConfigurerAdapter {

    private static final Integer CACHE_PERIOD = 3600 * 72;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**/*.*").addResourceLocations("classpath:/static/")
//                .setCachePeriod(CACHE_PERIOD);
//
//        super.addResourceHandlers(registry);
    }

}