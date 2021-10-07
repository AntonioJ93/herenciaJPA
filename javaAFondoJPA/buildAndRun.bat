@echo off
call mvn clean package
call docker build -t com.proyeccionDTOjpa/javaAFondoJPA .
call docker rm -f javaAFondoJPA
call docker run -d -p 9080:9080 -p 9443:9443 --name javaAFondoJPA com.proyeccionDTOjpa/javaAFondoJPA