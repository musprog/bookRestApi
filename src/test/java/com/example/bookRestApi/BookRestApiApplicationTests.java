package com.example.bookRestApi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class BookRestApiApplicationTests {

    @Autowired
    private BookServiceController bookController;

    @Test
    void contextLoads() throws Exception {
        assertThat(bookController).isNotNull();
    }

}
