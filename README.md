# Mini Loan App - Backend
A loan application where a user can signup, login, apply for loan, pay EMIs term, and track their transactions.</br>
RESTful backend server developed in Spring boot enabling users to stores data in a relational database. </br>

# Technologies used
Java 8
Maven
Hibernate
JPA
Spring Boot
MySQL

# Steps to setup the application
Clone the project :- ***$ git clone https://github.com/YogeshGurjar130/MiniLoan.git </br>
Install JDK 8 and MySQL server and workbench in your system. </br>
Inside MySQL workbench, create an schema named ***'mini_loan' </br>
Inside MySQL workbenck, run the given query :- </br>
***$ INSERT INTO `mini_loan`.`user` (`user_id`, `email`, `name`, `password`, `role`) VALUES ('1', 'admin@gmail.com', 'Admin', 'admin', 'admin'); </br>
This query will create the admin user in the database. By login as admin to approve loan. </br>
In Application Inside resource folder check in application.properties </br>
***spring.datasource.username=root </br>
***spring.datasource.password=root </br>
Above properties value should match with your database credentials (username and password) </br>
Run the application as Spring Boot Project. </br>
Application will run on the url: http://localhost:8080/ </br>
