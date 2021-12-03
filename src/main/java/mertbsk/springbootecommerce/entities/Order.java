package mertbsk.springbootecommerce.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table(name = "orders")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "order_tracking_number")
	private String orderTrackingNumber;

	@Column(name = "total_price", precision = 19, scale = 2)
	private BigDecimal totalPrice;

	@Column(name = "total_quantity")
	private int totalQuantity;

	@Column(name = "status")
	private String status;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_created")
	@UpdateTimestamp
	private Date dateCreated;

	@Temporal(TemporalType.DATE)
	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private Set<OrderItem> orderItems = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
	private Address shippingAddress;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "billing_address_id", referencedColumnName = "id")
	private Address billingAddress;

	// TODO SOLID Prensiplerine karşı gelindi, düzeltilmesi gerek!
	public void add(OrderItem item) {
		if(item != null){
			if (orderItems == null){
				orderItems = new HashSet<>();
			}

			orderItems.add(item);
			item.setOrder(this);
		}
	}

}