# Restaurant
Restaurant web application on Spring

## Technologies
+ Java Spring
+ Java Spring MVC
+ Java Spring Data JPA
+ Hibernate
+ Thymeleaf
+ PostgreSQL
+ Gradle

## Launch
1. Download Apache Tomcat
2. Create a database called ‘restaurant’
3. Create tables from the sql/restaurant.sql file
4. Import data from sql/*.csv files into database tables
5. Build a project in command line: ```.\gradlew.bat :war```
6. Place build/libs/Restaurant.war in the Tomcat /webapp folder.
7. Run Tomcat: ```.\bin\startup.bat``` from root Tomcat dir
8. Open http://localhost:8080/Restaurant/home in your browser

