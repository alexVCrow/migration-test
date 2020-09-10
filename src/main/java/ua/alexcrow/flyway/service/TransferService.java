package ua.alexcrow.flyway.service;

import ua.alexcrow.flyway.dto.TransferData;
import ua.alexcrow.flyway.entity.Transfer;

import java.util.Optional;

public interface TransferService {

    TransferData saveTransfer(TransferData transferData);

    Optional<Transfer> findOneOptional(long id);

    void deleteTransfer(long id);

}
