FROM openjdk:11
ADD /target/store.jar backend.jar
ENTRYPOINT ["java","-jar","backend.jar"]
