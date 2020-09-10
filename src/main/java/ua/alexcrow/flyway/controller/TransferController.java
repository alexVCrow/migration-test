package ua.alexcrow.flyway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.alexcrow.flyway.dto.TransferData;
import ua.alexcrow.flyway.entity.Transfer;
import ua.alexcrow.flyway.exception.TransferNotFoundException;
import ua.alexcrow.flyway.service.TransferService;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("transfer")
public class TransferController {

    private final TransferService transferService;

    private static final String ERROR = "Transfer not found: %s";

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transfer> find(@Valid @PathVariable long id){
        Optional<Transfer> optionalTransfer = transferService.findOneOptional(id);
        if(!optionalTransfer.isPresent()){
            throw new TransferNotFoundException(String.format(ERROR, id));
        }
        return ResponseEntity.ok(optionalTransfer.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable long id){
        if(transferService.findOneOptional(id).isPresent()){
            transferService.deleteTransfer(id);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<TransferData> create(@Valid @RequestBody TransferData transferData) {
        if(Objects.nonNull(transferData.getId())){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.saveTransfer(transferData));
    }

    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody TransferData transferData) {
        if(Objects.isNull(transferData.getId()) || !transferService.findOneOptional(transferData.getId()).isPresent()){
            throw new TransferNotFoundException(String.format(ERROR, transferData.getId()));
        }
        transferService.saveTransfer(transferData);
        return ResponseEntity.noContent().build();
    }

}
