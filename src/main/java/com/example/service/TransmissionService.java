package com.example.service;

import java.util.Set;

import com.example.model.Transmission;

public interface TransmissionService {

	public boolean storeTransmission(Transmission transmission) throws Exception;

	public Set<Transmission> getTransmissions() throws Exception;

}
