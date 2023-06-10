package com.cts.projectBus.ProBus.entity;


import org.springframework.stereotype.Service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int passengerId;
	private String passengerName;
	@Column(nullable=true)
	private int passengerAge;
	private String passengerGender;
	
	@ManyToOne
    @JoinColumn(name = "transactionId")
    private Transaction transaction;

	
	
	
}

