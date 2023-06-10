package com.cts.projectBus.ProBus;

import com.cts.projectBus.ProBus.controller.TransactionController;
import com.cts.projectBus.ProBus.exception.BusNotFoundException;
import com.cts.projectBus.ProBus.exception.TransactionException;
import com.cts.projectBus.ProBus.exception.UserNotFoundException;
import com.cts.projectBus.ProBus.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.cts.projectBus.ProBus.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMakeTransaction_ValidInput_ReturnsTransactionOutputModel() throws BusNotFoundException, TransactionException, UserNotFoundException {
        // Arrange
        TransactionInputModel transactionInputModel = new TransactionInputModel();
        // Set transaction input model properties as needed
        transactionInputModel.setNumberPlate("ABC123");
        transactionInputModel.setTripDate("2023-05-30");
        transactionInputModel.setEmail("test@example.com");
        transactionInputModel.setNoOfPassenger(3);

        List<PassengerModel> passengerList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            PassengerModel passengerModel = new PassengerModel();
            passengerModel.setPassengerName("Passenger " + (i + 1));
            passengerModel.setPassengerAge(25);
            passengerModel.setPassengerGender("Male");
            passengerList.add(passengerModel);
        }
        transactionInputModel.setPassengerList(passengerList);
        
        TransactionOutputModel expectedOutput = new TransactionOutputModel();
        // Set expected transaction output model properties as needed
        expectedOutput.setTransactionId(1);
        expectedOutput.setNoOfPassenger(3);
        expectedOutput.setSeatPrice(50);
        expectedOutput.setTotalPrice(150);
        expectedOutput.setTransactionDate(LocalDate.now());
        expectedOutput.setTripDate("2023-05-30");
        expectedOutput.setNumberPlate("ABC123");
        expectedOutput.setStartingTime("...");
        expectedOutput.setArrivalTime("...");
        expectedOutput.setDuration(2);
        expectedOutput.setEmail("test@example.com");
        
        when(transactionService.makeTransaction(transactionInputModel)).thenReturn(expectedOutput);

        // Act
        TransactionOutputModel response = transactionService.makeTransaction(transactionInputModel);

        // Assert
        assertEquals(expectedOutput, response);
        verify(transactionService).makeTransaction(transactionInputModel);
    }

    @Test
    void testMakeTransaction_InvalidInput_ReturnsErrorResponse() throws BusNotFoundException, TransactionException, UserNotFoundException {
        // Arrange
        TransactionInputModel transactionInputModel = new TransactionInputModel();
        // Set transaction input model properties as needed
        transactionInputModel.setNumberPlate("ABC123");
        transactionInputModel.setTripDate("2023-05-30");
        transactionInputModel.setEmail("test@example.com");
        transactionInputModel.setNoOfPassenger(3);

        List<PassengerModel> passengerList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            PassengerModel passengerModel = new PassengerModel();
            passengerModel.setPassengerName("Passenger " + (i + 1));
            passengerModel.setPassengerAge(25);
            passengerModel.setPassengerGender("Male");
            passengerList.add(passengerModel);
        }
        transactionInputModel.setPassengerList(passengerList);

        // Simulate an exception being thrown by the service
        when(transactionService.makeTransaction(transactionInputModel)).thenThrow(new BusNotFoundException("Bus not found"));

        // Act and Assert
        assertThrows(BusNotFoundException.class, () -> transactionController.makeTransaction(transactionInputModel));
        verify(transactionService).makeTransaction(transactionInputModel);
    }


}
