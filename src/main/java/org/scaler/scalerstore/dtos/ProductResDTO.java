package org.scaler.scalerstore.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.message.Message;
import org.scaler.scalerstore.models.Product;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResDTO {
    private ResStatus status;
    private String message;
    private Product product;
}
