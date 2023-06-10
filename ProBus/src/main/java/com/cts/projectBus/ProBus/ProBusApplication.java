package com.cts.projectBus.ProBus;
//package com.example.projectBus.ProBus;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ApplicationContext;
//
//import com.example.projectBus.ProBus.entity.Bus;
//import com.example.projectBus.ProBus.exception.BusNotFoundException;
//import com.example.projectBus.ProBus.service.BusService;
//
//@SpringBootApplication
//public class ProBusApplication {
//	private static ApplicationContext ctx =null;
//	//private static ApplicationContext ctx1 =null;
//	private static BusService busService=null;
//	private static Logger logger =  LoggerFactory.getLogger(ProBusApplication.class);
//	//private static CustomerService customerService=null;
//	public static void main(String[] args) {
//		SpringApplication.run(ProBusApplication.class, args);
//		//ctx1=SpringApplication.run(DemoProjectBusApplication.class, args);
//		busService= ctx.getBean(BusService.class);
//		//customerService=ctx1.getBean(CustomerService.class);
//		//testAddBus();
//		testgetBusById();
//		//testgetAllBus();
//		//getBusByStartLoc();
//		//getAllBusCapacityMoreThanRequired();
//		//testAddCustomer();
//		System.exit(0);
//	}
//	private static void getAllBusCapacityMoreThanRequired() {
//		logger.info("getAllBusCapacityMoreThanRequired() method start");
//		List <Bus> busList=null;
//		try {
//			busList =busService.getAllBusCapacityMoreThanRequired(50);
//			logger.info("busList={}" , busList);
//		} catch (BusNotFoundException e) {
//			logger.error("exception={}" , e.toString());
//		}
//		logger.info("getAllBusCapacityMoreThanRequired() method end");		
//	}
//	
//	
//	private static void getBusByStartLoc() {
//		logger.info("getBusByStartLoc() method start");
//		List <Bus> busList=null;
//		try {
//			busList =busService.getBusByStartLoc("Pune");
//			logger.info("busList={}" , busList);
//		} catch (BusNotFoundException e) {
//			logger.error("exception={}" , e.toString());
//		}
//		logger.info("getBusByStartLoc() method end");		
//	}
//	
//	private static void testgetAllBus() {
//		logger.info("testgetAllBus() method start");
//		List<Bus> busList=null;
//		busList =busService.getAllBus();
//		logger.info("busList={}" , busList);
//		logger.info("testgetAllBus() method end");
//	}
//	
//	private static void testgetBusById() {
//		logger.info("testgetBusById() method start");
//		Bus bus = null;
//		try {
//			bus =busService.getBusById("B10");
//			logger.info("bus={}" , bus);
//		} catch (BusNotFoundException e) {
//			// TODO Auto-generated catch block
//			logger.error("exception={}" , e.toString());
//		}
//		logger.info("testgetBusById() method end");
//	}
//
//	private static void testAddBus() {
//		logger.info("testAddBus() method start");
//		Bus bus = new Bus("B1","Shivshahi","Pune","Mumbai",100,60);
//		//Bus bus = new Bus("B2","ShivGar","Pune","Mumbai",60,100);
//		//Bus bus = new Bus("B3","Rajratan","Mumbai","Delhi",80);
//		//Bus bus = new Bus("B4","Volvo","Goa","Pune",40);
//		//Bus bus = new Bus("B18","VRL","Nagpur","Pune",46);
//		//Bus bus = new Bus("B2",)
//		try {
//			bus =busService.addBus(bus);
//			logger.info("bus={}" , bus);
//		} catch (BusNotFoundException e) {
//			// TODO Auto-generated catch block
//			logger.error("exception={}" , e.toString());
//		}
//		logger.info("testAddBus() method end");
//	}
//	/*
//	private static void testAddCustomer() {
//		logger.info("testAddCustomer() method start");
//		Customer customer=new Customer(1,"Yashal","Male",22,992039284);
//		
//		try {
//			customer =customerService.addCustomer(customer);
//			logger.info("customer={}" , customer);
//		} catch (CustomerNoFoundException e1) {
//			logger.error("exception={}" , e1.toString());
//		}
//		logger.info("testAddCustomer() method end");
//
//	}
//*/
//}

