package com.cts.projectBus.ProBus.entity;

import java.time.LocalDate;
import java.util.List;

import com.cts.projectBus.ProBus.model.BusOutputModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionId;
	private int noOfPassenger;
	private double totalPrice;
	private LocalDate transactionDate;
	private String tripDate;
	private String discount;
	@ManyToOne
	@JoinColumn(name="email")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="numberPlate")
	private Bus bus;
	
	@OneToMany(mappedBy = "transaction",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Passenger>Passengers;


}
