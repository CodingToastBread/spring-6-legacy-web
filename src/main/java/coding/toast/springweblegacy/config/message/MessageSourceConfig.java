package coding.toast.springweblegacy.config.message;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;

@Configuration(proxyBeanMethods = false)
public class MessageSourceConfig {
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:message/global-message");
		messageSource.setCacheSeconds(60);
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.toString());
		messageSource.setFallbackToSystemLocale(false);
		return messageSource;
	}
}
