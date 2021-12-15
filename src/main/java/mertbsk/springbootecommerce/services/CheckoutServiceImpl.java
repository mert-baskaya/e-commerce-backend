package mertbsk.springbootecommerce.services;

import mertbsk.springbootecommerce.dao.CustomerRepository;
import mertbsk.springbootecommerce.dto.Purchase;
import mertbsk.springbootecommerce.dto.PurchaseResponse;
import mertbsk.springbootecommerce.entities.Customer;
import mertbsk.springbootecommerce.entities.Order;
import mertbsk.springbootecommerce.entities.OrderItem;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

	private final CustomerRepository customerRepository;

	public CheckoutServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {

		// Siparişi dto'dan al
		Order order = purchase.getOrder();

		// Takip numarası üret
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);

		// Siparişi orderItems ile doldur
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		//Alternatif: orderItems.forEach(order::add);

		// Siparişi fatura adresi ve teslimat adresi ile doldur
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());

		// Müşteriye sipariş bilgisini ekle
		Customer customer = purchase.getCustomer();
		
		// Müşterinin veritabanında olup olmadığını kontrol et
		String email = customer.getEmail();
		Customer customerFromDB = customerRepository.findByEmail(email);
		if (customerFromDB != null){
			// Müşteri önceden kaydolduysa metodda kullanılan müşteriye ata
			customer = customerFromDB;
		}
		
		customer.add(order);

		// Veritabanına kaydet
		customerRepository.save(customer);

		// Bir cevap döndür
		return new PurchaseResponse(orderTrackingNumber);
	}

	private String generateOrderTrackingNumber() {
		// Tahmin etmesi zor ve benzersiz bir takip numarası üretilmeli
		// Rastgele bir UUID (Universally Unique ID Version-4) oluştur, kaynak: https://en.wikipedia.org/wiki/Universally_unique_identifier
		// Dipnot: Üretilecek id'nin kesinlikle tekrar etmesi istenmiyorsa id üretildikten sonra veritabanında tekrarı olup olmadığına bakılıp yeniden üretilir
		return UUID.randomUUID().toString();
	}
}
