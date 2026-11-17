package org.example.config;

import org.example.AppConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {


    // 加载父容器
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    // 加载子容器
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    //将DispatcherServlet映射到所有请求
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
