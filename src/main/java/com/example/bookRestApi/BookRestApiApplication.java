/*
This app is reading and manipulating books.json with APIs, all use cases
are fulfilled nad matching with the requirements
Tested with API client postman,to feed JSON payloads
The app includes a small unit test for output of APIs based on
requirements, project\test
*/
package com.example.bookRestApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookRestApiApplication.class, args);
    }

}
