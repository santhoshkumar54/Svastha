package com.svastha;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class SvasthaApplication extends SpringBootServletInitializer implements WebMvcConfigurer {

	@Value("${upload.directory}")
	private String uploadDirectory;

	private String imagePath;

//    private static final Logger logger = LoggerFactory.getLogger(SvasthaApplication.class);
//
//    public SvasthaApplication(@Value("${LOG_PATH}") String logPath) {
//    	System.out.println("logggg"+logPath);
//        logger.debug("Log Path from properties: {}", logPath);
//    }
//    
	@PostConstruct
    public void init() {
        System.out.println("Upload Directory: " + uploadDirectory);
        imagePath ="file:"+uploadDirectory+"images/";
        System.out.println("IMAGE MAPPING Directory: " + imagePath);
    }

	public static void main(String[] args) {
		SpringApplication.run(SvasthaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");
			System.out.println(uploadDirectory);

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
		};
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("IMAGE MAPPING registering Directory: " + imagePath);

		registry.addResourceHandler("/farmer/images/**").addResourceLocations(imagePath);
	}

}
