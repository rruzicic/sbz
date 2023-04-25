package com.sbz.bookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "item")
public class Item extends BaseEntity{
	@OneToOne
	Book book;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	@JsonBackReference("orderBackReference")
	Order order;

	@Column
	double discount;

	@Column
	int quantity;
}
