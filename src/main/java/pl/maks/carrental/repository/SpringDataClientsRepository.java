package pl.maks.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maks.carrental.repository.model.Client;

@Repository
public interface SpringDataClientsRepository extends JpaRepository<Client, Integer>{
}
