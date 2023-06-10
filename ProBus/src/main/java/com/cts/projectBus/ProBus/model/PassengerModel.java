package com.cts.projectBus.ProBus.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassengerModel {
	private String passengerName;
	private int passengerAge;
	private String passengerGender;
	private int transactionId;

}
