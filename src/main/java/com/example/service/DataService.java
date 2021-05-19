package com.example.service;

import java.util.Set;

import com.example.model.Transmission;

public interface DataService {

	public boolean store(Transmission tx) throws Exception;

	public Set<Transmission> get() throws Exception;

}
