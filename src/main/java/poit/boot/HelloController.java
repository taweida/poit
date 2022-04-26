package poit.boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/greetings")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping(value="/clicked")
	public void clicked(){
		System.out.println("Something was clicked!");
	}


}