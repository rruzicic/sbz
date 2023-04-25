package com.sbz.bookstore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "book_order")
public class Order extends BaseEntity{
	@ManyToOne
	User user;

	@Column
	double discount;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("orderBackReference")
	List<Item> items;
}
