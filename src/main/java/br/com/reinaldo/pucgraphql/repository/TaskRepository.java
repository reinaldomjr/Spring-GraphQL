package br.com.reinaldo.pucgraphql.repository;

import br.com.reinaldo.pucgraphql.repository.model.Status;
import br.com.reinaldo.pucgraphql.repository.model.Task;
import br.com.reinaldo.pucgraphql.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

  List<Task> findByUserId(Integer userId);
  List<Task> findByUserIdAndStatus(Integer userId, Status status);
  List<Task> findByStatus(Status status);
}
