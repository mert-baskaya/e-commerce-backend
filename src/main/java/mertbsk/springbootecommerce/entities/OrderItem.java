package mertbsk.springbootecommerce.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "order_item")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "unit_price", precision = 19, scale = 2)
	private BigDecimal unitPrice;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "product_id")
	private Long productId;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

}