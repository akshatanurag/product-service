package org.scaler.scalerstore.repositories.projections;

import org.scaler.scalerstore.models.Product;

import java.util.List;

public interface CategoryWithName {
    String getName();
    List<Product> getProducts();
}
