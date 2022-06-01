FROM amazoncorretto:17

COPY . app

WORKDIR app/

RUN ./gradlew clean build -x test
RUN mv build/libs/todo-*-SNAPSHOT.jar ../app.jar
WORKDIR ..
RUN rm -rf app/

ENTRYPOINT ["java","-jar","/app.jar"]