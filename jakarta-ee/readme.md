# MicroProfile generated Application

## Introduction

MicroProfile Starter has generated this MicroProfile application for you.

The generation of the executable jar file can be performed by issuing the following command


    mvn clean package

This will create an executable jar file **jakarta-ee-microbundle.jar** within the _target_ maven folder. This can be started by executing the following command

    java -jar target/jakarta-ee-microbundle.jar




To launch the test page, open your browser at the following URL

    http://localhost:8080/index.html  



To execute the tests:

```shell
curl --location --request POST 'http://localhost:8080/restaurants' \
--header 'Content-Type: application/json' \
--data-raw '{"name": "water", "description": "Water appears as a clear, nontoxic liquid composed of hydrogen and oxygen, essential for life.", "type": "BEVERAGE", 
"expires": "2025-12-03", "ingredients": [{"name": "water", "unit": "L", "quantity": 2}]}'

curl --location --request POST 'http://localhost:8080/restaurants' \
--header 'Content-Type: application/json' \
--data-raw '{"name": "coconut-water", "description": "Coconut water is the clear liquid inside coconuts.", "type": "BEVERAGE", 
"expires": "2025-12-03", "ingredients": [{"name": "coconut water", "unit": "L", "quantity": 1}]}'


curl --location --request GET 'http://localhost:8080/restaurants/water'

curl --location --request GET 'http://localhost:8080/restaurants'
```

Validations tests:

```shell
curl --location --request POST 'http://localhost:8080/restaurants' \
--header 'Content-Type: application/json' \
--data-raw '{"description": "Water appears as a clear, nontoxic liquid composed of hydrogen and oxygen, essential for life.", "type": "BEVERAGE", "expires": "2030-12-03"}'

curl --location --request POST 'http://localhost:8080/restaurants' \
--header 'Content-Type: application/json' \
--data-raw '{"name": "water", "description": "Water appears as a clear, nontoxic liquid composed of hydrogen and oxygen, essential for life.", "type": "BEVERAGE", "expires": "2000-12-03"}'

curl --location --request POST 'http://localhost:8080/restaurants' \
--header 'Content-Type: application/json' \
--data-raw '{"name": "w", "description": "Water appears as a clear, nontoxic liquid composed of hydrogen and oxygen, essential for life.", "type": "BEVERAGE", "expires": "2025-12-03"}'
```