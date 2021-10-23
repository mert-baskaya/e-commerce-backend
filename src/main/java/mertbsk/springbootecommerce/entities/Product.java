package mertbsk.springbootecommerce.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "product")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	//Stock-keeping number
	@Column(name = "sku")
	private String sku;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "unit_price", precision = 19, scale = 2)
	private BigDecimal unitPrice;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "units_in_stock")
	private Integer unitsInStock;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Temporal(TemporalType.DATE)
	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private ProductCategory category;

}