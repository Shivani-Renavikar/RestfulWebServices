package com.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.servlet.Servlet;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	@Autowired
	private UserDaoService userDaoService;

	@GetMapping("/users")
	public List<User> retriveAllUsers() {

		return userDaoService.findAll();

	}

	@GetMapping("/users/{id}")
	public EntityModel<User> retriveUserById(@PathVariable String id) {

		User userById = userDaoService.findUserById(id);

		if (userById == null) {
			throw new UserNotFoundException("Id : " + id);
		}

		EntityModel<User> entityModel = EntityModel.of(userById);
		WebMvcLinkBuilder webMvcLinkBuilder = linkTo(methodOn(this.getClass()).retriveAllUsers());

		entityModel.add(webMvcLinkBuilder.withRel("all-users"));
		return entityModel;

	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.saveUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userDaoService.deleteUserById(id);
	}

}
