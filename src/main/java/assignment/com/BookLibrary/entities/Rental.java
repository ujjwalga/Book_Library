package assignment.com.BookLibrary.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "rental")
@Getter
@Setter
@NoArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", nullable = false)
    private Book book;

    @Column(name =  "renter_name",length = 50, nullable = false)
    private String renterName;

    @Column(name =  "rental_date",length = 50)
    private Date rentalDate;

    @Column(name =  "return_date",length = 50)
    private Date returnDate;

    @Column(name =  "due_date",length = 50)
    private Date dueDate;
}
