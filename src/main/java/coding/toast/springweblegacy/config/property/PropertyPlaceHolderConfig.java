package coding.toast.springweblegacy.config.property;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;

@Configuration(proxyBeanMethods = false)
public class PropertyPlaceHolderConfig {
	
	@Bean
	public PropertySourcesPlaceholderConfigurer placeholderConfigurer(Environment environment) {
		PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		placeholderConfigurer.setFileEncoding(StandardCharsets.UTF_8.toString());
		placeholderConfigurer.setEnvironment(environment);
		placeholderConfigurer.setLocation(new ClassPathResource("placeholder.properties"));
		return placeholderConfigurer;
	}
}
