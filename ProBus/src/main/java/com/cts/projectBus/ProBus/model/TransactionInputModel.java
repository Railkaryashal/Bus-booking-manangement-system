package com.cts.projectBus.ProBus.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TransactionInputModel {
	private String numberPlate;
	private String source;
	private String destination;
	private String startingTime;
	private int noOfPassenger;
	private int transactionId;
	private String tripDate;
	private String email;
	private List<PassengerModel> passengerList;

}