package assignment.com.BookLibrary.payload;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDTO {

    private Integer id;

    @NotEmpty
    @Size(max = 100,message = "Author name must be maximum of 50 characters !!")
    private String authorName;

    @Size(max = 500 ,message = "Author biography must be maximum of 500 characters !!")
    private String authorBio;
}
