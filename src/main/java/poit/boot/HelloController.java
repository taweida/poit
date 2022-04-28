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

	// Here we esentially build our testingForm, which I'm kind of calling an adaptor. 
	// And it links up to the form through Thymeleaf...
	@GetMapping("/home")
	public String poemForm(Model model){
		model.addAttribute("testingForm", new TestingForm());
		return "home";
	}

	//Here, once the sumbit button is clicked, the "testingForm adaptor" is populated
	//with the user's input from the respective form.
	// Then we pass the values of our testingForm object, the Poem Factory.
	@PostMapping("/home")
	public String poemSubmit(@ModelAttribute TestingForm testingForm, Model model){

		//Initialize a PoemFactory, and a poem for the built poem to live. 
		PoemFactory factory = new PoemFactory();
		Poem poem;

		switch (testingForm.getStyle()) {
			case "Haiku":

			break;

			case "Shakespeare":

			break;

			case "Limerick":
				
			break;
		}

		//Pass the recieved text (from our adaptor testingForm) to Kai's build poem function?
		poem = factory.buildPoem(testingForm.getPoem());

		//Reset the adaptors poem text to the new poem for output testing?
		testingForm.setPoem(poem.getPoemText());

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