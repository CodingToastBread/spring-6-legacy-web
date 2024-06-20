package coding.toast.springweblegacy.welcome;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	@GetMapping(value = "/", produces = {MediaType.TEXT_HTML_VALUE})
	public String welcomePage(Model model) {
		model.addAttribute("helloMessage1", "Welcome To My Simple Web Page");
		model.addAttribute("helloMessage2", "Content Table is Like Below");
		return "index";
	}
}
