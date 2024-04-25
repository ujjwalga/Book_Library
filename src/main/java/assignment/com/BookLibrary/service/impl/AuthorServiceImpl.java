package assignment.com.BookLibrary.service.impl;

import assignment.com.BookLibrary.entities.Author;
import assignment.com.BookLibrary.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import assignment.com.BookLibrary.payload.AuthorDTO;
import assignment.com.BookLibrary.repositories.AuthorRepository;
import assignment.com.BookLibrary.services.AuthorService;


import java.util.List;
import java.util.stream.Collectors;
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setAuthorName(authorDTO.getAuthorName());
        author.setAuthorBio(authorDTO.getAuthorBio());
        Author savedAuthor = authorRepository.save(author);
        return modelMapper.map(savedAuthor, AuthorDTO.class);
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = this.authorRepository.findAll();
        return authors.stream()
                .map(author -> modelMapper.map(author, AuthorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTO getAuthorById(Integer id) {
        Author author = this.authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "Authorid", id));
        return modelMapper.map(author, AuthorDTO.class);
    }

    @Override
    public AuthorDTO updateAuthor(Integer id, AuthorDTO authorDTO) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "Authorid", id));

        existingAuthor.setAuthorName(authorDTO.getAuthorName());
        existingAuthor.setAuthorBio(authorDTO.getAuthorBio());

        Author updatedAuthor = authorRepository.save(existingAuthor);
        return modelMapper.map(updatedAuthor, AuthorDTO.class);
    }

    @Override
    public void deleteAuthor(Integer id) {

        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author", "AuthorId", id);
        }
        authorRepository.deleteById(id);
    }
}
