package assignment.com.BookLibrary.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO {

    private Integer id;

    @NotNull(message = "Book ID is required")
    private Integer bookId;

    @NotEmpty(message = "Renter name is required")
    private String renterName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rentalDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;

    private Date dueDate;


}
