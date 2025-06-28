package com.senai.prova;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.env.Environment;

import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class ProvaApplication implements ApplicationContextAware, ApplicationListener<ContextClosedEvent> {

    private static final String UTC_ID = "UTC";
    private static ApplicationContext ctx;

    public static void main(String[] args) {

        String property = "user.timezone";
        System.setProperty(property, UTC_ID);
        String timeZone = System.getProperty(property);
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));

        ctx = SpringApplication.run(ProvaApplication.class, args);

        Environment env = ctx.getEnvironment();
        log.warn("""
                 
                 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                 \t\t\tApplication [{}] is running
                 Profile(s): \t{}
                 Timezone: \t\t[{}]
                 Server : \t\t[{}:{}]
                 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%""",
                env.getProperty("spring.application.name"),
                env.getActiveProfiles(),
                timeZone,
                env.getProperty("server.address"),
                env.getProperty("server.port"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        log.warn("""
                 
                 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                 \t\t\t\t\t!!! STOPPING !!!
                 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%""");
    }
}
