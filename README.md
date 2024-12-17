# authservice
Authentication Application
### **ADD SWAGGER-UI**
Add the swagger dependency in gradle file. No additional configuration is needed.

**eg: implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0'**

Check the compatible version

The Swagger UI page will then be available at http://server:port/context-path/swagger-ui.html 

### **@RestControllerAdvice** - *Used to handle exception occured in controller*

ExceptionHandlerExceptionResolver works

  - Check if there is an appropriate ExceptionHandler in the controller that threw the exception
  - If there is no suitable ExceptionHandler, go to ControllerAdvice
  - Checks if there is a suitable ExceptionHandler in ControllerAdvice, and if not, it goes to the next handler

Note: Add this dependency "**implementation 'org.springframework.boot:spring-boot-starter-validation:3.4.0'**"
