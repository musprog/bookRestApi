/*
This Service provides all implementation required for the APIs
These methods are not APIs getPriceQuery(), stringToDate() and StringToDouble
Map<> are used for enhancing performance, it performs better with unique ids
Collections<> are used for sorting when its needed
The JSON file are loaded from project\resources folder
*/
package com.example.bookRestApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class BookService implements IBookService {

    //repositories
    private static final Map<String, Book> bookRepo = new HashMap<>();
    private static final List<Book> list;
    private static List<Book> result;

    static {
        try {

            File file = new ClassPathResource("/books.json").getFile();
            ObjectMapper mapper = new ObjectMapper();
            list = Arrays.asList(mapper.readValue(file, Book[].class));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Book b : list) bookRepo.put(b.getId(), b);
    }

    @Override
    public void createBook(Book book) {
        Integer id = list.size() + 1;
        String modId = id.toString();
        book.setId("B" + modId);
        bookRepo.put(book.getId(), book);
    }

    @Override
    public void updateBook(String id, Book book) {
        String upperCaseId = id.toUpperCase();
        bookRepo.remove(upperCaseId);
        book.setId(upperCaseId);
        bookRepo.put(upperCaseId, book);
    }

    @Override
    public Collection<Book> getBooks() {
        return bookRepo.values();
    }

    @Override
    public Collection<Book> searchById(String query) {

        result = new ArrayList<>();
        if (query != null) {
            for (Book b : list) {
                if (b.getId().toLowerCase().contains(query.toLowerCase())) {
                    result.add(b);
                }
            }
        } else {
            result = list;
        }
        result.stream().sorted(Comparator.comparing(Book::getId));
        return result;
    }

    @Override
    public Collection<Book> searchByAuthor(String query) {
        result = new ArrayList<>();
        if (query != null) {
            for (Book b : list) {
                if (b.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                    result.add(b);
                }
            }
        } else {
            result = list;
        }
        result.stream().sorted(Comparator.comparing(Book::getAuthor));
        return result;
    }

    @Override
    public Collection<Book> searchByTitle(String query) {
        result = new ArrayList<>();

        if (query != null) {
            for (Book b : list) {
                if (b.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    result.add(b);
                }
            }
        } else {
            result = list;
        }
        result.stream().sorted(Comparator.comparing(Book::getTitle));
        return result;
    }

    @Override
    public Collection<Book> searchByGenre(String query) {
        result = new ArrayList<>();

        if (query != null) {
            for (Book b : list) {
                if (b.getGenre().toLowerCase().contains(query.toLowerCase())) {
                    result.add(b);
                }
            }
        } else {
            result = list;
        }
        result.stream().sorted(Comparator.comparing(Book::getGenre));
        return result;
    }

    @Override
    public Collection<Book> searchByPrice(String query) {
        result = new ArrayList<>();

        if (query != null) {
            for (Book b : list) {
                if (stringToDouble(b.getPrice()).equals(stringToDouble(query))) {
                    result.add(b);
                }
            }
        } else {
            result = list;
        }
        result.stream().sorted(Comparator.comparing(Book::getPrice));
        return result;
    }

    @Override
    public Collection<Book> searchByPriceInterval(String query) {
        result = new ArrayList<>();

        if (query != null) {
            String[] values = getPriceQuery(query);
            double paramOne = stringToDouble(values[0]);
            double paramTwo = stringToDouble(values[1]);
            for (Book b : list) {
                if (paramOne <= stringToDouble(b.getPrice()) && paramTwo >= stringToDouble(b.getPrice())) {
                    result.add(b);
                }
            }
        } else {
            result = list;
        }
        result.stream().sorted(Comparator.comparing(Book::getPrice));
        return result;
    }

    public static String[] getPriceQuery(String query) {
        String[] params = query.split("&");
        return params;
    }

    public Double stringToDouble(String value) {
        return Double.valueOf(value);
    }

    @Override
    public Collection<Book> searchByDate(String year, String month, String day) {
        result = new ArrayList<>();
        String query = stringToDate(year, month, day);
        if (query != null) {
            for (Book b : list) {
                if (b.getPublish_date().contains(query)) {
                    result.add(b);
                }
            }
        } else {
            result = list;
        }
        result.stream().sorted(Comparator.comparing(Book::getPublish_date));
        return result;
    }

    public String stringToDate(String year, String month, String day) {
        StringBuilder date = new StringBuilder();
        String dash = "-";
        if (year != null && month != null && day != null) {
            date.append(year).append(dash).append(month).append(dash).append(day);
        } else if (year != null && month != null) {
            date.append(year).append(dash).append(month);
        } else {
            date.append(year);
        }
        return date.toString();
    }

    @Override
    public Collection<Book> searchByDesc(String query) {
        result = new ArrayList<>();

        if (query != null) {
            for (Book b : list) {
                if (b.getDescription().contains(query)) {
                    result.add(b);
                }
            }
        } else {
            result = list;
        }
        result.stream().sorted(Comparator.comparing(Book::getDescription));
        return result;
    }


}

