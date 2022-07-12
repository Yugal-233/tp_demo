package com.example.restapi.user;

import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userDaoService;
	
	@GetMapping("/users")
	public List<User> findAllUsers(){
		return userDaoService.findAllUser();
	}
	
	@GetMapping("users/{id}")
	public EntityModel<User> retriveUserById(@PathVariable int id) throws UserNotFound {
		
		User user= userDaoService.findUserById(id);
		if(user==null) {
			throw new UserNotFound("ID " +id);
		}
		EntityModel<User> model = EntityModel.of(user);
		
		WebMvcLinkBuilder linkToUser = linkTo(methodOn(this.getClass()).findAllUsers());
		model.add(linkToUser.withRel("all-users"));
		return model;
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		
		User addUser = userDaoService.saveUser(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public void deleteUserById(@PathVariable int id) throws UserNotFound {
		User user = userDaoService.deleteUserById(id);
		if(user==null) {
			throw new UserNotFound("Id :: "+id);
		}
	}
}
