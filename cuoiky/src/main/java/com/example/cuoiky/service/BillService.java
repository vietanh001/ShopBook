package com.example.cuoiky.service;

import org.springframework.stereotype.Service;

import com.example.cuoiky.model.Bill;
import com.example.cuoiky.repository.BillRepository;

@Service
public class BillService {
	private final BillRepository billRepository;

	public BillService(BillRepository billRepository) {
		super();
		this.billRepository = billRepository;
	}
	
	public Bill saveBill(Bill bill) {
		return billRepository.save(bill);
	}
}
