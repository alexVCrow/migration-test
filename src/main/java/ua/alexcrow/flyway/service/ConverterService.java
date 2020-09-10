package ua.alexcrow.flyway.service;

import ua.alexcrow.flyway.dto.TransferData;
import ua.alexcrow.flyway.entity.Transfer;

public interface ConverterService {

    Transfer transferDataToEntity(TransferData transferData);

    TransferData entityToTransferData(Transfer transfer);

}
