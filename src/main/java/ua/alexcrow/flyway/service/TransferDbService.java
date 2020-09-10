package ua.alexcrow.flyway.service;

import ua.alexcrow.flyway.entity.Transfer;

import java.util.Optional;

public interface TransferDbService {

    Transfer addOrUpdate(Transfer transfer);

    Optional<Transfer> findTransfer(long id);

    void delete(long id);

}
