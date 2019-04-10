package com.jci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 
 * @author apiadmin
 *
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class CHLPApplication extends SpringBootServletInitializer   {

	/*public static void main(String[] args) {
		SpringApplication.run(CHLPApplication.class, args);
	}
	*/
	
	  @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(CHLPApplication.class);
	    }

	    public static void main(String[] args) throws Exception {
	        SpringApplication.run(CHLPApplication.class, args);
	    }

	
}
