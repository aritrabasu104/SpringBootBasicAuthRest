package com.wavelet.stockmovement.service;

import java.util.List;
import com.wavelet.stockmovement.model.Stock;

public interface StockMovementService {
	public List<Stock> getStockMovementDetails();
	public Stock addStockMovementDetail(Stock stock);
}
