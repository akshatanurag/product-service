package org.scaler.scalerstore.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
}
