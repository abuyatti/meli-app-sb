package com.example.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Transmission;

@Service
public class TransmissionServiceImpl implements TransmissionService {

	@Autowired
	private DataService dataService;

	@Override
	public boolean storeTransmission(Transmission transmission) throws Exception {
		return dataService.store(transmission);
	}

	@Override
	public Set<Transmission> getTransmissions() throws Exception {
		return dataService.get();
	}

}
