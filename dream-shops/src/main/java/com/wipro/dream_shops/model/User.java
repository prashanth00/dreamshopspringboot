package com.wipro.dream_shops.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.NaturalId;

import com.wipro.dream_shops.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	@NaturalId
	private String email;
	private String password;
	
	@OneToOne(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval=true)
	private Cart cart;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Order> order;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade= {
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH
	})
	private Collection<Role> roles=new HashSet<>();
}
