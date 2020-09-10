package ua.alexcrow.flyway.service;

import org.springframework.stereotype.Service;
import ua.alexcrow.flyway.entity.Transfer;
import ua.alexcrow.flyway.repository.TransferRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TransferDbServiceImpl implements TransferDbService {

    private final TransferRepository transferRepository;

    public TransferDbServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    @Transactional
    public Transfer addOrUpdate(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    @Override
    @Transactional
    public Optional<Transfer> findTransfer(long id) {
        return transferRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(long id) {
        transferRepository.deleteById(id);
    }
}
