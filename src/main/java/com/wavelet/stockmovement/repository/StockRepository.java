package com.wavelet.stockmovement.repository;

import org.springframework.data.repository.CrudRepository;

import com.wavelet.stockmovement.model.Stock;

public interface StockRepository extends CrudRepository<Stock, Long> {

}
