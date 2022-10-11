# MicroProfile generated Application

## Introduction

MicroProfile Starter has generated this MicroProfile application for you.

The generation of the executable jar file can be performed by issuing the following command

```shell
mvn clean package
``

This will create an executable jar file **restaurant.jar** within the _target_ maven folder. This can be started by executing the following command

```shell
java -jar target/team.jar
```

To launch the test page, open your browser at the following URL

```shell
http://localhost:8080/index.html  
```


To execute the tests:

```shell
curl --location --request PUT 'http://localhost:8080/team' \
--header 'Content-Type: application/json' \
--data-raw '{"name": "Mario", "score": 10, "position": "ATTACKER", "city": "Salvador" }'

curl --location --request PUT 'http://localhost:8080/team' \
--header 'Content-Type: application/json' \
--data-raw '{"name": "Luigi", "score": 20, "position": "DEFENDER", "city": "Lisbon" }'

curl --location --request GET 'http://localhost:8080/team'

curl --location --request GET 'http://localhost:8080/team/Mario'

curl --location --request DELETE 'http://localhost:8080/team/Luigi'

curl --location --request GET 'http://localhost:8080/team/Luigi'

```