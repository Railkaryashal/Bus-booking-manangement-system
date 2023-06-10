package com.cts.projectBus.ProBus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.projectBus.ProBus.entity.Bus;
import com.cts.projectBus.ProBus.entity.Passenger;
import com.cts.projectBus.ProBus.entity.Transaction;
import com.cts.projectBus.ProBus.exception.TransactionException;
import com.cts.projectBus.ProBus.model.BusOutputModel;
import com.cts.projectBus.ProBus.model.PassengerModel;
import com.cts.projectBus.ProBus.model.TransactionInputModel;
import com.cts.projectBus.ProBus.model.TransactionOutputModel;
import com.cts.projectBus.ProBus.repository.PassengerRepository;
import com.cts.projectBus.ProBus.util.Mapper;

@Service
public class PassengerService {

	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	TransactionService transactionService;
	

    public void save(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    public void saveAll(List<Passenger> passengerList) {
        passengerRepository.saveAll(passengerList);
    }
    public Passenger getPassengerById(int id) {
        return passengerRepository.findById(id).orElse(null);
    }
}
