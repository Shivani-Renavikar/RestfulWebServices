package com.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	@GetMapping("/v1/person")
	public PersonV1 getAllPersons() {
		return new PersonV1("Shivani Renavikar");
	}

	@GetMapping("/v2/person")
	public PersonV2 getAllPersonsVersionTwo() {
		return new PersonV2(new Name("Shivani", "Renavikar"));
	}

	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getAllPersonsUsingRequestParam() {
		return new PersonV1("Shivani Renavikar");
	}

	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getAllPersonsVersionTwoUsingRequestParam() {
		return new PersonV2(new Name("Shivani", "Renavikar"));
	}

	@GetMapping(path = "/person/header", headers = "X_API_VERSION=1")
	public PersonV1 getAllPersonsHeader() {
		return new PersonV1("Shivani Renavikar");
	}

	@GetMapping(path = "/person/header", headers = "X_API_VERSION=2")
	public PersonV2 etAllPersonsVersionTwoUsingHeader() {
		return new PersonV2(new Name("Shivani", "Renavikar"));
	}

	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getAllPersonsHeaderUsingProduces() {
		return new PersonV1("Shivani Renavikar");
	}

	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
	public PersonV2 etAllPersonsVersionTwoUsingProduces() {
		return new PersonV2(new Name("Shivani", "Renavikar"));
	}

}
