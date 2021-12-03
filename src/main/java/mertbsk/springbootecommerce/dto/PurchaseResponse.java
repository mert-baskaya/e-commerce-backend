package mertbsk.springbootecommerce.dto;

import lombok.Data;

// Bu sınıf Java sınıfını JSON cevabı olarak döndürecek
@Data
public class PurchaseResponse {
	// Lombok @NonNull veya final alanlarını constructor'a ekler
	private final String orderTrackingNumber;

}
