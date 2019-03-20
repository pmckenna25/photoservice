FROM openjdk:latest

COPY /target/app.jar app.jar
COPY entry.sh entry.sh

RUN chmod 777 entry.sh

EXPOSE 9090

ENTRYPOINT ["./entry.sh"]