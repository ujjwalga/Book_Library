package assignment.com.BookLibrary.services;

import assignment.com.BookLibrary.payload.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();

    BookDTO getBookById(Integer id);

    BookDTO createBook(BookDTO bookDTO);

    BookDTO updateBook(Integer id, BookDTO bookDTO);

    void deleteBook(Integer id);

    List<BookDTO> getBooksByAuthorName(String authorName);

    List<BookDTO> getAvailableBooks();

    List<BookDTO> getRentedBooks();
}
