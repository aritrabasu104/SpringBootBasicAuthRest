package com.wavelet.stockmovement.controller;

import java.util.Collections;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.wavelet.stockmovement.model.Stock;
import com.wavelet.stockmovement.service.StockMovementService;

@RestController
public class StockMovementController {
	
	@Autowired
	private StockMovementService service;

	@GetMapping("/api/stocks")
	public ResponseEntity<?> stocks(){

		return ResponseEntity.ok(service.getStockMovementDetails());
	}
	@PostMapping("/api/addStock")
	public ResponseEntity<?> addStock(@Valid @RequestBody Stock stock, Errors errors){
		String errorMsg = null;
		if (errors.hasErrors()) {
			// get all errors 
			errorMsg = errors.getAllErrors()
				.stream()
				.map(x -> ((FieldError)x).getField()+"-"+((FieldError)x).getDefaultMessage())
				.collect(Collectors.joining(","));
				
            return ResponseEntity.badRequest().body(Collections.singletonMap("response", errorMsg));
        }
		service.addStockMovementDetail(stock);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addStockMovementDetail(stock));
	}
	
}

