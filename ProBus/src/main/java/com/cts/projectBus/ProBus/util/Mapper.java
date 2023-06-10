package com.cts.projectBus.ProBus.util;

import java.util.List;
import java.util.ArrayList;

import com.cts.projectBus.ProBus.entity.Bus;
import com.cts.projectBus.ProBus.entity.Passenger;
import com.cts.projectBus.ProBus.model.BusInputModel;
import com.cts.projectBus.ProBus.model.BusOutputModel;
import com.cts.projectBus.ProBus.model.PassengerModel;

public class Mapper {
	public static BusOutputModel makeBusOutputModel(Bus bus) {
		if (bus == null) {
	        // handle null case
	        return null;
	    }
		BusOutputModel busOutputModel=new BusOutputModel();
		busOutputModel.setArrivalTime(bus.getArrivalTime());
		busOutputModel.setBusName(bus.getBusName());
		busOutputModel.setCapacity(bus.getCapacity());
		busOutputModel.setDestination(bus.getDestination());
		busOutputModel.setDuration(bus.getDuration());
		busOutputModel.setNumberPlate(bus.getNumberPlate());
		busOutputModel.setSeatPrice(bus.getSeatPrice());
		busOutputModel.setSource(bus.getSource());
		busOutputModel.setStartingTime(bus.getStartingTime());
		return busOutputModel;
	}
	public Bus makeBus(BusInputModel busInputModel ){
		Bus bus=new Bus();
		bus.setNumberPlate(busInputModel.getNumberPlate());
		bus.setBusName(busInputModel.getBusName());
		bus.setSource(busInputModel.getSource());
		bus.setDestination(busInputModel.getDestination());
		bus.setCapacity(busInputModel.getCapacity());
		bus.setSeatPrice(busInputModel.getSeatPrice());
		bus.setStartingTime(busInputModel.getStartingTime());
		bus.setArrivalTime(busInputModel.getArrivalTime());
		int startingTime=Integer.parseInt(bus.getStartingTime());
		int arrivalTime=Integer.parseInt(bus.getArrivalTime());
		bus.setDuration(arrivalTime-startingTime);
		return bus;
	}
	public static List<BusOutputModel> makeBusOutputModel(List<Bus> busList) {
		List<BusOutputModel> busOutputList = new ArrayList<>();

		for (Bus bus : busList) {
			busOutputList.add(makeBusOutputModel(bus));
		}
		return busOutputList;
	}
    public List<Bus> makeBusList(List<BusInputModel> busInputList) {
        List<Bus> busList = new ArrayList<>();

        for (BusInputModel busInput : busInputList) {
            busList.add(makeBus(busInput));
        }
        return busList;
    }
	
}
