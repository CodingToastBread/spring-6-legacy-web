package coding.toast.springweblegacy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackages = "coding.toast.springweblegacy"
	// actually, you don't have to exclude this class.
	// spring is smart enough to not register GlobalBeanScanner type Bean TWICE.
	, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, value = GlobalBeanScanner.class)
)
public class GlobalBeanScanner {
}
