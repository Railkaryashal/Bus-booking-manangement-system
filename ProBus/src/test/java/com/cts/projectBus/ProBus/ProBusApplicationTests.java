package com.cts.projectBus.ProBus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.projectBus.ProBus.entity.*;
import com.cts.projectBus.ProBus.exception.BusNotFoundException;
import com.cts.projectBus.ProBus.exception.TransactionException;
import com.cts.projectBus.ProBus.exception.UserNotFoundException;
import com.cts.projectBus.ProBus.model.*;
import com.cts.projectBus.ProBus.repository.*;
import com.cts.projectBus.ProBus.service.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;

import java.util.Optional;

@SpringBootTest

class ProBusApplicationTests {
	@Mock
    private BusRepository busRepository;

    @InjectMocks
    private BusService busService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;
    
    @Test
    void testAddBus_WhenBusDoesNotExist_ReturnsBusOutputModel() throws BusNotFoundException {
        // Arrange
        BusInputModel busInputModel = new BusInputModel("123", "Bus A", "A", "B", 60, 100, "10", "12");
        Bus bus = new Bus("123", "Bus A", "A", "B", 60, 100, "10", "12", 2,null);
        when(busRepository.findById(anyString())).thenReturn(Optional.empty());
        when(busRepository.save(any(Bus.class))).thenReturn(bus);

        // Act
        BusOutputModel result = busService.addBus(busInputModel);

        // Assert
        assertNotNull(result);
        assertEquals("Bus A", result.getBusName());
    }

    @Test
    void testAddBus_WhenBusAlreadyExists_ThrowsBusNotFoundException() {
        // Arrange
        BusInputModel busInputModel = new BusInputModel("123", "Bus A", "A", "B", 60, 100, "10", "12");
        when(busRepository.findById(anyString())).thenReturn(Optional.of(new Bus()));

        // Act and Assert
        assertThrows(BusNotFoundException.class, () -> {
            busService.addBus(busInputModel);
        });
    }
    
    @Test
    void testDeleteBusById_WhenBusExists_DeletesBusSuccessfully() throws BusNotFoundException {
        // Arrange
        String id = "123";
        when(busRepository.existsById(anyString())).thenReturn(true);

        // Act
        assertDoesNotThrow(() -> {
            busService.deleteBusById(id);
        });

        // Assert
        verify(busRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteBusById_WhenBusDoesNotExist_ThrowsBusNotFoundException() {
        // Arrange
        String id = "123";
        when(busRepository.existsById(anyString())).thenReturn(false);

        // Act and Assert
        assertThrows(BusNotFoundException.class, () -> {
            busService.deleteBusById(id);
        });

        // Verify that deleteById is not called
        verify(busRepository, never()).deleteById(anyString());
    }
    
    @Test
    void testGetBusByNumberPlate_WhenBusExists_ReturnsBusOutputModel() throws BusNotFoundException {
        // Arrange
        String numberPlate = "123";
        Bus bus = new Bus("123", "Bus A", "A", "B", 60, 100, "10", "12", 2,null);
        when(busRepository.findById(anyString())).thenReturn(Optional.of(bus));

        // Act
        BusOutputModel result = busService.getBusByNumberPlate(numberPlate);

        // Assert
        assertNotNull(result);
        assertEquals("Bus A", result.getBusName());
    }

    @Test
    void testGetBusByNumberPlate_WhenBusDoesNotExist_ThrowsBusNotFoundException() {
        // Arrange
        String numberPlate = "123";
        when(busRepository.findById(anyString())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(BusNotFoundException.class, () -> {
            busService.getBusByNumberPlate(numberPlate);
        });
    }
    
    @Test
    void testGetAllBuses_ReturnsBusOutputModels() {
        // Arrange
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus("123", "Bus A", "A", "B", 60,100, "10", "12", 2,null));
        buses.add(new Bus("456", "Bus B", "A", "B", 70,200,"10","12",2,null));
        when(busRepository.findAll()).thenReturn(buses);

        // Act
        List<BusOutputModel> result = busService.getAllBuses();

        // Assert
        assertEquals(2, result.size());
    }
    
    @Test
    void testSearchPurticularBus_WhenBusesExist_ReturnsBusOutputModels() throws BusNotFoundException {
        // Arrange
        String busName = "Bus A";
        String source = "A";
        String destination = "B";
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus("123", "Bus A", "A", "B", 60,100, "10", "12", 2,null));
        when(busRepository.searchPurticularBus(anyString(), anyString(), anyString())).thenReturn(buses);

        // Act
        List<BusOutputModel> result = busService.searchPurticularBus(busName, source, destination);

        // Assert
        assertEquals(1, result.size());
    }
    @Test
    void testSearchPurticularBus_WhenNoBusesExist_ThrowsBusNotFoundException() {
        // Arrange
        String busName = "Bus A";
        String source = "A";
        String destination = "B";
        when(busRepository.searchPurticularBus(anyString(), anyString(), anyString())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertThrows(BusNotFoundException.class, () -> {
            busService.searchPurticularBus(busName, source, destination);
        });
    }
    
    @Test
    void testSearchBus_WhenBusesExist_ReturnsBusOutputModels() throws BusNotFoundException {
        // Arrange
        String source = "A";
        String destination = "B";
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus("123", "Bus A", "A", "B", 60,100, "10", "12", 2,null));
        buses.add(new Bus("456", "Bus B", "A", "B", 70,200,"10","12",2,null));
        when(busRepository.searchBus(anyString(), anyString())).thenReturn(buses);

        // Act
        List<BusOutputModel> result = busService.searchBus(source, destination);

        // Assert
        assertEquals(2, result.size());
    }
    @Test
    void testSearchBus_WhenNoBusesExist_ThrowsBusNotFoundException() {
        // Arrange
        String source = "A";
        String destination = "B";
        when(busRepository.searchBus(anyString(), anyString())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertThrows(BusNotFoundException.class, () -> {
            busService.searchBus(source, destination);
        });
    }

    @Test
    void testGetAllBusCapacityMoreThanRequired_WhenBusesExist_ReturnsBusOutputModels() throws BusNotFoundException {
        // Arrange
        int capacity = 50;
        String source = "A";
        String destination = "B";
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus("123", "Bus A", "A", "B", 60,100, "10", "12", 2,null));
        buses.add(new Bus("456", "Bus B", "A", "B", 70,200,"10","12",2,null));
        when(busRepository.findBySourceAndDestinationAndCapacityGreaterThanEqual(anyString(), anyString(), anyInt())).thenReturn(buses);

        // Act
        List<BusOutputModel> result = busService.getAllBusCapacityMoreThanRequired(capacity, source, destination);

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void testGetAllBusCapacityMoreThanRequired_WhenNoBusesExist_ThrowsBusNotFoundException() {
        // Arrange
        int capacity = 50;
        String source = "A";
        String destination = "B";
        when(busRepository.findBySourceAndDestinationAndCapacityGreaterThanEqual(anyString(), anyString(), anyInt())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertThrows(BusNotFoundException.class, () -> {
            busService.getAllBusCapacityMoreThanRequired(capacity, source, destination);
        });
    }
    

}
