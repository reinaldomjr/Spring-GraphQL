
# TODO List GraphQL

---

Run the application using the command:
```bash
mvn spring-boot:run
```

Open the browser and access the address:

http://localhost:8080/graphiql

If you want to access the Memory database, use the address:

http://localhost:8080/h2-console

Use the following configurations to connect:
```properties
Driver_Class: org.h2.Driver
JDBC_URL: jdbc:h2:mem:tasksDB
UserName: sa
Password: password
```

 
# GraphQL API

---
The graphQL api provides the following queries:
### Users:

- Create User:
```
mutation CreateUser {
  createUser(email: "", name: "") {
    name
    id
    email
  }
}
```
- List Users
```
query ListUsers {
  users {
    id
    email
    name
  }
}
```
- User By Id
```
query UserById {
  userById(id: 1) {
    id
    name
    email
  }
}
```
- User By Email
```
query UserByEmail {
  userByEmail(email: "reinaldo@email.com") {
    id
    name
    email
  }
}
```
- Update User
```
mutation UpdateUser {
  updateUser(id:1, name: "Aderbal") {
    id
    name
    email
  }
}
```
- Delete User
```
mutation DeleteUser {
  deleteUser(id: 1) {
    id
    name
    email
  }
}
```
### Tasks:
- Create Task
```
mutation CreateTask {
  createTask(title: "Task Title", description: "Task Description", userId: 1) {
    id
    title
    description
    status
    user{
      id
      name
    }
  }
}
```
- Start Task
```
mutation StartTask {
  startTask(id: 1) {
    id
    title
    status
  }
}
```
- Complete Task
```
mutation CompleteTask {
  completeTask(id: 1) {
    id
    title
    status
    user {
      id
      name
    }
  }
}
```
- List Tasks
```
query ListTasks {
  tasks {
    id
    title
    status
  }
}
```
- Task By Id
```
query TaskById {
  taskById(id: 1) {
    id
    title
    description
    status
    user {
      id
      name
    }
  }
}
```
- List Tasks By Status
```
query TaskByStatus {
  tasksByStatus(status: TODO) {
    id
    title
    status
    user {
      id
    }
  }
}
```
- List Tasks By User Id
```
query TaskByUserId {
  tasksByUserId(userId: 2) {
    id
    title
    status
    user {
      id
      name
    }
  }
}
```
- List Tasks By User Id And Status
```
query TaskByUserIdAndStatus {
  tasksByUserIdAndStatus(userId: 2, status: TODO) {
  	id
    title
    status
    user {
      id
      name
    }
  }
}
```