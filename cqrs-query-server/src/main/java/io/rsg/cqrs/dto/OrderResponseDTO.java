package io.rsg.cqrs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class OrderResponseDTO {
    private String message;
    private boolean success;
}
