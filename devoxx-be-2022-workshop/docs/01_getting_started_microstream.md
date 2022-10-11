# Delivering ultra-fast persistence with well-designed Java microservices

## Introduction

### What you will learn

Here are the key learnings you will get with this hands-on exercise:

* Learn how Microprofile empowers Java developers to quickly build and deliver microservices. 

    !!! INFO
    
        The exercises are built on Helidon as the runtime, although other MicroProfile runtimes can be used to implement the same examples. E.g. WildFly, Open Liberty, Payara.

* Explore the basic project structure of a MicroProfile-based Java microservice;
* Understand how architectural best practices allows the implementation decoupled code - domain logic and technical details are not tightened together;
* Learn how to add in-memory Java-native persistence;
* Know the capabilities that results from usage of specifications such as MicroProfile Config, CDI, JAX-RS and more;
* Experience the benefits of using a Java native persistence solution, such as easy data model mapping and manipulation;

## Pre requisites

To be able to go through this guided exercise, you will need to have the following components in your dev environment:

* Have finished the steps described in [Preparing your environment](00_environment.md), especially the section _[Prepare-your-working-directory](00_environment.md#prepare-your-working-directory)_.

## Hands-on practice

### Getting familiar with the labs' projects 

The projects have the following characteristics:

  | Info      | Details                          |
  | ----------| -----------------------------------|
  | MicroProfile Version    | 3.3 |
  | Build Tool | Maven | 
  | MicroProfile Runtime | Helidon |
   
The project's structures are similar to:
   ```shell
   .
   ├── pom.xml
   ├── readme.md
   └── src
   └── main
   ├── java
   │   └── org
   │       └── a4j
   │        └── ...(packages and classes)
   │           └── resources
   │               └── META-INF
   │                   ├── beans.xml
   │                   └── microprofile-config.properties
   └── pom.xml
   ```

### Running the project

5. Open the terminal and access the project's folder. 

6. Run the following maven command. Maven will download the dependencies described in the project's `pom.xml` file. It will also create an executable jar we can use to access our application: `hello-payara-microbundle.jar`.
   ```shell
   mvn clean package
   ```

7. Now, let's start our first Helidon service. In the terminal, run the following command:
   ```bash
   java -jar target/hello-helidon.jar
   ```

8. If everything goes well, you should see an output similar to:
   ```
    INFO io.helidon.microprofile.server.ServerCdiExtension !thread!: Server started on http://localhost:8080 (and all other host addresses) in 2319 milliseconds (since JVM startup).
    INFO io.helidon.common.HelidonFeatures !thread!: Helidon MP 2.4.1 features: [CDI, Config, Fault Tolerance, Health, JAX-RS, Metrics, Open API, REST Client, Security, Server, Tracing]
   
   ```

9. In your browser, access the page [http://localhost:8080/data/hello](http://localhost:8080/data/hello)

    !!! question "What are the classes that are responding to this request?" 

10. Now change the message that is displayed in this page. 
11. Package your application, start it again, and check the changes on the page. 

!!! success "Congratulations"
    You've successfully created, packaged, changed and accessed an application based on the MicroProfile specification and that runs with the Helidon runtime!

### Going further

There are several cool capabilities - such as native compilation and the Helidon dev loop - that you can further explore. To know more about these topics, refer to the [Learn More](more/learn_more.
md) page.

## 
