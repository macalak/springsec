package ite.librarymaster.application.configuration;

import ite.access.application.interceptor.TokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorsConfiguration implements WebMvcConfigurer {
	
	@Autowired
	private TokenEnhancer tokenEnhancer;
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(tokenEnhancer);
	}
}
