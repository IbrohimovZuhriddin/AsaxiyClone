package zuhriddinscode.service;

import zuhriddinscode.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> getPopularBooks();
}