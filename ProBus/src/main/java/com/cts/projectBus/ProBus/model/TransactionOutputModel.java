
package com.cts.projectBus.ProBus.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TransactionOutputModel {
	private int transactionId;
	private int noOfPassenger;
	private int seatPrice;
	private double totalPrice;
	private LocalDate transactionDate;
	private String tripDate;
	private String numberPlate;
    private String busName;
	private String startingTime;
	private String arrivalTime;
	private int duration;
	private String email;
	private String discount;
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) {
	            return true;
	        }
	        if (o == null || getClass() != o.getClass()) {
	            return false;
	        }
	        TransactionOutputModel that = (TransactionOutputModel) o;
	        return transactionId == that.transactionId &&
	                noOfPassenger == that.noOfPassenger &&
	                Double.compare(that.seatPrice, seatPrice) == 0 &&
	                Double.compare(that.totalPrice, totalPrice) == 0 &&
	                Objects.equals(transactionDate, that.transactionDate) &&
	                Objects.equals(tripDate, that.tripDate) &&
	                Objects.equals(numberPlate, that.numberPlate) &&
	                Objects.equals(busName, that.busName) &&
	                Objects.equals(startingTime, that.startingTime) &&
	                Objects.equals(arrivalTime, that.arrivalTime) &&
	                duration == that.duration &&
	                Objects.equals(email, that.email) &&
	                Objects.equals(discount, that.discount);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(transactionId, noOfPassenger, seatPrice, totalPrice, transactionDate, tripDate, numberPlate, busName, startingTime, arrivalTime, duration, email, discount);
	    }
}
