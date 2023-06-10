package com.cts.projectBus.ProBus;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;

import com.cts.projectBus.ProBus.controller.BusController;
import com.cts.projectBus.ProBus.entity.Bus;
import com.cts.projectBus.ProBus.exception.BusNotFoundException;
import com.cts.projectBus.ProBus.model.BusInputModel;
import com.cts.projectBus.ProBus.model.BusOutputModel;
import com.cts.projectBus.ProBus.service.BusService;

class BusControllerTest {

    @Mock
    private BusService busService;

    @InjectMocks
    private BusController busController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSearchBus_ValidInput_ReturnsListOfBuses() throws BusNotFoundException {
        // Arrange
        String source = "CityA";
        String destination = "CityB";
        List<BusOutputModel> expectedOutput = new ArrayList<>();
        // Set expected output list of buses as needed
        expectedOutput.add(new BusOutputModel("ABC123", "Bus1", "CityA", "CityB", 50, 10, "10", "12", 2));

        when(busService.searchBus(source, destination)).thenReturn(expectedOutput);

        // Act
        List<BusOutputModel> result = busController.searchBus(source, destination);

        // Assert
        assertEquals(expectedOutput, result);
        verify(busService).searchBus(source, destination);
    }

    @Test
    void testSearchPurticularBus_ValidInput_ReturnsListOfBuses() throws BusNotFoundException {
        // Arrange
        String busName = "MyBus";
        String source = "CityA";
        String destination = "CityB";
        List<BusOutputModel> expectedOutput = new ArrayList<>();
        // Set expected output list of buses as needed
        expectedOutput.add(new BusOutputModel("ABC123", "MyBus", "CityA", "CityB", 50, 10, "10", "12", 2));

        when(busService.searchPurticularBus(busName, source, destination)).thenReturn(expectedOutput);

        // Act
        List<BusOutputModel> result = busController.searchPurticularBus(busName, source, destination);

        // Assert
        assertEquals(expectedOutput, result);
        verify(busService).searchPurticularBus(busName, source, destination);
    }

    @Test
    void testGetAllBusCapacityMoreThanRequired_ValidInput_ReturnsListOfBuses() throws BusNotFoundException {
        // Arrange
        int capacity = 50;
        String source = "CityA";
        String destination = "CityB";
        List<BusOutputModel> expectedOutput = new ArrayList<>();
        // Set expected output list of buses as needed
        expectedOutput.add(new BusOutputModel("ABC123", "Bus1", "CityA", "CityB", 50, 10, "10", "12", 2));

        when(busService.getAllBusCapacityMoreThanRequired(capacity, source, destination)).thenReturn(expectedOutput);

        // Act
        List<BusOutputModel> result = busController.getAllBusCapacityMoreThanRequired(capacity, source, destination);

        // Assert
        assertEquals(expectedOutput, result);
        verify(busService).getAllBusCapacityMoreThanRequired(capacity, source, destination);
    }

    @Test
    void testGetAllBuses_ReturnsListOfBuses() {
        // Arrange
        List<BusOutputModel> expectedOutput = new ArrayList<>();
        // Set expected output list of buses as needed
        expectedOutput.add(new BusOutputModel("ABC123", "Bus1", "CityA", "CityB", 50, 10, "10", "12", 2));

        when(busService.getAllBuses()).thenReturn(expectedOutput);

        // Act
        List<BusOutputModel> result = busController.getAllBuses();

        // Assert
        assertEquals(expectedOutput, result);
        verify(busService).getAllBuses();
    }

    @Test
    void testGetBusByNumberPlate_ValidInput_ReturnsBus() throws BusNotFoundException {
        // Arrange
        String numberPlate = "ABC123";
        BusOutputModel expectedOutput = new BusOutputModel("ABC123", "Bus1", "CityA", "CityB", 50, 10, "10", "12", 2);

        when(busService.getBusByNumberPlate(numberPlate)).thenReturn(expectedOutput);

        // Act
        BusOutputModel result = busController.getBusByNumberPlate(numberPlate);

        // Assert
        assertEquals(expectedOutput, result);
        verify(busService).getBusByNumberPlate(numberPlate);
    }
    
    @Test
    void testAddBus_ValidInput_ReturnsBusOutputModel() throws BusNotFoundException {
        // Arrange
    	BusInputModel inputModel = new BusInputModel();
    	inputModel.setNumberPlate("ABC123");
    	inputModel.setBusName("MyBus");
    	inputModel.setSource("CityA");
    	inputModel.setDestination("CityB");
    	inputModel.setCapacity(50);
    	inputModel.setSeatPrice(10);
    	inputModel.setStartingTime("08");
    	inputModel.setArrivalTime("10");

    	BusOutputModel expectedOutput = new BusOutputModel();
    	expectedOutput.setNumberPlate("ABC123");
    	expectedOutput.setBusName("MyBus");
    	expectedOutput.setSource("CityA");
    	expectedOutput.setDestination("CityB");
    	expectedOutput.setCapacity(50);
    	expectedOutput.setSeatPrice(10);
    	expectedOutput.setStartingTime("08");
    	expectedOutput.setArrivalTime("10");
    	expectedOutput.setDuration(2); // Assuming duration is specified in minutes

        when(busService.addBus(inputModel)).thenReturn(expectedOutput);

        // Act
        BusOutputModel result = busController.addBus(inputModel);

        // Assert
        assertEquals(expectedOutput, result);
        verify(busService).addBus(inputModel);
    }

    @Test
    void testDeleteBus_ValidId_ReturnsSuccessMessage() throws BusNotFoundException {
        // Arrange
        String busId = "123";

        // Act
        ResponseEntity<String> response = busController.deleteBus(busId);

        // Assert
        assertEquals(ResponseEntity.ok("Bus with id " + busId + " deleted successfully"), response);
        verify(busService).deleteBusById(busId);
    }
}
