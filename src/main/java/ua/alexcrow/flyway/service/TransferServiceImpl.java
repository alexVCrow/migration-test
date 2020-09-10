package ua.alexcrow.flyway.service;

import org.springframework.stereotype.Service;
import ua.alexcrow.flyway.dto.TransferData;
import ua.alexcrow.flyway.entity.Transfer;

import java.util.Optional;

@Service
public class TransferServiceImpl implements TransferService {

    private final ConverterService converterService;
    private final TransferDbService transferDbService;

    public TransferServiceImpl(ConverterService converterService, TransferDbService transferDbService) {
        this.converterService = converterService;
        this.transferDbService = transferDbService;
    }

    @Override
    public TransferData saveTransfer(TransferData transferData) {
        Transfer transfer = transferDbService.addOrUpdate(converterService.transferDataToEntity(transferData));
        return converterService.entityToTransferData(transfer);
    }

    @Override
    public Optional<Transfer> findOneOptional(long id) {
        return transferDbService.findTransfer(id);
    }

    @Override
    public void deleteTransfer(long id) {
        transferDbService.delete(id);
    }
}
