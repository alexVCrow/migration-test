package ua.alexcrow.flyway.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class TransferData implements Serializable {

    private Long id;
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]{5,20}")
    private String transfer;
    @NotNull
    @Pattern(regexp = "[A-Z]{2}")
    private String sendCountry;
    @NotNull
    @Pattern(regexp = "[A-Z]{2}")
    private String receiveCountry;
}
