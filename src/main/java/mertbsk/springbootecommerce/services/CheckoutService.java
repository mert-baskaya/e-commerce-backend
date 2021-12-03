package mertbsk.springbootecommerce.services;

import mertbsk.springbootecommerce.dto.Purchase;
import mertbsk.springbootecommerce.dto.PurchaseResponse;

public interface CheckoutService {

	PurchaseResponse placeOrder(Purchase purchase);

}
