package com.example.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransmissionWrapper {

	@JsonProperty("satellites")
	private Set<Transmission> transmissions = new HashSet<>();

	public Set<Transmission> getTransmissions() {
		return transmissions;
	}

}
