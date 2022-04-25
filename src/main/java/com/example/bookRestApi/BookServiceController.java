/*
The Controller, managing the URLs for the app
*/

package com.example.bookRestApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class BookServiceController {
    @Autowired
    BookService bookService;

    @GetMapping(value = "/books", produces = "application/json")
    public ResponseEntity<Object> getBook() {
        return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
    }

    @GetMapping(value = "/books/id", produces = "application/json")
    public ResponseEntity<Object> getBookById() {
        return new ResponseEntity<>(bookService.searchById(null), HttpStatus.OK);
    }

    @GetMapping(value = "/books/id/{id}", produces = "application/json")
    public ResponseEntity<Object> getBookById(@PathVariable("id") String id) {
        return new ResponseEntity<>(bookService.searchById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/books/author", produces = "application/json")
    public ResponseEntity<Object> getAllBookByAuthor() {
        return new ResponseEntity<>(bookService.searchByAuthor(null), HttpStatus.OK);
    }

    @GetMapping(value = "/books/author/{query}", produces = "application/json")
    public ResponseEntity<Object> getBookByAuthor(@PathVariable("query") String query) {
        return new ResponseEntity<>(bookService.searchByAuthor(query), HttpStatus.OK);
    }

    @GetMapping(value = "/books/title", produces = "application/json")
    public ResponseEntity<Object> getAllBookByTitle() {
        return new ResponseEntity<>(bookService.searchByTitle(null), HttpStatus.OK);
    }

    @GetMapping(value = "/books/title/{query}", produces = "application/json")
    public ResponseEntity<Object> getBookByTitle(@PathVariable("query") String query) {
        return new ResponseEntity<>(bookService.searchByTitle(query), HttpStatus.OK);
    }

    @GetMapping(value = "/books/genre", produces = "application/json")
    public ResponseEntity<Object> getAllBookByGenre() {
        return new ResponseEntity<>(bookService.searchByGenre(null), HttpStatus.OK);
    }

    @GetMapping(value = "/books/genre/{query}", produces = "application/json")
    public ResponseEntity<Object> getBookByGenre(@PathVariable("query") String query) {
        return new ResponseEntity<>(bookService.searchByGenre(query), HttpStatus.OK);
    }

    @GetMapping(value = "/books/price", produces = "application/json")
    public ResponseEntity<Object> getAllBookByPrice() {
        return new ResponseEntity<>(bookService.searchByPrice(null), HttpStatus.OK);
    }

    @GetMapping(value = "/books/price/{query}", produces = "application/json")
    public ResponseEntity<Object> getBookByPrice(@PathVariable("query") String query) {
        Object bookServiceObject;
        if (query.contains("&")) {
            bookServiceObject = bookService.searchByPriceInterval(query);
        } else {
            bookServiceObject = bookService.searchByPrice(query);
        }
        return new ResponseEntity<>(bookServiceObject, HttpStatus.OK);
    }


    @GetMapping(value = "/books/published", produces = "application/json")
    public ResponseEntity<Object> getAllBookByDate() {
        return new ResponseEntity<>(bookService.searchById(null), HttpStatus.OK);
    }

    @GetMapping(value = {"/books/published/{year}", "/books/published/{year}/{month}",
            "/books/published/{year}/{month}/{day}"}, produces = "application/json")
    public ResponseEntity<Object> getBookByDate(@PathVariable("year") String year,
                                                @PathVariable(required = false) String month,
                                                @PathVariable(required = false) String day) {
        return new ResponseEntity<>(bookService.searchByDate(year, month, day), HttpStatus.OK);
    }

    @GetMapping(value = "/books/description", produces = "application/json")
    public ResponseEntity<Object> getAllBookByDesc() {
        return new ResponseEntity<>(bookService.searchByDesc(null), HttpStatus.OK);
    }

    @GetMapping(value = "/books/description/{query}", produces = "application/json")
    public ResponseEntity<Object> getBookByDesc(@PathVariable("query") String query) {
        return new ResponseEntity<>(bookService.searchByDesc(query), HttpStatus.OK);
    }

    @PostMapping(value = "/books/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updateBook(@PathVariable("id") String id, @RequestBody Book book) {

        bookService.updateBook(id, book);
        return new ResponseEntity<>("Book is updated successsfully", HttpStatus.OK);
    }

    @PutMapping(value = "/books", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createBook(@RequestBody Book book) {
        bookService.createBook(book);
        return new ResponseEntity<>("Book is created successfully", HttpStatus.CREATED);
    }
}
