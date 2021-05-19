package com.example.service;

import java.util.Set;

import com.example.model.PositionMessage;
import com.example.model.Transmission;

public interface IntelligenceService {

	public PositionMessage decode(Set<Transmission> transmissions) throws Exception;

}
