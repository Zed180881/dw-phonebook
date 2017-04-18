# phonebook

How to start the phonebook application
---

1. Check your database connection settings in resources/liquibase.properties and config.yml
2. Update your database structure `mvn liquibase:update`
3. Run `mvn clean install` to build your application
4. Start application with `java -jar target/dw-spring-1.0.jar server config.yml`
5. To check that your application is running enter url `http://localhost:9000`

Health Check
---

To see your applications health enter url `http://localhost:9001/healthcheck`
To see your applications API docs enter url `http://localhost:9000/apidocs/`

To create Docker image run command: `docker build -t dw-spring .`
To run Docker image run command: `docker run -p 9000:9000 dw-spring`