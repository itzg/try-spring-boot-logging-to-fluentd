This Spring Boot application is configured with [more logback appenders](https://github.com/sndyuk/logback-more-appenders) to send logs to [fluentd](https://docs.fluentd.org/) using [fluency](https://github.com/komamitsu/fluency).

The application declares two schedule methods to simulate some activity to log. One method logs at info-level the current time every second. The other method logs an exception with stack trace every 10 seconds at warning level.

# Usage

## Start fluentd

This repository includes a [Docker Compose file](docker-compose.yml) that declares a fluentd service bound to the default fluentd port, 24224. To start the service run:  

```
docker-compose up -d
```

## Start the application

```
./mvnw spring-boot:run
```

You won't see anything after the initial banner since the only appender configured sends to fluend.

## Check fluentd for the application logs

```
docker exec try-spring-boot-logging-to-fluentd_fluentd_1 tail -f /fluentd/log/data.log
```

# How it works

The logback configuration for fluentd is located in [fluency.xml](src/main/resources/fluency.xml) and that is included by [logback-spring.xml](src/main/resources/logback-spring.xml). That approach to customizing log configuration in Spring Boot is described in the [logging chapter of the Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-logging.html#boot-features-custom-log-configuration).