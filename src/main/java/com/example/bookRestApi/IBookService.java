/*
The restful APIs
*/
package com.example.bookRestApi;

import java.util.Collection;

public interface IBookService {

    public abstract void createBook(Book book);

    public abstract void updateBook(String id, Book book);

    public abstract Collection<Book> getBooks();

    public abstract Collection<Book> searchById(String query);

    public abstract Collection<Book> searchByAuthor(String query);

    public abstract Collection<Book> searchByTitle(String query);

    public abstract Collection<Book> searchByGenre(String query);

    public abstract Collection<Book> searchByPriceInterval(String query);

    public abstract Collection<Book> searchByPrice(String query);

    public abstract Collection<Book> searchByDate(String year, String month, String day);

    public abstract Collection<Book> searchByDesc(String query);
}
