package com.cts.projectBus.ProBus.service;

import java.time.LocalDate;

import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.projectBus.ProBus.entity.Bus;
import com.cts.projectBus.ProBus.entity.Passenger;
import com.cts.projectBus.ProBus.entity.Transaction;
import com.cts.projectBus.ProBus.entity.User;
import com.cts.projectBus.ProBus.exception.BusNotFoundException;
import com.cts.projectBus.ProBus.exception.UserNotFoundException;
import com.cts.projectBus.ProBus.model.BusInputModel;
import com.cts.projectBus.ProBus.model.BusOutputModel;
import com.cts.projectBus.ProBus.model.PassengerModel;
import com.cts.projectBus.ProBus.model.TransactionInputModel;
import com.cts.projectBus.ProBus.model.TransactionOutputModel;
import com.cts.projectBus.ProBus.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	BusService busService;
	@Autowired
	UserService userService;
	@Autowired
	PassengerService passengerService;
	
	boolean discount1=false;
	@Transactional
	public TransactionOutputModel makeTransaction(TransactionInputModel transactionInputModel) throws BusNotFoundException, TransactionException, UserNotFoundException {
		// Get the bus details
		BusOutputModel busOutputModel = busService.getBusByNumberPlate(transactionInputModel.getNumberPlate());
		
		// Check the total seats booked for the given bus 
		Integer res=transactionRepository.getTotalSeatsBooked(transactionInputModel.getNumberPlate(),transactionInputModel.getTripDate());
		int totalSeatsBooked=0;
		if(res!=null)
				totalSeatsBooked=res.intValue();
		
		// Check if there are enough seats available for the transaction
		long available_seats=busOutputModel.getCapacity()-totalSeatsBooked;
		double required_capacity=busOutputModel.getCapacity()-(transactionInputModel.getNoOfPassenger()+totalSeatsBooked);
		if(required_capacity<0)
			throw new TransactionException("No seats are available, only "+ available_seats+ " Seats are available");
		
		// Get the user details
		User user=userService.getUserByEmail(transactionInputModel.getEmail());
		
		// Create a new transaction entity
		Transaction transaction = new Transaction();
		Bus bus=new Bus();
		bus.setNumberPlate(transactionInputModel.getNumberPlate());
			transaction.setBus(bus);
			transaction.setNoOfPassenger(transactionInputModel.getNoOfPassenger());
			transaction.setUser(user);
			
			// Calculate the total price of the transaction based on the number of passengers and seat price
			if (user.getNoOfTimesBooking()>2) {
				discount1=true;
				transaction.setDiscount("10%");				
				transaction.setTotalPrice((transactionInputModel.getNoOfPassenger()*busOutputModel.getSeatPrice())-(transactionInputModel.getNoOfPassenger()*busOutputModel.getSeatPrice())*0.1);
				//transaction.setDiscount(transaction.getDiscount());
			}else {
				transaction.setTotalPrice(transactionInputModel.getNoOfPassenger()*busOutputModel.getSeatPrice());
			} 
			transaction.setTransactionDate(LocalDate.now());
			transaction.setTransactionId(transactionInputModel.getTransactionId());
			transaction.setTripDate(transactionInputModel.getTripDate());
			
			// Save the transaction to the database
			transaction = transactionRepository.save(transaction);
			
			// Create a new passenger entity for each passenger in the transaction
			List<Passenger> passengerList = new ArrayList<>();
			for(int i=0; i<transactionInputModel.getNoOfPassenger(); i++){
			    PassengerModel passengerModel = transactionInputModel.getPassengerList().get(i);
			    Passenger passenger = new Passenger();
			    passenger.setTransaction(transaction);
			    passenger.setPassengerName(passengerModel.getPassengerName());
			    passenger.setPassengerAge(passengerModel.getPassengerAge());
			    passenger.setPassengerGender(passengerModel.getPassengerGender());
			    passengerList.add(passenger);
			}

			// Save the passenger entities to the database
			passengerService.saveAll(passengerList);

			// Create and return the transaction output model
			TransactionOutputModel transactionOutputModel = new TransactionOutputModel();
			transactionOutputModel.setArrivalTime(busOutputModel.getArrivalTime());
			transactionOutputModel.setNumberPlate(busOutputModel.getNumberPlate());
			transactionOutputModel.setBusName(busOutputModel.getBusName());
			transactionOutputModel.setDuration(busOutputModel.getDuration());
			transactionOutputModel.setNoOfPassenger(transaction.getNoOfPassenger());
			transactionOutputModel.setSeatPrice(busOutputModel.getSeatPrice());
			if(discount1==true) {
				transactionOutputModel.setDiscount(transaction.getDiscount());
			}
			else {
				transactionOutputModel.setDiscount(null);
			}
			transactionOutputModel.setStartingTime(busOutputModel.getStartingTime());
			transactionOutputModel.setTotalPrice(transaction.getTotalPrice());
			transactionOutputModel.setTransactionDate(transaction.getTransactionDate());
			transactionOutputModel.setTransactionId(transaction.getTransactionId());
	        transactionOutputModel.setTripDate(transaction.getTripDate());
	        transactionOutputModel.setEmail(user.getEmail());
			return transactionOutputModel;
	}

//    public TransactionOutputModel getTransactionById(int transactionId) throws TransactionException {
//        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
//
//        if (transactionOptional.isPresent()) {
//            Transaction transaction = transactionOptional.get();
//            // Map the Transaction entity to the TransactionOutputModel
//            TransactionOutputModel transactionOutputModel = mapTransactionToOutputModel(transaction);
//            return transactionOutputModel;
//        } else {
//            throw new TransactionException("Transaction not found with ID: " + transactionId);
//        }
//    }
//
//    private TransactionOutputModel mapTransactionToOutputModel(Transaction transaction) {
//        TransactionOutputModel outputModel = new TransactionOutputModel();
//        outputModel.setTransactionId(transaction.getTransactionId());
//        outputModel.setTotalPrice(transaction.getTotalPrice());
//        outputModel.setTransactionDate(transaction.getTransactionDate());
//        outputModel.setTripDate(transaction.getTripDate());
//        outputModel.setDiscount(transaction.getDiscount());
//        return outputModel;
//    }
}
