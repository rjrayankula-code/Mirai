   package org.mirai.controller;

   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.RestController;

   import java.util.HashMap;
   import java.util.Map;

   @RestController
   @RequestMapping("/api") // Base API path
   public class GreetingController {

       @GetMapping("/greet")
       public Map<String, String> greet() {
           Map<String, String> response = new HashMap<>();
           response.put("message", "Hello from Spring Boot!");
           return response;
       }

   }