# NoSQL persistence on Java services 

---- wip: update references to team

In this exercise you can validate and practice your knowledge of the following concepts:

* Java microservice implementation with Helidon;
* Dev experience when using NoSQL databases with Java;
* Jakarta NoSQL;
* Implicit and constant usage of CDI;

Getting familiar with the use case and the exercise goals described next is highly recommended, as a detailed
step-by-step guide is not provided.

**Friendly advice**: consider leveraging this opportunity to
upskill by making the best use of your knowledge and research skills to code the solution without replicating the
provided solution.

## Scenario

Acme Store contacted you to ask for advise about NoSQL databases usage in MicroProfile-based services. They need a solution built on top of the RESTFul service delivered on the previous exercise. 
If you used the quickstart, by now you should have a microservice that relies on in-memory persistence with MicroStream. We will now add another option of persistence layer to this service, 
allowing it to persist data on [MongoDB](mongodb.com). 

### Goals

**About the service:**

- You should adapt an existing application, the `acme-store-rest` application. It uses Microprofile 3.3 + Helidon and MicroStream for persistence.
- The service handles `Product`, and provides RESTFul operations for it:
    - List all products:  GET "/products/"
    - Find a product by ID: GET "/products/{productName}"
    - Delete a product: DELETE "/products/{productName}"
    - Update a product: PUT "/products/{productName}"
    - Insert a product: POST "/products/{productName}"
- The APIs are documented with the [Eclipse Microprofile-Open-API](https://github.com/eclipse/microprofile-open-api);
- It's persistence layer relies on MicroStream for performant in-memory persistence capabilities.

**Goals:**

* Add the support for persistence with JNoSQL for MongoDB;
* Change as minimum code as possible;
* Test the application;

## How to get started

### The database

This exercise requires a MongoDB instance. To facilitate you can use Docker. 

!!! info "If you don't have Docker installer yet, you can get it at: https://www.docker.com/" 

* Running MongoDB with Docker:

```shell
docker run -d --name mongodb-instance -p 27017:27017 mongo
```

### The project

You can use the delivered service you finished on the lab [Golden APIs for ACME Store](challenges/04_challenge_rest.md). 

If you prefer, you can also start from this quickstart application. It's similar to a 
resulting application from the previous lab with extra instructions on what the required tasks:

[https://github.com/architects4j/helidon-microstream-training-labs-foundation/tree/main/acme-store-rest-nosql](https://github.com/architects4j/helidon-microstream-training-labs-foundation/tree/main/acme-store-rest-nosql)

To start the application, you can build, package and run it as usual.

## Implementing the solution

See below a general guidance on how you can add NoSQL persistence to this service:

1. **Dependencies**: Add the Jakarta NoSQL dependencies to `pom.xml`:
```xml
        <dependency>
            <groupId>org.eclipse.jnosql.mapping</groupId>
            <artifactId>mapping-document</artifactId>
            <version>1.0.0-b4</version>
        </dependency>
        <dependency>
            <groupId>org.eclipse.jnosql.communication</groupId>
            <artifactId>mongodb-driver</artifactId>
            <version>1.0.0-b4</version>
        </dependency>
```

2. **Database configurations**: Add the MongoDB configurations to `microprofile-config.properties`:
```properties
# MongoDB Configs
document=document
document.database=restaurant
document.settings.jakarta.nosql.host=localhost:27017
document.provider=org.eclipse.jnosql.communication.mongodb.document.MongoDBDocumentConfiguration
```

3. **Entity configurations**: 
     1. Configure the `Product` as an entity bean:
     1. Add the `@jakarta.nosql.mapping.Entity` declaration;
     2. Add an empty constructor method;
     g3. Annotate the attributes with adequate column configurations. Use `@Id` and `@Column`. Remember, the attributes can no longer be final.

4. **The document manager bean:** In the package `org.a4j.product.infra` create a new class `DocumentManagerProducer`:
```java
package org.a4j.product.infra;

//TODO: Set this bean as ApplicationScope
class DocumentManagerProducer {

    //TODO: Use CDI to inject this bean
    //TODO: Obtain the `document` value configured in microprofile-config.properties. Use @ConfigProperty.
    private DocumentCollectionManager manager;
    
    // TODO: Configure as a producer with @Produces
    public DocumentCollectionManager getManager() {
        return manager;
    }

    public void destroy(@Disposes DocumentCollectionManager manager) {
        manager.close();
    }
}
```

1. **Creating a new repository**: Add a new `ProductRepository` interface. It should extend the `Repository` interface:
```java
extends Repository<Product, String> 
```
2. **Adjusting the resource class implementation**: 
     1. In the `ProductResource`, let's change the persistence layer. Replace the existing repository based on `Inventory` with our new one 
      `ProductResource`.
      ```java
      //**Before:** 
      private Inventory repository;
      //**After:**
      @Inject
      private ProductRepository repository;
      ```
     1. the following injection method as we're already injecting in the attribute level:
       ```java 
       @Inject
       ProductResource(Inventory repository) {
        this.repository = repository;
       }
       ```

3. **Adding a new method to the repository:**:  At this point, you will notice the code doesn't compile since the method `findAll` does not exist in the repository. Currently, (v1.0.0-b4) the 
   `jakarta.
   nosql.mapping.Repository` 
   does not offer 
   a `findAll` out-of-the-box. It does offer `save`, `deleteById`, `findById`, `existsById` and `count`, therefore, the only method you need to att to the `ProductRepository` interface to deliver 
   the existing endpoints is the `getAll` method. In the `ProductRepository`, add the method signature. Example:
```java
List<Product> findAll();
```

### Testing the service

* By default it will run on port 8081, as configured in the `microprofile-config.properties`.
* Validate your APIs documentation with OpenAPI UI at: [http://localhost:8081/openapi-ui/]()
* Check the service implementation with the following request examples:
    * **Insert** a new product
    ```shell
    curl --location --request POST 'http://localhost:8081/products' \
    --header 'Content-Type: application/json' \
    --data-raw '{"name": "bottle", "description": "Can store cold and hot liquids.", "quantity": "2"}'
    ```
    * **List** all products
    ```shell
    curl --location --request GET 'http://localhost:8081/products'
    ```
    * **Search** a product by ID (name)
    ```shell
    curl --location --request GET 'http://localhost:8081/products/bottle'
    ```
    * **Update** a product using its ID (name)
    ```shell
    curl --location --request POST 'http://localhost:8081/products/bottle' \
    --header 'Content-Type: application/json' \
    --data-raw '{"name": "bottle", "description": "Can store ONLY cold liquids.", "quantity": "2"}'
    ```
    * **Delete** a product by ID (name)
    ```shell
    curl --location --request DELETE 'http://localhost:8081/products/bottle'
    ```

* If you can successfully execute the above items, it means you acomplished all this challenge goals!

!!! success "Congratulations!"

    You've finished your task for Acme Store! You have easily modified an existing project by taking advantadge of a well-defined persistence layer. You have used JNoSQL to allow this service to persist its data using MongoDB. Well done!
