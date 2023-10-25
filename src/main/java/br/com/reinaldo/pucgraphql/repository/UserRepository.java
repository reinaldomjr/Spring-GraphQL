package br.com.reinaldo.pucgraphql.repository;

import br.com.reinaldo.pucgraphql.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  User findByEmail(String email);
}
