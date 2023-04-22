package com.sbz.bookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	@JsonBackReference("orderBackReference")
	Order order;

	@Column
	int discount;

	@Column
	int quantity;
}
