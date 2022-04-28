package poit.boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HelloController {

	// @GetMapping("/greetings")
	// public String index() {
	// 	System.out.println("we went to /greetings!");
	// 	return "<h1>Greetings from Spring Boot!</h1>";
	// }

	@GetMapping("/home")
	public String poemForm(Model model){
		model.addAttribute("testingForm", new TestingForm());
		return "home";
	}

	@PostMapping("/home")
	public String poemSubmit(@ModelAttribute TestingForm testingForm, Model model){

		//Initialize a PoemFactory, and a poem for the built poem to live. 
		PoemFactory poem = new PoemFactory();
		Poem newPoem;

		//Pass the recieved text (from our adaptor testingForm) to Kai's build poem function?
		newPoem = poem.buildPoem(testingForm.getPoem());

		//Reset the adaptors poem text to the new poem for output testing?
		testingForm.setPoem(newPoem.poemText);

		//Seems that addAttribute is essentialy "pushing"? 
		//And we can use the simple "testingForm" as a way for Poem to communicate through Thymeleaf
		//because I know it works. Call testingForm an adaptor lol. 
		model.addAttribute("testingForm", testingForm);
		return "result";
	}

	// Could use this "PoemPage" as a template for Kai's real poem object. 
	// But not being used right now. 
	@GetMapping("/poemPage")
	public String realPoemForm(Model model){
		model.addAttribute("poemForm", new TestingForm());
		return "poemPage";
	}

	@PostMapping("/poemPage")
	public String realPoemSubmit(Model model){
		model.addAttribute("poemForm", new TestingForm());
		return "poemPage";
	}

}