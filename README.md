# Employee Management System

A web-based Java application for managing employee records with full CRUD operations. It includes a responsive UI, RESTful API, and is built using Java Servlets, JSP, MySQL, Maven, and deployed on Apache Tomcat.

## 🚀 Features

- Add, Update, Delete, and View employee records
- RESTful API for JSON access
- Client-side validation using JavaScript
- Responsive UI with HTML/CSS
- Maven-based dependency and build management
- Organized with Git (separate `frontend` and `backend` branches)

---

## 🛠️ Technologies Used

| Layer       | Technology                                 |
|-------------|---------------------------------------------|
| Back-End    | Java Servlets, MySQL                        |
| Front-End   | JSP, HTML, CSS (`styles.css`), JavaScript (`main.js`) |
| Build Tool  | Maven                                       |
| Server      | Apache Tomcat                              |
| Versioning  | Git, GitHub                                 |

---

## 📦 Setup Instructions

### ✅ Prerequisites

- Java JDK 11+
- Apache Maven 3.8+
- Apache Tomcat 9+
- MySQL 8.0+
- Git

### 🧭 Steps

#### 1. Clone Repository

```bash
git clone https://github.com/ChetanGowda-27/employee-management-system.git
cd employee-management-system
````

#### 2. Set Up MySQL Database

```sql
CREATE DATABASE employee_db;
USE employee_db;

CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    salary DOUBLE NOT NULL
);

INSERT INTO employees (name, salary) 
VALUES ('John Doe', 50000.00), ('Jane Smith', 60000.00);
```

> 💡 Update `src/main/java/com/chetangowda/util/DBUtil.java` with your MySQL username and password.

#### 3. Build the Project

```bash
mvn clean package
```

#### 4. Deploy to Tomcat

* Copy `target/employee-management-system.war` to `<TOMCAT_HOME>/webapps/`
* Start Tomcat:

```bash
<TOMCAT_HOME>/bin/startup.sh
```

#### 5. Access Application

[http://localhost:8080/employee-management-system/employees](http://localhost:8080/employee-management-system/employees)

---

## 🔄 Test REST API

Use Postman or `curl`:

* **GET** all employees:

  ```bash
  curl http://localhost:8080/employee-management-system/employees -H "Accept: application/json"
  ```

* **POST** a new employee:

  ```bash
  curl -X POST http://localhost:8080/employee-management-system/employees -d "name=New Employee&salary=45000"
  ```

---

## 🖼️ Screenshots (Coming Soon)

* 📋 Employee List
* ✏️ Edit Employee

---

## 📁 Project Structure (Key Files)

```
employee-management-system/
├── pom.xml
├── src/main/
│   ├── java/com/chetangowda/
│   │   ├── EmployeeServlet.java
│   │   ├── model/Employee.java
│   │   └── util/DBUtil.java
│   └── webapp/
│       ├── employees.jsp
│       ├── edit-employee.jsp
│       ├── css/styles.css
│       └── js/main.js
└── README.md
```

---

## 🔮 Future Improvements

* Add user login and authentication
* Implement pagination for large datasets
* Use JWT for secure API access
* Enhance UI with client-side filtering/sorting

---


## ⚖️ License

This project is licensed under the [MIT License](LICENSE).

---

## 👨‍💻 Author

**Chetan Gowda**
🔗 [GitHub Profile](https://github.com/ChetanGowda-27)

---

