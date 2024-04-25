package assignment.com.BookLibrary.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import assignment.com.BookLibrary.payload.RentalDTO;
import assignment.com.BookLibrary.services.RentalService;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;


    @PostMapping("/create")
    public ResponseEntity<RentalDTO> createRental(@Valid @RequestBody RentalDTO rentalDto) {
        RentalDTO createdRental = this.rentalService.createRental(rentalDto);
        return new ResponseEntity<>(createdRental, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDTO> getRentalById(@PathVariable Integer id) {
        RentalDTO rental = this.rentalService.getRentalById(id);
        return new ResponseEntity<>(rental, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<RentalDTO>> getAllRentals() {
        List<RentalDTO> rentals = this.rentalService.getAllRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @PostMapping("/rent")
    public RentalDTO rentBook(@RequestParam Integer bookId, @RequestParam String renterName) {
        return rentalService.rentBook(bookId, renterName);
    }

    @PostMapping("/return")
    public RentalDTO returnBook(@RequestParam Integer rentalId) {
        return rentalService.returnBook(rentalId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRentalsByIds(@RequestBody List<Integer> rentalIds) {
        rentalService.deleteRentalsByIds(rentalIds);
        return ResponseEntity.status(HttpStatus.OK).body("Rentals deleted successfully");
    }

    @GetMapping("/overdueRentals")
    public ResponseEntity<List<RentalDTO>> getOverdueRentals() {
        List<RentalDTO> overdueRentals = rentalService.getOverdueRentals(14); // Example: 14 days
        return ResponseEntity.ok(overdueRentals);
    }

    @PutMapping("/{rentalId}")
    public ResponseEntity<RentalDTO> updateRental(@PathVariable Integer rentalId, @RequestBody RentalDTO rentalDTO) {
        RentalDTO updatedRentalDTO = rentalService.updatedRental(rentalId, rentalDTO);
        return ResponseEntity.ok(updatedRentalDTO);
    }

}
