# Spring4 MVC example application
\* [Japanese version](/README.ja.md)

## 1. Overview
This project is Maven project of Web application example using Spring Framework.

## 2. Development Environment
This project is under development in the following environment.

* IDE
  - Eclipse 4.6 Neon
  - Spring Tool Suite (STS) 3.8.4 (Eclipse plugin)
  - Lombok 1.16.12
* DB
  - MySQL 5.6

## 3. Functions
This application has the following functions.

* Basic functions
  - Authentication / Authorization
  - Validation
  - File upload and download
  - DB-CRUD
  - Static resource reference (Using Webjars)
  - Exception handling
  - Internationalization
* AOP (Transaction setting, logging)
* REST API (text, XML, JSON)
* Asynchronous process (Send e-mail)
* Scheduled task (DB registration of task execution history)

## 4. Execution method
To execute this application, please follow the procedure below.

### 4.1. Testing environment
See [provisioning-environment-for-tomcat8](https://github.com/d-saitou/provisioning-environment-for-tomcat8).

### 4.2. Development environment
Please follow the procedure below.

1. Install the following software.
	* [Eclipse](https://www.eclipse.org/) \* [Pleiades All in One](http://mergedoc.osdn.jp/) is also acceptable.
	* [MySQL 5.6 (or later)](https://www.mysql.com/)
	* [Tomcat 8 (or later)](http://tomcat.apache.org/) \* Link with Eclipse.


2. Install the following plugin in Eclipse.
	* Spring Tool Suite (STS)
	* [Lombok](https://projectlombok.org/) \* Refer to the link for the installation procedure.


3. Check out this source project on Eclipse.

4. Execute the following command to create a database in MySQL.

  ```
  mysql -u {MySQL user} -p{password} < {this source project root}/data/db/spring4example.sql
  ```

6. Change the following parameters of the config file [web.xml](/src/main/webapp/WEB-INF/web.xml) as necessary.

| Paramater Name                                               | Description                      |
|:-------------------------------------------------------------|:---------------------------------|
| &lt;servlet&gt;\-&lt;multipart\-config&gt;\-&lt;location&gt; | Temporary files storage location |

6. Change the following parameters of the config file [application.properties](/src/main/resources/application.properties) as necessary.

| Paramater Name | Description                                        |
|:---------------|:---------------------------------------------------|
| app.datadir    | Files storage location (log, temporary file, etc.) |
| jdbc.url       | Database URL                                       |
| jdbc.username  | Database user name                                 |
| jdbc.password  | Database password                                  |
| javax.mail.\*  | E\-mail config                                     |

7. Configure Eclipse to run this source project in Tomcat.
