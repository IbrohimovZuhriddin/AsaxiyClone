package zuhriddinscode.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zuhriddinscode.domain.Book;
import zuhriddinscode.repository.BookRepository;
import zuhriddinscode.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List< Book > getPopularBooks() {
        return bookRepository.getPopularBooks();
    }

}