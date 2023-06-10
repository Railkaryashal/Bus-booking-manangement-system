package com.cts.projectBus.ProBus;

import com.cts.projectBus.ProBus.entity.User;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cts.projectBus.ProBus.entity.Transaction;
import com.cts.projectBus.ProBus.exception.BusNotFoundException;
import com.cts.projectBus.ProBus.exception.TransactionException;
import com.cts.projectBus.ProBus.exception.UserNotFoundException;
import com.cts.projectBus.ProBus.model.BusOutputModel;
import com.cts.projectBus.ProBus.model.PassengerModel;
import com.cts.projectBus.ProBus.model.TransactionInputModel;
import com.cts.projectBus.ProBus.model.TransactionOutputModel;
import com.cts.projectBus.ProBus.repository.TransactionRepository;
import com.cts.projectBus.ProBus.service.BusService;
import com.cts.projectBus.ProBus.service.PassengerService;
import com.cts.projectBus.ProBus.service.TransactionService;
import com.cts.projectBus.ProBus.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TransactionServiceTest {

    @Mock
    private BusService busService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private UserService userService;

    @Mock
    private PassengerService passengerService;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMakeTransaction_ValidInput_ReturnsTransactionOutputModel() throws BusNotFoundException, TransactionException, UserNotFoundException {
        // Arrange
        String numberPlate = "ABC123";
        String tripDate = "2023-05-30";
        String email = "test@example.com";
        int noOfPassenger = 3;

        // Mock bus details
        BusOutputModel busOutputModel = new BusOutputModel();
        busOutputModel.setNumberPlate(numberPlate);
        busOutputModel.setCapacity(10);
        busOutputModel.setSeatPrice(50);

        when(busService.getBusByNumberPlate(numberPlate)).thenReturn(busOutputModel);

        // Mock total seats booked
        int totalSeatsBooked = 5;
        when(transactionRepository.getTotalSeatsBooked(numberPlate, tripDate)).thenReturn(totalSeatsBooked);

        // Mock user details
        User user = new User();
        user.setEmail(email);
        user.setNoOfTimesBooking(3);
        when(userService.getUserByEmail(email)).thenReturn(user);

        // Mock transaction entity
        Transaction savedTransaction = new Transaction();
        savedTransaction.setTransactionId(1);
        savedTransaction.setNoOfPassenger(noOfPassenger);
        savedTransaction.setTotalPrice(150);
        savedTransaction.setTransactionDate(LocalDate.now());
        savedTransaction.setTripDate(tripDate);

        when(transactionRepository.save(any(Transaction.class))).thenReturn(savedTransaction);

        // Mock passenger list
        List<PassengerModel> passengerList = new ArrayList<>();
        for (int i = 0; i < noOfPassenger; i++) {
            PassengerModel passengerModel = new PassengerModel();
            passengerModel.setPassengerName("Passenger " + (i + 1));
            passengerModel.setPassengerAge(25);
            passengerModel.setPassengerGender("Male");
            passengerModel.setTransactionId(savedTransaction.getTransactionId());
            passengerList.add(passengerModel);
        }
        doNothing().when(passengerService).saveAll(anyList());

        // Expected output
        TransactionOutputModel expectedOutput = new TransactionOutputModel();
        expectedOutput.setTransactionId(savedTransaction.getTransactionId());
        expectedOutput.setNoOfPassenger(savedTransaction.getNoOfPassenger());
        expectedOutput.setSeatPrice(busOutputModel.getSeatPrice());
        expectedOutput.setTotalPrice(savedTransaction.getTotalPrice());
        expectedOutput.setTransactionDate(savedTransaction.getTransactionDate());
        expectedOutput.setTripDate(savedTransaction.getTripDate());
        expectedOutput.setNumberPlate(busOutputModel.getNumberPlate());
        expectedOutput.setStartingTime(busOutputModel.getStartingTime());
        expectedOutput.setArrivalTime(busOutputModel.getArrivalTime());
        expectedOutput.setDuration(busOutputModel.getDuration());
        expectedOutput.setEmail(user.getEmail());

        // Create input model
        TransactionInputModel inputModel = new TransactionInputModel();
        inputModel.setNumberPlate(numberPlate);
        inputModel.setTripDate(tripDate);
        inputModel.setEmail(email);
        inputModel.setNoOfPassenger(noOfPassenger);

        inputModel.setPassengerList(passengerList);

        // Act
        TransactionOutputModel result = transactionService.makeTransaction(inputModel);

        // Assert
        assertEquals(expectedOutput, result);
        verify(busService).getBusByNumberPlate(numberPlate);
        verify(transactionRepository).getTotalSeatsBooked(numberPlate, tripDate);
        verify(userService).getUserByEmail(email);
        verify(transactionRepository).save(any(Transaction.class));
        verify(passengerService).saveAll(anyList());
    }
}
