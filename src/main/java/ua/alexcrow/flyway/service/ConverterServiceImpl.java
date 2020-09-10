package ua.alexcrow.flyway.service;

import org.springframework.stereotype.Service;
import ua.alexcrow.flyway.dto.TransferData;
import ua.alexcrow.flyway.entity.Transfer;

@Service
public class ConverterServiceImpl implements ConverterService {

    @Override
    public Transfer transferDataToEntity(TransferData transferData) {
        Transfer transfer = new Transfer();
        transfer.setId(transferData.getId());
        transfer.setReference(transferData.getTransfer());
        transfer.setReceiverCountry(transferData.getReceiveCountry());
        transfer.setSenderCountry(transferData.getSendCountry());
        return transfer;
    }

    @Override
    public TransferData entityToTransferData(Transfer transfer) {
        return TransferData.builder()
                .id(transfer.getId())
                .receiveCountry(transfer.getReceiverCountry())
                .sendCountry(transfer.getSenderCountry())
                .transfer(transfer.getReference())
                .build();
    }
}
