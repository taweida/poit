package poit.boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@RestController
public class HelloController {

	@GetMapping("/greetings")
	public String index() {
		System.out.println("we went to /greetings!");
		return "<h1>Greetings from Spring Boot!</h1>";
	}

	@RequestMapping(value="/clicked")
	public void clicked(){
		System.out.println("Something was clicked!");
	}

	@GetMapping("/home")
	public void poemForm(Model model){
		model.addAttribute(new TestingForm());
	}

	@PostMapping("/home")
	public void poemSubmit(@ModelAttribute TestingForm testingForm, Model model){
		model.addAttribute(testingForm);
	}

}