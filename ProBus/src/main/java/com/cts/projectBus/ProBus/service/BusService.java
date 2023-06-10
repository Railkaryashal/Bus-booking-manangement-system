package com.cts.projectBus.ProBus.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cts.projectBus.ProBus.entity.Bus;
import com.cts.projectBus.ProBus.entity.User;
import com.cts.projectBus.ProBus.exception.BusNotFoundException;
import com.cts.projectBus.ProBus.exception.CannotDeleteBusException;
import com.cts.projectBus.ProBus.exception.UserNotFoundException;
import com.cts.projectBus.ProBus.model.BusInputModel;
import com.cts.projectBus.ProBus.model.BusOutputModel;
import com.cts.projectBus.ProBus.repository.BusRepository;
import com.cts.projectBus.ProBus.util.Mapper;

import java.util.ArrayList;
@Service
public class BusService {
	@Autowired
	BusRepository busRepository;
	 Mapper mapper = new Mapper();
	 public List<BusOutputModel> getAllBusCapacityMoreThanRequired(int capacity, String source, String destination) throws BusNotFoundException {
	        List<Bus> buses = busRepository.findBySourceAndDestinationAndCapacityGreaterThanEqual(source, destination, capacity);
	        if(buses.isEmpty()) {
	            throw new BusNotFoundException("Sorry, No buses found with capacity more than or equal to "+capacity+" from "+source+" to "+destination);
	        }
	        return mapper.makeBusOutputModel(buses);
	 }
	 
	 public List<BusOutputModel> searchBus(String source, String destination) throws BusNotFoundException {
	        List<Bus> foundBuses = busRepository.searchBus(source, destination);
	        if (foundBuses.isEmpty()) {
	            throw new BusNotFoundException("Sorry, No buses found from "+source+ " to "+ destination);
	        }
	        return mapper.makeBusOutputModel(foundBuses);
	    }


	public List<BusOutputModel> searchPurticularBus(String busName, String source, String destination) throws BusNotFoundException {
		List<Bus> buses = busRepository.searchPurticularBus(source, destination, busName);
        if(buses.isEmpty()) {
            throw new BusNotFoundException("Sorry, No buses found with bus name: "+busName+" from "+source+" to "+destination);
        }
        return mapper.makeBusOutputModel(buses);
	}
	
	public List<BusOutputModel> getAllBuses() {
	    List<Bus> buses = busRepository.findAll();
	    List<BusOutputModel> busOutputList = new ArrayList<>();

	    for (Bus bus : buses) {
	        BusOutputModel busOutput = mapper.makeBusOutputModel(bus);
	        busOutputList.add(busOutput);
	    }

	    return busOutputList;
	}

	public BusOutputModel getBusByNumberPlate(String numberPlate) throws BusNotFoundException{
		Bus bus=busRepository.findById(numberPlate).orElse(null);
		if(bus==null)
			throw new BusNotFoundException("Bus not found with number plate: "+numberPlate);
		return new Mapper().makeBusOutputModel(bus);
	}
	
	public BusOutputModel addBus(BusInputModel busInputModel) throws BusNotFoundException {
		
		Bus b=busRepository.findById(busInputModel.getNumberPlate()).orElse(null);
		if(b!=null)
			throw new BusNotFoundException("Bus already exist in Database");
		
		Bus bus=new Mapper().makeBus(busInputModel);
		bus=busRepository.save(bus);
		return new Mapper().makeBusOutputModel(bus);
	}

    public void deleteBusById(String id) throws BusNotFoundException, CannotDeleteBusException{
    	if (!busRepository.existsById(id)) {
            throw new BusNotFoundException("Bus not found with id: " + id);
        }
        try {
        busRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
        	throw new CannotDeleteBusException("Cannot delete bus with id: " + id + " because the trasaction in the bus exist.", e);
        }
    }
    



}
