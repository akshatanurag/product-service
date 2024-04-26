package org.scaler.scalerstore.models;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
public class Product extends BaseModel {
    private String title;
    private Double price;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Category category;
    private String description;
    private String imageUrl;

    public Product() {

    }
}
