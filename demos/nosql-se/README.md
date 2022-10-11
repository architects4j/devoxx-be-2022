# Jakarta NoSQL and Java SE sample

## Using Docker

1. Install docker: https://www.docker.com/
1. https://hub.docker.com/_/mongo
1. Run docker command
1. Run MongoDB: verify MongoDB image name with the command `docker images`, it can be mongodb or mongo, and then execute this
    * `docker run -d --name mongodb-instance -p 27017:27017 mongo`