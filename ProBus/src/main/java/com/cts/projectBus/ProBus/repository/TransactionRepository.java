
package com.cts.projectBus.ProBus.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.projectBus.ProBus.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	@Query (value = "select sum(t.noOfPassenger) from Transaction t where t.bus.numberPlate=?1 and t.tripDate=?2")
	Integer getTotalSeatsBooked(String numberPlate, String tripDate);
}
