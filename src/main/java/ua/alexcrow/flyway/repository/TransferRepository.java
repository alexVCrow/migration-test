package ua.alexcrow.flyway.repository;

import org.springframework.data.repository.CrudRepository;
import ua.alexcrow.flyway.entity.Transfer;

public interface TransferRepository extends CrudRepository<Transfer, Long> { }
