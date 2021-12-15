package mertbsk.springbootecommerce.dao;

import mertbsk.springbootecommerce.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	// Veritabanında aranan bulunamazsa `null` döndürür
	Customer findByEmail(String email);
	
}
