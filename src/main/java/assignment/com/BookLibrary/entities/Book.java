package assignment.com.BookLibrary.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer id;

    @Column(name =  "book_title",length = 100, nullable = false)
    private String bookTitle;

    @Column(name =  "book_author",length = 50, nullable = false)
    private String bookAuthor;

    @Column(name = "isbn",length = 13, nullable = false)
    private String isbn;

    @Column(name = "pub_year",length = 4, nullable = false)
    private Integer publicationYear;

    @Column(name = "is_rented")
    private boolean rented;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id") // Name of the foreign key column in the Book table
    private Author author;
}
