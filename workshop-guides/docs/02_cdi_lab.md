# Contexts and Dependency Injection (CDI) 

## Introduction

### What you will learn

Here are the main goals you will achieve with this hands-on execise:

* Learn how to use the reference implementation of the CDI specification, [Weld](https://weld.cdi-spec.org/).
* Learn how to get access to the current container in Java SE applications using an important class in the CDI world, `javax.enterprise.inject.se.SeContainer`.
* Try out different scopes and test the injection of different objects and their behavior;
* Practice the concept of observers and event listeners;

### CDI overview

[Contexts and Depedendency Injection (CDI)](http://cdi-spec.org/) is an specification included in Java since version 6. It brings loose coupling between components of the application and makes it easy for developers to manage stateful object instances and connect together different layers of the application. 

When getting started with CDI, there are key concepts developers be aware of: 

* Beans
* Dependency Injection
* Qualifiers
* Scopes
* Interceptors 
* Events. 

???+ Info more about CDI

    For detailed explanation and examples refer to the [CDI's user guides](https://docs.jboss.org/cdi/learn/userguide/CDI-user-guide.html).

The fundamental concept to learn is the **managed bean**. According the the [CDIn's user guides](https://docs.jboss.org/cdi/learn/userguide/CDI-user-guide.html#_what_is_a_bean):

!!! Quote ""
    *"(...) Managed Beans are defined as container-managed objects with minimal programming restrictions, otherwise known by the acronym POJO (Plain Old Java Object). They support a small set of basic services, such as resource injection, lifecycle callbacks and interceptors. (...)"*.

With CDI developers have the possibility to manage stateful components' lifecycle. There are in total five different **scopes** that can be used, being `@Dependent`, `@Conversation`, `@RequestScoped` and `@ApplicationScoped`. See below explanation for the default option, and other two commonly used options:

**The Default Scope**

:   This is the default scope for managed beans in case none is configured. Managed Beans with default scope last the same time as its client bean.

**The request scope**

:   Managed beans configured with `@RequestScoped` will last for an HTTP request lifespan, say, in a web application. 

**The application scope**:

:   Managed beans configured with `@ApplicationScoped` will exist throughout all interactions done with an application, including different users' interaction and requests. 

!!! TIP 
    For more information about application scopes, please refer to Weld - reference implementation of CDI - User Guide: [Weld: Scopes and Contexts](https://docs.jboss.org/weld/reference/3.0.0.Final/en-US/html_single/#scopescontexts) and the [Jakarta EE CDI 3.0 Specification Document](https://jakarta.ee/specifications/cdi/3.0/jakarta-cdi-spec-3.0.html#contexts). 

-----

## Pre requisites

To be able to go through this guided exercise, you will need to have the following components in your dev environment:

* Have finished the steps described in [Preparing your environment](00_environment.md)

## Hands-on practice  

Let's get started with the exercise with a series of steps that will guide you through completing the implementation of a project.

### First steps

1. Inside the cloned repository [helidon-microstream-training-labs-foundation](helidon-microstream-training-labs-foundation), you'll find the project `cdi-lab`. Open the project `cdi-lab` in your IDE of choice. 

2. Open the `pom.xml` file. In the `<dependencies>` section, line 21, add the dependency to Weld. Weld is reference implementation of the CDI specification.
```xml
<dependency>
    <groupId>org.jboss.weld.se</groupId>
    <artifactId>weld-se-shaded</artifactId>
    <version>${weld.se.core.version}</version>
</dependency>
```

4. Notice the version is already configured in `pom.xml`, in the property `weld.se.core.version`.

4. Build and install the application using maven command line or your IDE:
```shell
mvn clean install
```

Your project isn't going to compile yet, due to other unfinished code. Let's move on to the next step.

### Contexts and objects behaviors

Now, let's start practicing with the Vehicle example demonstrated by the instructor. You will finish the project implementation, run it and analise it yourself.

1. Using your IDE of choice, open the Class `App1.java`. In the next steps, we'll update this class where: a new container should be created, and we will use it to inject an instance of an object that inherits the Interface `Vehicle` and an instance of an object based on the class `Car`.

#### Instantiating an SeContainer

   1. Locate in the `App1.java` the line 15. 
     ```Java
             try (SeContainer container = null) {
     ```

   2. This code is incorrect, since container should not be initializated with null. To fix this code, replace the "null" initialization with an intialization done with the class [SeContainerInitializer](https://docs.jboss.org/cdi/api/2.0.EDR2/javax/enterprise/inject/se/SeContainerInitializer.html).
   
    !!! tip 
        To use the `initialize()` method, you should first create a `newInstance()` if the `SeContainerInitializer`.

   3. To make sure the project is compiling, you can run `mvn clean install` without errors.

    !!! tip 
        If you try to run the `main` method in the class `App1` you will get a RuntimeException, `NullPointerException` because there is still code that needs to be initialized correctly.

        Also, if your project was generated with microprofile starter or helidon maven archetype, you might get an `IllegalStateException` if you try to run the `main` method. That is due to the `io.helidon.microprofile.cdi` implementation that is being used. If you use it instead of weld, in order to be able to execute the main method, you need to add the property configuration `mp.initializer.allow=true` to your `microprofile-config.properties`.

#### Obtaining existing object instances in the SeContainer

1. In the class `App1`, locate the object car initialization on line 23. 

```java
    Car car = null;
    car.move();
```

2. An object car may already exist in the container. If so, there is no need to create a new instance of this class. Use the container to obtain the existing car instance (if existing). The great thing about the CDI API is that by default, if no object instance is located in memory, it creates a new instance and retrieves it.

    !!! tip 
        Check how the Vehicle is being obtained from the container. Be sure to obtain an instance of the `Car` class, and not the interface `Vehicle`.

3. Make sure the project is compiling, run `mvn clean install` without errors.

#### **Testing your code:**

1. Now, execute the main method of the class `App1`. 
2. Confirm you see a message output in the logs like: *"Is the same vehicle? true"*

    !!! Question "In this code, we use CDI to obtain an instance of a Vehicle and then, of a Car. Why does the CDI API return the same object?"

3. Checking the **CDI Scope**

   1. Open the class `Car`.
   2. Locate the declaration of this bean's scope, on line 8:

      ```java
      @ApplicationScoped
      public class Car implements Vehicle {
      ```

   3. Comment the annotation `@ApplicationScoped` and save the file. The class should look like:

      ```java
      // @ApplicationScoped
      public class Car implements Vehicle {
      ```

   4. Now, run your code again and check the output. 
      

    !!! Question "Did you get a different output message? Why changing the scope of the Car bean changed the behavior of our code?"

### Producers and Consumers

When working with Java code, we can leverage the CDI API to create classes that will behave and producers and consumers. In other words, instead of invoking a behavior of a specific class, we can instead create decoupled code that reacts to specific events in the container. Let's see this in action by trying out the news example demonstrated by the instructor.

1. Open the project `cdi-lab` in your IDE of choice if you still haven't. In this project we have the package `my.compary.cdi.lab.news` with the classes:
   
    - `Journalist`  class that is a **producer** responsible for notifying the news to every consumer in the project;
    - There are three **consumers** in this project:  `Magazine`, `Newspaper`, and `SocialMedia` .

2. Open the `Journalist.java` class, and:
    1. Analyse the different CDI annotations, like `@ApplicationScoped` and `@Inject`.
   
        !!! Question "What is this "Event" class and what can you use it for?"

    2. On the line 14, inside the method `receiveNews` , fire **events** containing the news, using the injected object `event`.

3. Open the `Magazine`, `Newspaper`, and `SocialMedia` classes.
    1. Notice it implements the interface `java.util.function.Consumer`. 
    2. Fix the method `accept`, by making it react to events that were fired in this application scope. 

        !!! TIP
            You may want to use the anotation `@javax.enterprise.event.Observes`;

4. Finally, open the class `App4.java` and analyse it.

5. Validate if you did everything right by running the main method in `App4`. You should see in a log output for each observer (three in total), containing messages like: "We got the news, we'll publish it on Social Media: Java 17 has arrived!!".

    ```shell
    INFO: WELD-ENV-002003: Weld SE container 724cb2e4-edb7-4de5-8b0f-df2adc814bb3 initialized
    Oct 10, 2021 8:52:36 PM my.compary.cdi.lab.news.SocialMedia accept
    INFO: We got the news, we'll publish it on Social Media: Java 17 has arrived!!
    Oct 10, 2021 8:52:36 PM my.compary.cdi.lab.news.NewsPaper accept
    INFO: We got the news, we'll publish it on a newspaper: Java 17 has arrived!!
    Oct 10, 2021 8:52:36 PM my.compary.cdi.lab.news.Magazine accept
    INFO: We got the news, we'll publish it on a magazine: Java 17 has arrived!!
    Oct 10, 2021 8:52:36 PM org.jboss.weld.environment.se.WeldContainer shutdown
    INFO: WELD-ENV-002001: Weld SE container 724cb2e4-edb7-4de5-8b0f-df2adc814bb3 shut down
    ```

    !!! Question "Why these messages were logged, if we never invoked the method `accept` in those classes (e.g. `magazine.accept(news)`)?"

!!! success "Congratulations"
    You've successfully completed the CDI exercise.There are several ways to use CDI in Java applications. In these exercises you could practice some of the features that can be leverage in application where you can use CDI.
    
    You also configured and used the specification through Weld, the reference implementation of the specification, but there are several others where each vendor provides their own implementation of the CDI spec.


