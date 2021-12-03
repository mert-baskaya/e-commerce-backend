package mertbsk.springbootecommerce.dto;

import lombok.Data;
import mertbsk.springbootecommerce.entities.Address;
import mertbsk.springbootecommerce.entities.Customer;
import mertbsk.springbootecommerce.entities.Order;
import mertbsk.springbootecommerce.entities.OrderItem;

import java.util.Set;

@Data
public class Purchase {

	private Customer customer;
	private Address shippingAddress;
	private Address billingAddress;
	private Order order;
	private Set<OrderItem> orderItems;

}
