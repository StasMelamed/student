package telran.java51.simplewebservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleWebServiceController {

	@GetMapping("/hello")
	public String hello(@RequestParam("title") String name) {
		
		return "Hello "+name+"!";
	}
	
	@PostMapping("/hello")
	public String getPostData(@RequestBody PersonDto person) {
		
		return "Hello "+person.getName()+" "+person.getLastName();
	}
	
	@PostMapping("/food")
	public PersonFoodDto personFood(@RequestBody PersonDto person) {
		
		return PersonFoodDto.builder()
				.fullName(person.getName() +" " +person.getLastName())
				.food("halva")
				.food("shaverma")
				.food("coffee")
				.build();
	}
	
}
