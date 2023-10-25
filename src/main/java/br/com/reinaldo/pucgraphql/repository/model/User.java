package br.com.reinaldo.pucgraphql.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;

@Entity(name = "Users")
public class User {
  @Id
  @GeneratedValue
  private Integer id;
  private String name;
  @Column(unique = true)
  private String email;

  public User() {
  }

  public User(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
