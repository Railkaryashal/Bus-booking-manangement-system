package com.cts.projectBus.ProBus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.projectBus.ProBus.exception.BusNotFoundException;
import com.cts.projectBus.ProBus.model.BusInputModel;
import com.cts.projectBus.ProBus.model.BusOutputModel;
import com.cts.projectBus.ProBus.repository.BusRepository;
import com.cts.projectBus.ProBus.service.BusService;


@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class BusController {
	@Autowired
	BusRepository busRepository;
	@Autowired
	BusService busService;
	
	@GetMapping(value="/searchbus/{source}/{destination}")
	public List<BusOutputModel> searchBus(@PathVariable("source")String source,
			@PathVariable("destination")String destination) throws BusNotFoundException{
		return busService.searchBus(source, destination);
	}

	@GetMapping(value="/source/destination/busname")
	public List<BusOutputModel> searchPurticularBus(
	    @RequestParam("busName") String busName,
	    @RequestParam("source") String source,
	    @RequestParam("destination") String destination) throws BusNotFoundException {
	    return busService.searchPurticularBus(busName, source, destination);
	}
	
	@GetMapping(value="/source/destination/getAllBusCapacityMoreThanRequired")
	public List<BusOutputModel> getAllBusCapacityMoreThanRequired(
	    @RequestParam("capacity") int capacity,
	    @RequestParam("source") String source,
	    @RequestParam("destination") String destination) throws BusNotFoundException {
	    return busService.getAllBusCapacityMoreThanRequired(capacity, source, destination);
	}

	@GetMapping("/getallbuses")
	public List<BusOutputModel> getAllBuses() {
	    return busService.getAllBuses();
	}

	
	@GetMapping(value ="/getBusByNumberPlate/{numberPlate}")
	public BusOutputModel getBusByNumberPlate(@PathVariable("numberPlate") String numberPlate) throws BusNotFoundException {
		return busService.getBusByNumberPlate(numberPlate);
	}

	@PostMapping(value = "/addbus" )
	public BusOutputModel addBus( @RequestBody BusInputModel  busInputModel ) throws BusNotFoundException {
		BusOutputModel busOutputModel=null;
		busOutputModel=busService.addBus(busInputModel);
		return busOutputModel;
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBus(@PathVariable String id) throws BusNotFoundException {
	    busService.deleteBusById(id);
	    return ResponseEntity.ok("Bus with id " + id + " deleted successfully");
	}

}
