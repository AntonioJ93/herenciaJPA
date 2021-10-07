#!/bin/sh
mvn clean package && docker build -t com.proyeccionDTOjpa/javaAFondoJPA .
docker rm -f javaAFondoJPA || true && docker run -d -p 9080:9080 -p 9443:9443 --name javaAFondoJPA com.proyeccionDTOjpa/javaAFondoJPA