package com.cts.projectBus.ProBus;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cts.projectBus.ProBus.entity.Passenger;
import com.cts.projectBus.ProBus.entity.Transaction;
import com.cts.projectBus.ProBus.repository.PassengerRepository;
import com.cts.projectBus.ProBus.repository.TransactionRepository;
import com.cts.projectBus.ProBus.service.PassengerService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class PassengerServiceTest {

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private PassengerService passengerService;

    @Test
    public void testSavePassenger() {
        // Arrange
        Passenger passenger = new Passenger();
        passenger.setPassengerId(1);
        passenger.setPassengerName("John Doe");
        passenger.setPassengerAge(30);
        passenger.setPassengerGender("Male");

        // Act
        passengerService.save(passenger);

        // Assert
        verify(passengerRepository).save(passenger);
    }

    @Test
    public void testGetPassengerById() {
        // Arrange
        int passengerId = 1;
        Passenger passenger = new Passenger();
        passenger.setPassengerId(passengerId);
        passenger.setPassengerName("John Doe");

        when(passengerRepository.findById(passengerId)).thenReturn(Optional.of(passenger));

        // Act
        Passenger retrievedPassenger = passengerService.getPassengerById(passengerId);

        // Assert
        assertNotNull(retrievedPassenger);
        assertEquals(passengerId, retrievedPassenger.getPassengerId());
        assertEquals("John Doe", retrievedPassenger.getPassengerName());
    }
}
