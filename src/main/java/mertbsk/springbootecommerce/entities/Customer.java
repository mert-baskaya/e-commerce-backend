package mertbsk.springbootecommerce.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "customer")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private Set<Order> orders = new HashSet<>();

	// TODO SOLID Prensiplerine karşı gelindi, düzeltilmesi gerek!
	public void add(Order order) {
		if(order != null){
			if (orders == null){
				orders = new HashSet<>();
			}

			orders.add(order);
			order.setCustomer(this);
		}
	}

}