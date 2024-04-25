package assignment.com.BookLibrary.repositories;

import assignment.com.BookLibrary.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
