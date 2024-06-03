FROM openjdk:17
COPY build/libs/ms-announcement-project-0.0.1-SNAPSHOT.jar /app/
CMD ["java","-jar","/app/ms-announcement-project-0.0.1-SNAPSHOT.jar"]