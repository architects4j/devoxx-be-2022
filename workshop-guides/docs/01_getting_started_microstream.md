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

### Getting started with MicroProfile
#### Explore the project 

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

#### Package and run 

5. Open the terminal and access the project's folder: `devoxx-be-2022/workshop-foundation/microprofile/` 

6. Run the following maven command. Maven will download the dependencies described in the project's `pom.xml` file. It will also create an executable jar we can use to access our application:`team.jar`.
   ```shell
   mvn clean package
   ```

7. Now, let's start the microservice. In the terminal, run the following command:
   ```bash
   java -jar target/team.jar
   ```

8. If everything goes well, you should see an output similar to:
   ```bash
2022.10.11 02:34:00 INFO io.helidon.microprofile.server.ServerCdiExtension !thread!: Server started on http://localhost:8080 (and all other host addresses) in 2277 milliseconds (since JVM startup).
2022.10.11 02:34:00 INFO io.helidon.common.HelidonFeatures !thread!: Helidon MP 2.5.4 features:[CDI, Config, Fault Tolerance, Health, JAX-RS, Metrics, Open API, REST Client, Security, Server, Tracing] 
    ```

9. In your browser, access the page [http://localhost:8080/data/hello](http://localhost:8080/data/hello)

    !!! question "What are the classes that are responding to this request?" 

10. Now change the message that is displayed in this page to **"Hello Devoxx Belgium!!!"** .
    
    ??? Tip
    
        In the class `org.a4j.workshop.HelloController`, you'll find a GET endpoint that prints the message you saw in your browser. If you update it, package and run the application you should see 
        the changes reflected on the page. 
        
        When using helidon CLI and the Dev Loop, you should be able to visualize the change without the extra effort of manually re-packaging and running the service. 

11. Package your application, start it again, and check the changes on the page. 

    ??? Tip

        Maven helps you packaging the application, and you can use the JDK installed locally to execute the generated file:
        ```shell
        mvn clean package
        java -jar target/team.jar
        ```

!!! success "Congratulations"
    You've successfully created, packaged, changed and accessed an application based on the MicroProfile specification and that runs with the Helidon runtime!

#### Going further

There are several cool capabilities - such as native compilation and the Helidon dev loop - that you can further explore. To know more about these topics, refer to the [Learn More](more/learn_more.
md) page.

### MicroStream: using ultra-fast persistence

The project contains a data model and the respective classes required to manipulate these data:
* Data model: composed of the `Player`, `Position` and `Team` classes. 
* Service classes: the layer that holds business rules. The `TeamService` is part of it.
* Endpoints: the REST API is exposed through the resource class, in this case, `TeamResource`.

Feel free to explore these classes before moving forward.

#### Add MicroStream to the project 

1. To add MicroStream as the persistence solution of this microservice, we must add it as a maven dependency, so it gets included as a library when this project is build. Open the `pom.xml` and 
   add the following dependency:
```xml
        <dependency>
            <groupId>one.microstream</groupId>
            <artifactId>microstream-integrations-cdi</artifactId>
            <version>07.00.00-MS-GA</version>
        </dependency>
```

2. Next, we'll add Microstream's configuration. It relies on MicroProfile Config and has a very flexible and easy setup. Open `src/main/resources/META-INF/microprofile-config.properties`:
    ```properties
    one.microstream.storage.directory=target/data
    one.microstream.channel.count=4 
    ```

    ??? Tip

        The property `one.microstream.storage.directory` indicates the folder where any data will be persisted. Filesystem is the default persistent storage target, although, it is not recommended
        for production. To learn about other storage targets check out the [MicroStream documentation](https://docs.microstream.one/manual/storage/storage-targets/index.html)

#### Data mapping and storage configuration

3. **Entity configurations**:
   
    Open the `Player` class. In the JPA world, this would be considered an entity bean and would be mapped with @Entity`. With MicroStream, there is no need to annotate classes and map them to 
      an existing database. 

    If we were using JPA, we would also have to set the `@Id` and `@Column` in the attributes of the class. 

    _Good news_: nothing needs to be changed on the data model! 

4. **Root and Storage:** manipulating data gets very straightforward with MicroStream. The objects are serialized and saved in a graph structure, which means you don't have to worry with all the 
   complex relations between classes. The key is to understand what is going to be your `root` node. 

     In our scenario, we have a team of players. In this case, we do not want to allow the user to manipulate players as an independent model, instead, every player is part of a team - in other 
   words, it belongs to an object graph in which the `root` is the team. 

    Once you identify what is going to be the `root` class in your scenario, you then know which class you should configure with the `@Storage` annotation. Once you add the root node as a 
   `@Storage`, CDI will take care of a lot of work under the covers. You won't have to deal with transaction managers, persistence context and so on. As of now, let's configure the `Team` class as 
   our `root`: 

    4.1. Open the `org.a4j.workshop.Team` class, and annotate it with `@Storage` :

    ```java
    @Storage
    public class Team {
    ```

Next, you'll work on the methods that manipulates the data inside our service in-memory persistence storage.

#### Data manipulation

---- wip refer to the comments in the code 

#### Methods that changes persisted data

 --- wip: @store in class teamservice

### Testing the service

* By default it will run on port 8081, as configured in the `microprofile-config.properties`.
* Check the service implementation with the following request examples:
----- wip: atualizar os requests
    * **Insert** a new product
    ```shell
    ```
    * **List** all products
    ```shell
    
    ```
    * **Search** a product by ID (name)
    ```shell
    
    ```
    * **Update** a product using its ID (name)
    ```shell

    ```
    * **Delete** a product by ID (name)
    ```shell
    ```

* If you can successfully execute the above items, it means you acomplished this section's goals!

!!! success "Congratulations!"

    You've finished your task! You have easily modified an existing project by taking advantadge of a well-defined persistence layer. You have used MicroStream to allow this service to persist its 
    data using an ultra-fast in-memory solution. Well done!
