# Spring4 MVC example application

## 1. Overview
This project is Maven project of Web application example using Spring Framework.

## 2. Development Environment
This project is under development in the following environment.

* IDE
  - Eclipse 4.6 Neon
  - Spring Tool Suite(STS) 3.8.4 (Eclipse plugin)
  - Lombok 1.16.12 (installed in Eclipse)
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

## 4. Note
In order to run this application, the following settings are required.

### 4.1. Executive environment
Install the following software.

* MySQL
* Lombok (Development environment only. Required)
* Spring Tool Suite (Development environment only. If necessary)

### 4.2. Create database
Execute the following command to create a database in MySQL.

  ```
  mysql -u [user] -p[password] < [project root]/data/db/spring4example.sql
  ```

### 4.3. Change application config
Change the parameter of the configuration file application.properties.

| Paramater Name | Description                                        |
|:---------------|:---------------------------------------------------|
| app.datadir    | Files storage location (log, temporary file, etc.) |
| jdbc.url       | Database URL                                       |
| jdbc.username  | Database user name                                 |
| jdbc.password  | Database password                                  |
| javax.mail.\*  | E\-mail config                                     |
