FROM java:8-jre-alpine
COPY target/dw-spring-1.0.jar /home
COPY config.yml /home
WORKDIR /home
CMD ["java","-jar","/home/dw-spring-1.0.jar","server","config.yml"]
EXPOSE 9000-9001