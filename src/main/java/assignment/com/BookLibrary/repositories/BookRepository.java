package assignment.com.BookLibrary.repositories;

import assignment.com.BookLibrary.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT * FROM Book WHERE LOWER(book_author) = LOWER(:authorName)", nativeQuery = true)
    List<Book> findBooksByAuthorName(String authorName);
    @Query(value = "SELECT * FROM Book WHERE is_rented = true", nativeQuery = true)
    List<Book> findByRentedTrue();

    @Query("SELECT b FROM Book b WHERE b.rented = false")
    List<Book> findByRentedFalse();


}
