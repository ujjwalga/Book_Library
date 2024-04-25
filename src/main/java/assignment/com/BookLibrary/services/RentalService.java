package assignment.com.BookLibrary.services;

import assignment.com.BookLibrary.payload.RentalDTO;

import java.util.List;

public interface RentalService {

    //Create a rental
    RentalDTO createRental(RentalDTO rentalDTO);

    RentalDTO getRentalById(Integer rentalId);

    // Get all rentals
    List<RentalDTO> getAllRentals();

    // Rent a book for a given bookId and renterName
    RentalDTO rentBook(Integer bookId, String renterName);

    // Return a book based on its rentalId
    RentalDTO returnBook(Integer rentalId);

    void deleteRentalsByIds(List<Integer> rentalIds);
    // Get overdue rentals based on the maximum allowed rental days
    List<RentalDTO> getOverdueRentals(int maxRentalDays);

    RentalDTO updatedRental(Integer rentalId, RentalDTO rentalDTO);

}
