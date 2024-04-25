package assignment.com.BookLibrary.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {

    private Integer id;

    @NotEmpty(message = "title must not be empty")
    @Size(max = 50,message = "title must be maximum of 50 characters !!")
    private String bookTitle;

    @NotEmpty(message = "author name must not be empty")
    @Size(max = 50,message = "Author name must be maximum of 50 characters !!")
    private String bookAuthor;

    @NotEmpty(message = "ISBN must not   be empty")
    @Size(min = 10, max = 13,message = "isbn must be minimum of 10 digits maximum of 13 digits !!")
    @Pattern(regexp = "^[0-9]{10,13}$", message = "Invalid ISBN format. Should contain only digits")
    private String isbn;

    @NotEmpty(message = "publication year must not be empty")
    @Size(max = 4,message = "year must be a valid value !!")
    private Integer publicationYear;
}
