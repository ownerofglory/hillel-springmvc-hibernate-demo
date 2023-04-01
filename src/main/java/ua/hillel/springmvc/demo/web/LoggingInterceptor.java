package ua.hillel.springmvc.demo.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Incoming request");
        String method = request.getMethod();

        log.debug("Request method {}", method);
        if (method.equalsIgnoreCase(HttpMethod.GET.name())) {
            String queryString = request.getQueryString();
            log.debug("Query String {}", queryString);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       log.info("Response");
       log.debug("Response status {}", response.getStatus());
    }
}
