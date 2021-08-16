# Spring MVC example application
\* [Japanese version](/README.ja.md)

## 1. Overview
This project is Maven project of Web application example using Spring Framework.

## 2. Implementation
The following functions were implemented as an implementation example.

* form validation
* file upload and download
* DB CRUD
* authentication and authorization
* AOP (transaction configuration and logging)
* REST API
* sending an email using asynchronous processing
* resource access using webjars
* exception handling
* internationalization
* task scheduler

## 3. Development environment
To run this application, please follow the steps below.

1. Install the following items.
	* JDK 11 (or later)
	* IDEs such as Eclipse and IntelliJ (require Lombok and Maven plugins)
	* MySQL 8.0
	* Tomcat 9 (or later)

2. Check out this source project on the IDE.

3. Change the following parameters of the config file [application.properties](/src/main/resources/application.properties) as necessary.

| Paramater Name      | Description                                        |
|:--------------------|:---------------------------------------------------|
| application.datadir | files storage location (log, temporary file, etc.) |
| jdbc.host           | database host                                      |
| jdbc.database       | database name                                      |
| jdbc.username       | database user name                                 |
| jdbc.password       | database password                                  |
| javax.mail.\*       | e\-mail config                                     |

4. Create database and user in MySQL according to parameters in [application.properties](/src/main/resources/application.properties).

5. (Windows) Create tables in MySQL by running the batch file [run_sql_files.bat](/data/db/run_sql_files.bat)\*\.  
   (other) Create tables in MySQL by running SQL files under [db](/data/db) directory.  
   \*[run_sql_files.bat](/data/db/run_sql_files.bat) references [application.properties](/src/main/resources/application.properties) to connect to the database.

6. Deploy this source project to Tomcat.
