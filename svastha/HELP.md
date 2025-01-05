# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.13/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.13/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.13/reference/htmlsingle/#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* Set up MySQL and configure it using the application.properties (username, password).

### Directory Setup (Local File Storage)
* To set up the required local file storage structure for application, run the following commands:

```bash

mkdir "C:\Users\smsan\Documents\work"
mkdir "C:\Users\smsan\Documents\work\images"
mkdir "C:\Users\smsan\Documents\work\images\images"
```
* These directories will be used for file uploads within the application.


### Code Formatting
To check formatting, run:
```bash
mvn spotless:check
```
To format code, run:

```bash
mvn spotless:apply
```