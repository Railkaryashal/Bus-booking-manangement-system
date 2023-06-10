package com.cts.projectBus.ProBus.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BusInputModel {
	private String numberPlate;
	private String busName;
	private String source;
	private String destination;
	private int capacity;
	private int seatPrice;
	private String startingTime;
	private String arrivalTime;
	
}
