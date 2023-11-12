package moe.zhi.boot.app.config;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;

@Configuration
public class WebUrlExtensionConfig implements WebMvcConfigurer {
    /**
     * add .html suffix to url path when url path is not root or api path
     * @param registry registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                final var isApiHandle = handler instanceof HandlerMethod;
                final var path = request.getServletPath();
                if (Optional.of(path).filter(f -> f.contains(".")).isEmpty()
                        && !"/".equals(path)
                        && !isApiHandle) {
                    request.getRequestDispatcher(path + ".html").forward(request, response);
                    return false;
                }
                return true;
            }
        });
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }
}
