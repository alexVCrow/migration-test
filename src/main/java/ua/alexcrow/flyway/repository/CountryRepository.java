package ua.alexcrow.flyway.repository;

import org.springframework.data.repository.CrudRepository;
import ua.alexcrow.flyway.entity.Country;

public interface CountryRepository extends CrudRepository<Country, Long> { }
