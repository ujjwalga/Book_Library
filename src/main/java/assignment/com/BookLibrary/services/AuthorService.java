package assignment.com.BookLibrary.services;

import assignment.com.BookLibrary.payload.AuthorDTO;

import java.util.List;

public interface AuthorService {

    AuthorDTO createAuthor(AuthorDTO authorDTO);

    List<AuthorDTO> getAllAuthors();

    AuthorDTO getAuthorById(Integer id);

    AuthorDTO updateAuthor(Integer id, AuthorDTO authorDTO);

    void deleteAuthor(Integer id);
}
