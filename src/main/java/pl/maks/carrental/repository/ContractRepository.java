package pl.maks.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maks.carrental.repository.model.Contract;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
}

