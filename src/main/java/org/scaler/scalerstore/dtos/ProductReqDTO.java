package org.scaler.scalerstore.dtos;

import lombok.Getter;
import lombok.Setter;
import org.scaler.scalerstore.models.Category;

@Getter
@Setter
public class ProductReqDTO {
    private String title;
    private double price;
    private String description;
    private Category category;
    private String image;
}
