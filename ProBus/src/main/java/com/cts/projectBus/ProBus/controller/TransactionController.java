package com.cts.projectBus.ProBus.controller;

import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.projectBus.ProBus.entity.Transaction;
import com.cts.projectBus.ProBus.exception.BusNotFoundException;
import com.cts.projectBus.ProBus.exception.UserNotFoundException;
import com.cts.projectBus.ProBus.model.TransactionInputModel;
import com.cts.projectBus.ProBus.model.TransactionOutputModel;
import com.cts.projectBus.ProBus.service.TransactionService;


@RestController

@CrossOrigin(origins = "http://localhost:3000/")
public class TransactionController {

	@Autowired
	TransactionService transactionService;
	
	@PostMapping(value = "/maketransaction")
	public TransactionOutputModel makeTransaction(@RequestBody TransactionInputModel transactionModel) throws BusNotFoundException, TransactionException, UserNotFoundException {
		TransactionOutputModel transactionOutputModel=null;
		transactionOutputModel = transactionService.makeTransaction(transactionModel);
		return transactionOutputModel;
	}
}
