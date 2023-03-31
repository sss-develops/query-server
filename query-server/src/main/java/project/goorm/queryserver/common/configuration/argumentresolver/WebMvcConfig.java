package project.goorm.queryserver.common.configuration.argumentresolver;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final PagingArgumentResolver pagingArgumentResolver;

    public WebMvcConfig(PagingArgumentResolver pagingArgumentResolver) {
        this.pagingArgumentResolver = pagingArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(pagingArgumentResolver);
    }
}
