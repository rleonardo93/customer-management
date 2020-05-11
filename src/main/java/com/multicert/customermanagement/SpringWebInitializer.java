package com.multicert.customermanagement;

import com.multicert.customermanagement.config.AppConfig;
import com.multicert.customermanagement.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Register a DispatcherServlet and allow us to easily add configuration classes
 * to load for both classes and to apply filters to the DispatcherServlet and
 * to provide the servlet mapping.
 */
public class SpringWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AppConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
