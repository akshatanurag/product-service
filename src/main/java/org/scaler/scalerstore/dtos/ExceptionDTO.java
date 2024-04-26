package org.scaler.scalerstore.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDTO {
    private ResStatus status;
    private String message;
}
