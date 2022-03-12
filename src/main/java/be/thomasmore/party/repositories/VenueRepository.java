package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VenueRepository extends CrudRepository<Venue, Integer> {
    Iterable<Venue> findByOutdoor(boolean isOutdoor);
    Iterable<Venue> findByIndoor(boolean isIndoor);

    @Query("SELECT v FROM Venue v WHERE v.capacity <= :max")
    Iterable<Venue> findCapacitySmallerThan(@Param("max") int capacity);
    Iterable<Venue> findByCapacityLessThanEqual(int capacity);

    @Query("SELECT v FROM Venue v WHERE v.capacity <= ?2 AND v.capacity >= ?1")
    Iterable<Venue> findCapacityBetween(int min, int max);
    Iterable<Venue> findByCapacityIsBetween(int min, int max);

    @Query("SELECT v FROM Venue v WHERE v.capacity >= ?1")
    Iterable<Venue> findCapacityBiggerThan(int capacity);
    Iterable<Venue> findByCapacityGreaterThanEqual(int capacity);

    Optional<Venue> findFirstByIdLessThanOrderByIdDesc(int id);
    Optional<Venue> findFirstByIdGreaterThanOrderById(int id);
    Optional<Venue> findFirstByOrderByIdDesc();
    Optional<Venue> findFirstByOrderByIdAsc();

    @Query("SELECT v FROM Venue v")
    Iterable<Venue> findAllVenues();

    @Query("SELECT v FROM Venue v WHERE (:min IS NULL OR v.capacity >= :min) AND " +
            "(:max IS NULL OR v.capacity <= :max) AND " +
            "(:distance IS NULL OR v.distanceFromPublicTransportInKm <= :distance) AND " +
            "(:food IS NULL OR v.foodProvided = :food)")
    Iterable<Venue> findVenues(@Param("min") Integer minimum,
                               @Param("max") Integer maximum,
                               @Param("distance") Double distance,
                               @Param("food") Boolean food);
}