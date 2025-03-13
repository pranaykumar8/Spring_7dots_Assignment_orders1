# Spring_7dots_Assignment_orders1

# 🛒 eCommerce Backend (Spring Boot + MySQL)

This is a full-stack eCommerce application built with **Spring Boot (backend)**, and **MySQL (database)**. The backend provides REST APIs for user authentication, order management, and analytics.

---

## 🚀 Features
- 📦 **Product Management**
- 🛍️ **Order Processing & Payments**
- 📊 **Analytics (Profitable Customers, Retention Rate, etc.)**

---

## ⚙️ Tech Stack
- **Backend**: Spring Boot, JPA, MySQL, Java Streams
- **Database**: MySQL
- **Tools**: Postman (API Testing), GitHub, Spring-tool-suite (Backend), Workbench (Database)

---

## 📌 Prerequisites
Make sure you have the following installed:
- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [MySQL](https://dev.mysql.com/downloads/installer/)
- [Postman](https://www.postman.com/)
- [Git](https://git-scm.com/)
- [Maven](https://maven.apache.org/) (Comes with Spring Boot)

---

## 📥 Clone the Repository

git clone https://github.com/your-username/ecommerce-backend.git
cd ecommerce-backend

Steps to Run and Test Your Spring Boot Project
1. Open the Project in Spring Tool Suite (STS)
Open STS (Spring Tool Suite).
Click on File → Import.
Select Maven → Existing Maven Projects and click Next.
Click Browse, select your Spring Boot project folder, and click Finish.
2. Run the Project in STS
In the Package Explorer, right-click on your project folder.
Click Run As → Spring Boot App.
Wait for the console logs to confirm that the application has started.
3. Test the API in Postman
Open Postman.
Select GET as the request type.
Enter the following URL:
bash

http://localhost:8080/orders/1
Click Send to retrieve the data for order ID 1.
Check the response to verify the data.
4. Verify Data in MySQL Workbench
Open MySQL Workbench and connect to your database.
Run the following SQL query to check the orders table:
sql

SELECT * FROM orders WHERE id = 1;
Verify that the data in MySQL matches the API response from Postman.


Setup MySQL Database
Open MySQL and create a new database:

sql

CREATE DATABASE ecommerce_db;
Update src/main/resources/application.properties:


spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=root
spring.datasource.password=yourpassword
Run the SQL script to insert sample data:

sh

mysql -u root -p ecommerce_db < src/main/resources/sample_data.sql
▶️ Run the Project


mvn spring-boot:run
Once the application starts, the API will be available at:

http://localhost:8080
📡 API Endpoints
Endpoint	Method	Description
/products	GET	Fetch all products
/orders	POST	Place an order
/analytics/profitable-customers	GET	Get most profitable customers
/analytics/monthly-retention	GET	Calculate retention rate
/analytics/top-products	GET	Get top-selling products
/analytics/avg-order-value	GET	Get average order value
/analytics/order-processing-time	GET	Order processing time analysis


