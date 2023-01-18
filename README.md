# SpringBootArchetype
Archetype for a simple spring boot project without any own functionality. Written in Kotlin. Based on Spring Boot 3.0. 
Contains an Index Controller with a thymeleaf index page and a RestController with some example get-Requests. Can be run and built.

To run the application in your IDE, open the class `src/main/kotlin/de/springbootarchetype/Application.kt` and run its main function `de.springbootarchetype.ApplicationKt.main`.

The application can be built by `mvn clean install`. That creates a runnable, standalone jar-file within the `target` directory.