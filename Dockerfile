FROM openjdk:17
RUN mkdir /bffApp
WORKDIR /bffApp
COPY rest/target/BffApplication.jar /bffApp
CMD ["java","-jar","/bffApp/BffApplication.jar"]
EXPOSE 8082