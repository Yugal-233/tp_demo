package com.example.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping("/helloworld")
	public String helloWorld() {
		return "hello-world";
	}
	
	@GetMapping("/helloworldbean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("hello-world-BEan");
	}
	@GetMapping("/helloworldbean/path/{name}")
	public HelloWorldBean helloWorldBeanNAme(@PathVariable String name) {
		return new HelloWorldBean("hello-world-BEan :: "+name);
	}
	
	@GetMapping("/helloworldInternanationalize")
	public String helloWorldInternanationalize() {
		return "hello-world-intern";
	}

}
