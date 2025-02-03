1.docker pull mysql
 
2.docker run -p 3308:3306 --name mysqllcontainer -e MYSQL_ROOT_PASSWORD=apulapa -e MYSQL_USER=apulapa -e MYSQL_PASSWORD=apulapa -e MYSQL_DATABASE=users -d mysql

3.docker build -t springboot-app . 

4.docker run -p 8090:8080 --name springcontainer -e MYSQL_HOST=mysqllcontainer -e MYSQL_PORT=3306 -e MYSQL_DB_NAME=users -e MYSQL_USER=apulapa -e MYSQL_PASSWORD=apulapa springboot-app

spring.application.name=springboot-crud-docker
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:${MYSQL_PORT:3306}/${MYSQL_DB_NAME:sb}
spring.datasource.username=${MYSQL_USER:root}
spring.datasource.password=${MYSQL_PASSWORD:root}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect 


MYSQL_ROOT_PASSWORD: Sets the root password for the MySQL instance.
MYSQL_DATABASE: Creates a database named users.
MYSQL_USER: Creates a new user with the specified username (in this case, apulapa).
MYSQL_PASSWORD: Sets the password for the user specified in MYSQL_USER.

Yes, setting both MYSQL_ROOT_PASSWORD and MYSQL_PASSWORD serves different purposes in MySQL's configuration:

MYSQL_ROOT_PASSWORD: This is the password for the root user, which is the superuser account in MySQL. The root user has full privileges on all databases within the MySQL instance and can manage other users, databases, and settings. Setting this password is essential for database security.

MYSQL_PASSWORD (paired with MYSQL_USER): This sets the password for a regular user created alongside the root user. The MYSQL_USER environment variable specifies the name of this regular user, and MYSQL_PASSWORD defines the password for it. This user typically has limited privileges, often restricted to specific databases or tables.
