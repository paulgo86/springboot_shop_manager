package shopManager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setDefaultEncoding("UTF-8");
		commonsMultipartResolver.setMaxUploadSize(5 * 1024 * 1024);
		return commonsMultipartResolver;
	}
	
	
	private final String uploadImagePath;
	private final String requestPath;
	
	public WebMvcConfiguration(
			@Value("${custom.path.location}") String uploadImagePath,
			@Value("${custom.path.uri_path}") String requestPath
			) {
		this.uploadImagePath = uploadImagePath;
		this.requestPath = requestPath;
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(requestPath)
			.addResourceLocations("file://"+uploadImagePath);
		
	}
}
