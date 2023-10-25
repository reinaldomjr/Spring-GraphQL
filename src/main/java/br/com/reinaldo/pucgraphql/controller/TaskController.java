package br.com.reinaldo.pucgraphql.controller;

import br.com.reinaldo.pucgraphql.exceptions.EntityNotFound;
import br.com.reinaldo.pucgraphql.repository.TaskRepository;
import br.com.reinaldo.pucgraphql.repository.UserRepository;
import br.com.reinaldo.pucgraphql.repository.model.Status;
import br.com.reinaldo.pucgraphql.repository.model.Task;
import br.com.reinaldo.pucgraphql.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TaskController {
  final TaskRepository taskRepository;
  final UserRepository userRepository;

  public TaskController(TaskRepository taskRepository, UserRepository userRepository) {
    this.taskRepository = taskRepository;
    this.userRepository = userRepository;
  }
  @QueryMapping
  public List<Task> tasks() {
    return taskRepository.findAll();
  }
  @QueryMapping
  Task taskById(@Argument Integer id) {
    return taskRepository.findById(id).orElse(null);
  }
  @QueryMapping
  List<Task> tasksByStatus(@Argument Status status) {
    return taskRepository.findByStatus(status);
  }
  @QueryMapping
  List<Task> tasksByUserId(@Argument Integer userId) {
    return taskRepository.findByUserId(userId);
  }
  @QueryMapping
  List<Task> tasksByUserIdAndStatus(@Argument Integer userId, @Argument Status status) {
    return taskRepository.findByUserIdAndStatus(userId, status);
  }

  @MutationMapping
  Task createTask(@Argument String title, @Argument String description, @Argument Integer userId) {
    User user = userRepository.findById(userId).orElse(null);
    if(user != null) {
      Task newTask = new Task(title, description, Status.TODO, user);
      return taskRepository.save(newTask);
    }
    throw new EntityNotFound(userId, User.class);
  }
  @MutationMapping
  Task updateTask(@Argument Integer id, @Argument String title, @Argument String description, @Argument Integer userId) {
    Task task = taskRepository.findById(id).orElse(null);
    if(task != null) {
      if(title != null) {
        task.setTitle(title);
      }
      if(description != null) {
        task.setDescription(description);
      }
      if(userId != null) {
        User user = userRepository.findById(userId).orElse(null);
        if(user != null) {
          task.setUser(user);
        } else {
          throw new EntityNotFound(userId, User.class);
        }
      }
      return taskRepository.save(task);
    }
    throw new EntityNotFound(id, Task.class);
  }

  @MutationMapping
  Task deleteTask(@Argument Integer id) {
    Task task = taskRepository.findById(id).orElse(null);
    if(task != null) {
      taskRepository.delete(task);
      return task;
    }
    throw new EntityNotFound(id, Task.class);
  }
  @MutationMapping
  Task completeTask(@Argument Integer id) {
    Task task = taskRepository.findById(id).orElse(null);
    if(task != null) {
      task.setStatus(Status.DONE);
      return taskRepository.save(task);
    }
    throw new EntityNotFound(id, Task.class);
  }
  @MutationMapping
  Task startTask(@Argument Integer id) {
    Task task = taskRepository.findById(id).orElse(null);
    if(task != null) {
      task.setStatus(Status.DOING);
      return taskRepository.save(task);
    }
    throw new EntityNotFound(id, Task.class);
  }
}
