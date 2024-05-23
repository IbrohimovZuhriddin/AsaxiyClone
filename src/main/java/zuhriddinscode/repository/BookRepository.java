package zuhriddinscode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zuhriddinscode.domain.Book;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List < Book > getPopularBooks();
}