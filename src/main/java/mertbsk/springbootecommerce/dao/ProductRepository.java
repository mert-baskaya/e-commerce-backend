package mertbsk.springbootecommerce.dao;

import mertbsk.springbootecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}