package coding.toast.springweblegacy.config.web;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.nio.charset.StandardCharsets;

// If You use "WebMvcConfigurer" then add "@EnableWebMvc" annotation on top of the class!
// example:
// @EnableWebMvc public class WebConfiguration implements WebMvcConfigurer {

// If you use "WebMvcConfigurationSupport", then don't add "@EnableWebMvc" annotation Like Below!
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/favicon.ico")
			.addResourceLocations("classpath:static/favicon.ico");
		
		registry.addResourceHandler("/static/**")
			.addResourceLocations("classpath:static/");
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		StandardServletMultipartResolver standardServletMultipartResolver = new StandardServletMultipartResolver();
		standardServletMultipartResolver.setResolveLazily(true);
		standardServletMultipartResolver.setStrictServletCompliance(true);
		return standardServletMultipartResolver;
	}
	
	
	// https://www.thymeleaf.org/doc/tutorials/3.1/thymeleafspring.html
	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setPrefix("classpath:templates/");
		resolver.setSuffix(".html");
		//resolver.setCacheable(false); // For HotSwap + Refreshing Thymeleaf HTML, uncomment this line.
		return resolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine(SpringResourceTemplateResolver templateResolver) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

		// the config below is for applying [nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect] layout library.
		templateEngine.addDialect(new LayoutDialect());
		return templateEngine;
	}
	
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine) {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine);
		resolver.setContentType(MediaType.TEXT_HTML_VALUE);
		resolver.setCharacterEncoding(StandardCharsets.UTF_8.toString());
		return resolver;
	}
}
