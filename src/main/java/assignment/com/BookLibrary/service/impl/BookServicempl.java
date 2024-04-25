package assignment.com.BookLibrary.service.impl;

import assignment.com.BookLibrary.entities.Book;
import assignment.com.BookLibrary.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import assignment.com.BookLibrary.payload.BookDTO;
import assignment.com.BookLibrary.repositories.BookRepository;
import assignment.com.BookLibrary.services.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServicempl implements BookService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = this.bookRepository.findAll();
        List<BookDTO> bookDtos = books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
        return bookDtos;
    }

    @Override
    public BookDTO getBookById(Integer id) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "BookId", id));
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = new Book();

        book.setBookTitle(bookDTO.getBookTitle());
        book.setBookAuthor(bookDTO.getBookAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublicationYear(bookDTO.getPublicationYear());

        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookDTO.class);
    }

    @Override
    public BookDTO updateBook(Integer id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "BookId", id));

        // Update the existingBook entity with fields from bookDTO
        existingBook.setBookTitle(bookDTO.getBookTitle());
        existingBook.setBookAuthor(bookDTO.getBookAuthor());
        existingBook.setIsbn(bookDTO.getIsbn());
        existingBook.setPublicationYear(bookDTO.getPublicationYear());

        // Save the updated book entity
        Book updatedBook = bookRepository.save(existingBook);

        return modelMapper.map(updatedBook, BookDTO.class);
    }

    @Override
    public void deleteBook(Integer id){
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book", "BookId", id);
        }
        bookRepository.deleteById(id);
    }
    @Override
    public List<BookDTO> getBooksByAuthorName(String authorName) {
        List<Book> books = bookRepository.findBooksByAuthorName(authorName);
        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getAvailableBooks() {
        List<Book> availableBooks = bookRepository.findByRentedFalse();
        return availableBooks.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getRentedBooks() {
        List<Book> rentedBooks = bookRepository.findByRentedTrue();
        return rentedBooks.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

}
