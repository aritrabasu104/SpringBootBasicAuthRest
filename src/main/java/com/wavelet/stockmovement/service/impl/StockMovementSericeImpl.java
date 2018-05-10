package com.wavelet.stockmovement.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavelet.stockmovement.model.Stock;
import com.wavelet.stockmovement.repository.StockRepository;
import com.wavelet.stockmovement.service.StockMovementService;

@Service
public class StockMovementSericeImpl implements StockMovementService {

	@Autowired
	private StockRepository stockRepository;
	@Override
	public List<Stock> getStockMovementDetails() {
		List<Stock> stocks= new ArrayList<>();
		stockRepository.findAll().forEach(stocks::add);
		return stocks;
	}

	@Override
	public Stock addStockMovementDetail(Stock stock) {
		stockRepository.save(stock);
		return stock;
	}

}
