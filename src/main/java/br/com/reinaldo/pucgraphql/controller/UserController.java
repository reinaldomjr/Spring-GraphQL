package br.com.reinaldo.pucgraphql.controller;

import br.com.reinaldo.pucgraphql.exceptions.EntityNotFound;
import br.com.reinaldo.pucgraphql.repository.UserRepository;
import br.com.reinaldo.pucgraphql.repository.model.User;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

  final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @QueryMapping
  public List<User> users() {
    return userRepository.findAll();
  }

  @QueryMapping
  public User userById(@Argument Integer id) {
    return userRepository.findById(id).orElse(null);
  }

  @QueryMapping
  public User userByEmail(@Argument String email) {
    return userRepository.findByEmail(email);
  }

  @MutationMapping
  public User createUser(@Argument String name, @Argument String email) {
    User newUser = new User(name, email);
    return userRepository.save(newUser);
  }

  @MutationMapping
  public User updateUser(@Argument Integer id, @Argument String name, @Argument String email) {
    User user = userRepository.findById(id).orElse(null);
    if (user != null) {
      if(name != null) {
        user.setName(name);
      }
      if(email != null) {
        user.setEmail(email);
      }
      return userRepository.save(user);
    }
    throw new EntityNotFound(id, User.class);
  }

  @MutationMapping
  public User deleteUser(@Argument Integer id) {
    User user = userRepository.findById(id).orElse(null);
    if (user != null) {
      userRepository.delete(user);
      return user;
    }
    throw new EntityNotFound(id, User.class);
  }
}
