package pl.maks.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maks.carrental.repository.model.Parking;

@Repository
public interface SpringDataParkingsRepository extends JpaRepository<Parking, Integer> {
}
