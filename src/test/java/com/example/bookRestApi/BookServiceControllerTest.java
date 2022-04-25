package com.example.bookRestApi;

import org.assertj.core.api.AbstractStringAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookServiceControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getBook() throws Exception {

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books",
                String.class)).contains("B13");
    }


    @Test
    void getBookById() throws Exception {

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books/id",
                String.class)).contains("id");
    }

    @Test
    void testGetBookById() throws Exception {

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books/id/b",
                String.class)).contains("B7");
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books/id/b1",
                String.class)).contains("B1");
    }

    @Test
    void getAllBookByAuthor() throws Exception {

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books/author/",
                String.class)).contains("B5");
    }

    @Test
    void getBookByAuthor() throws Exception {

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books/author/joe",
                String.class)).contains("B1");
    }

    @Test
    void getAllBookByTitle() throws Exception {

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books/title/",
                String.class)).contains("B8");
    }

    @Test
    void getBookByTitle() throws Exception {

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books/title/deploy",
                String.class)).contains("B1");
    }

    @Test
    void getAllBookByGenre() throws Exception {

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books/genre/",
                String.class)).contains("B11");
    }

    @Test
    void getBookByGenre() throws Exception {

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books/genre/com",
                String.class)).contains("B10");
    }

    @Test
    void getAllBookByPrice() throws Exception {

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books/price/",
                String.class)).contains("5.95");
    }

    @Test
    void getBookByPrice() throws Exception {

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books/price/33.0",
                String.class)).contains("B1");
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books/price/30.0&35.0",
                String.class)).contains("B11");
    }

    @Test
    void getAllBookByDate() throws Exception {

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books/published/",
                String.class)).contains("2000");
    }

    @Test
    void getBookByDate() throws Exception {

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books/published/2012",
                String.class)).contains("B13");
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books/published/2012/08",
                String.class)).contains("B1");
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books/published/2012/08/15",
                String.class)).contains("B1");
    }

    @Test
    void getAllBookByDesc() throws Exception {

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books/description/",
                String.class)).contains("B9");
    }

    @Test
    void getBookByDesc() throws Exception {

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books/description/deploy",
                String.class)).contains("B13");
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/books/description/applications",
                String.class)).contains("B1");
    }

    @Test
    void updateBook() throws Exception {

    }

    @Test
    void createBook() {

    }
}