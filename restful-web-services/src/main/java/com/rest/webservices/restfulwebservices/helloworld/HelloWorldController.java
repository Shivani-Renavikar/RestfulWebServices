package com.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	 @Autowired
	private MessageSource messageSource;

//	public HelloWorldController(MessageSource messageSource) {
//		super();
//		this.messageSource = messageSource;
//	}

	// @RequestMapping(path = "/hello-world", method = RequestMethod.GET)
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping(path = "/hello-world-Bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World!!");
	}

	@GetMapping(path = "/hello-world-Bean/path-variable/{name}")
	public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name) {
		return new HelloWorldBean("Hello World!! " + name);
	}

	@GetMapping(path = "/hello-world-Internalization")
	public String helloWorldInternalization() {

		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default message", locale);
		// return "Hello World Internalization";
	}

}
