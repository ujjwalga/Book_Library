package assignment.com.BookLibrary.service.impl;

import assignment.com.BookLibrary.entities.Book;
import assignment.com.BookLibrary.entities.Rental;
import assignment.com.BookLibrary.exceptions.BookAlreadyRentedException;
import assignment.com.BookLibrary.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import assignment.com.BookLibrary.payload.RentalDTO;
import assignment.com.BookLibrary.repositories.BookRepository;
import assignment.com.BookLibrary.repositories.RentalRepository;
import assignment.com.BookLibrary.services.RentalService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalServiceImpl implements RentalService {
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public RentalDTO createRental(RentalDTO rentalDTO) {
        // Retrieve the book being rented
        Book book = bookRepository.findById(rentalDTO.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book","bookId",rentalDTO.getBookId()));
        if (book.isRented()) {
            throw new BookAlreadyRentedException("Book", "bookId", book.getId());
        }
        book.setRented(true);
        bookRepository.save(book);

        Rental savedRental = new Rental();
        savedRental.setBook(book);
        savedRental.setRenterName(rentalDTO.getRenterName());
        Date rentalDate = new Date();
        savedRental.setRentalDate(rentalDate);
        savedRental.setDueDate(calculateDueDate(rentalDate,14));
        Rental savedRental1 = rentalRepository.save(savedRental);
        rentalDTO.setId(savedRental.getId());
        return rentalDTO;
    }


    @Override
        public RentalDTO rentBook (Integer bookId, String rentarName){
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
            if (book.isRented()) {
                throw new BookAlreadyRentedException("Book", "id", bookId);
            }
            book.setRented(true);
            bookRepository.save(book);

            Date rentalDate = new Date();
            Date dueDate = calculateDueDate(rentalDate, 14);

            Rental rental = new Rental();
            rental.setBook(book);
            rental.setRenterName(rentarName);
            rental.setRentalDate(rentalDate);
            rental.setDueDate(calculateDueDate(rentalDate,14));
            rentalRepository.save(rental);
            return modelMapper.map(rental, RentalDTO.class);
        }

    public RentalDTO returnBook(Integer rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental", "id", rentalId));

        // Find the associated book by book ID
        Book book = rental.getBook();
        book.setRented(false);
        bookRepository.save(book);

        rental.setReturnDate(new Date());
        rentalRepository.save(rental);
        return modelMapper.map(rental, RentalDTO.class);
    }

        @Override
        public RentalDTO getRentalById (Integer rentalId){
            Rental rental = rentalRepository.findById(rentalId)
                    .orElseThrow(() -> new ResourceNotFoundException("Rental", "rentalId", rentalId));
            return modelMapper.map(rental, RentalDTO.class);
        }
        @Override
        public List<RentalDTO> getAllRentals () {
            List<Rental> rentals = rentalRepository.findAll();
            return rentals.stream()
                    .map(rental -> modelMapper.map(rental, RentalDTO.class))
                    .collect(Collectors.toList());
        }

         @Override
        public void deleteRentalsByIds(List<Integer> rentalIds) {
        rentalRepository.deleteByIds(rentalIds);
        }
        @Override
        public List<RentalDTO> getOverdueRentals ( int maxRentalDays){
            // Calculate the due date (current date - maxRentalDays)
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -maxRentalDays);
            Date dueDate = calendar.getTime();

            // Retrieve overdue rentals based on the due date
            List<Rental> overdueRentals = rentalRepository.findByRentalDateBeforeAndReturnDateIsNull(dueDate);

            return overdueRentals.stream()
                    .map(rental -> modelMapper.map(rental, RentalDTO.class))
                    .collect(Collectors.toList());
        }

        @Override
        public RentalDTO updatedRental(Integer rentalId, RentalDTO rentalDTO) {
        Rental existingRental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental", "id", rentalId));

        if (rentalDTO.getBookId() != null) {
            Book book = bookRepository.findById(rentalDTO.getBookId())
                    .orElseThrow(() -> new ResourceNotFoundException("Book", "id", rentalDTO.getBookId()));
            existingRental.setBook(book);
        }
            existingRental.setRenterName(rentalDTO.getRenterName());
            existingRental.setRentalDate(rentalDTO.getRentalDate());
            existingRental.setDueDate(calculateDueDate(existingRental.getRentalDate(),14));
            existingRental.setReturnDate(rentalDTO.getReturnDate());

        Rental updatedRental = rentalRepository.save(existingRental);
        RentalDTO updatedRentalDTO = modelMapper.map(updatedRental, RentalDTO.class);

        return updatedRentalDTO;
    }

    private Date calculateDueDate (Date rentalDate,int maxRentalDays){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(rentalDate);
            calendar.add(Calendar.DAY_OF_MONTH, maxRentalDays * -1); // Subtract maxRentalDays from current date
            return calendar.getTime();
        }
    }

