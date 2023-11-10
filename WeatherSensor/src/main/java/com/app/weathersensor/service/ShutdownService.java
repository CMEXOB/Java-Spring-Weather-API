package com.app.weathersensor.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Service to shut down application
 *
 * @author Skripko Egor
 */
@Service
public class ShutdownService implements ApplicationContextAware {

    private ApplicationContext context;

    /**
     * Shut down application
     */
    public void shutdownContext() {
        ((ConfigurableApplicationContext) context).close();
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.context = ctx;
    }
}
