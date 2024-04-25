package assignment.com.BookLibrary.repositories;

import assignment.com.BookLibrary.entities.Rental;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
@Transactional
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    @Query("SELECT r FROM Rental r WHERE r.rentalDate < ?1")
    List<Rental> findByRentalDateBeforeAndReturnDateIsNull(Date maxRentalDate);

    @Modifying
    @Query(value = "DELETE FROM rental WHERE rental_id IN :rentalIds", nativeQuery = true)
    void deleteByIds(List<Integer> rentalIds);
}
