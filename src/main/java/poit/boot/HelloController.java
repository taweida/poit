package poit.boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HelloController {

	@GetMapping("/greetings")
	public String index() {
		System.out.println("we went to /greetings!");
		return "<h1>Greetings from Spring Boot!</h1>";
	}

	@GetMapping("/home")
	public String poemForm(Model model){
		model.addAttribute("testingForm", new TestingForm());
		return "home";
	}

	@PostMapping("/home")
	public String poemSubmit(@ModelAttribute TestingForm testingForm, Model model){
		model.addAttribute("testingForm", testingForm);
		return "result";
	}

}