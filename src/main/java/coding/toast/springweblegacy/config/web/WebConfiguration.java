package coding.toast.springweblegacy.config.web;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
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
	
	// resource handling
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/favicon.ico")
			.addResourceLocations("classpath:static/favicon.ico");

		registry.addResourceHandler("/js/**")
				.addResourceLocations("classpath:static/js/");

		registry.addResourceHandler("/img/**")
				.addResourceLocations("classpath:static/img/");

		registry.addResourceHandler("/css/**")
				.addResourceLocations("classpath:static/css/");

		registry.addResourceHandler("/etc/**")
				.addResourceLocations("classpath:static/etc/");
	}
	
	// file upload handling
	@Bean
	public MultipartResolver multipartResolver() {
		StandardServletMultipartResolver standardServletMultipartResolver = new StandardServletMultipartResolver();
		standardServletMultipartResolver.setResolveLazily(true);
		standardServletMultipartResolver.setStrictServletCompliance(true);
		return standardServletMultipartResolver;
	}
	
	
	// template engine config (1)
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setPrefix("classpath:templates/");
		resolver.setSuffix(".html");
		//resolver.setCacheable(false); // For HotSwap + Refreshing Thymeleaf HTML, uncomment this line.
		return resolver;
	}
	
	// template engine config (2)
	@Bean
	public SpringTemplateEngine templateEngine(SpringResourceTemplateResolver templateResolver) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

		// the config below is for applying [nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect] layout library.
		templateEngine.addDialect(new LayoutDialect());
		return templateEngine;
	}
	
	// template engine config (3)
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine) {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine);
		resolver.setContentType(MediaType.TEXT_HTML_VALUE);
		resolver.setCharacterEncoding(StandardCharsets.UTF_8.toString());
		return resolver;
	}
	
	// add messageSource
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:/message/global-message");
		messageSource.setCacheSeconds(60);
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.toString());
		messageSource.setFallbackToSystemLocale(false);
		return messageSource;
	}
	
	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
		validatorFactoryBean.setValidationMessageSource(messageSource());
		return validatorFactoryBean;
	}
	
	/**
	 * this config is very important if you want to use  Hibernate Validator's MessageInterpolator functionality.<br>
	 * you can use this functionality inside ControllerAdvice by calling BindingError's getDefaultMessage() method.<br>
	 * reference code : {@link coding.toast.springweblegacy.user.controller.UserControllerAdvice} here!<br>
	 */
	@Override
	protected org.springframework.validation.Validator getValidator() {
		return validator();
	}
}
