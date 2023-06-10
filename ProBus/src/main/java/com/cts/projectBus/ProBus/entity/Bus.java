package com.cts.projectBus.ProBus.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bus {
	@Id
	private String numberPlate;
	private String busName;
	private String source;
	private String destination;
	private int capacity;
	private int seatPrice;
	private String startingTime;
	private String arrivalTime;
	private int duration;
	@OneToMany(mappedBy = "bus")
	@JsonIgnoreProperties("bus") 
	private List<Transaction> transactionList;
}
