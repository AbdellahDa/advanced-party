package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.repository.CrudRepository;

public interface VenueRepository extends CrudRepository<Venue, Integer> {
    Iterable<Venue> findByOutdoor(boolean isOutdoor);

    Iterable<Venue> findByCapacityLessThanEqual(int i);

    Iterable<Venue> findByCapacityIsBetween(int i, int i1);

    Iterable<Venue> findByCapacityIsGreaterThan(int i);

}