package com.sbz.bank.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sbz.bank.model.BaseEntity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="book")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book extends BaseEntity {

	@Column
	String name;

	@Column
	LocalDate publishDate;

	@Column
	int numberOfCopies;

	@Column
	LocalDate addedToBookstoreDate;

	@Column
	double price;

}
