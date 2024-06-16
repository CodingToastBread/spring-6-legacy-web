package coding.toast.springweblegacy.welcome;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	@GetMapping(value = "/", produces = {MediaType.TEXT_HTML_VALUE})
	public String welcomePage() {
		return "index";
	}
}
