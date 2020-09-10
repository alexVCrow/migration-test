package ua.alexcrow.flyway.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ua.alexcrow.flyway.interseptors.LogInterceptor;

@Configuration
public class LogConfig implements WebMvcConfigurer {

    private final LogInterceptor logInterceptor;

    public LogConfig(LogInterceptor logInterceptor) {
        this.logInterceptor = logInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);
    }

}
