package com.example.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.config.Trilateration;
import com.example.exception.ISException;
import com.example.model.Position;
import com.example.model.PositionMessage;
import com.example.model.Satellite;
import com.example.model.Transmission;

@Service
public class IntelligenceServiceImpl implements IntelligenceService {

	private static final Logger log = LoggerFactory.getLogger(IntelligenceServiceImpl.class);

	@Autowired
	private LocationService locationService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private Trilateration trilateration;

	@Override
	public PositionMessage decode(Set<Transmission> transmissions) throws Exception {

		// Validate Transmissions
		List<Transmission> validTransmissions = validateTransmissions(transmissions);

		// Resolve Message
		String message = resolveMessage(validTransmissions);

		// Resolve Position
		Position position = resolvePosition(validTransmissions);

		return new PositionMessage(position, message);
	}

	public List<Transmission> validateTransmissions(Set<Transmission> transmissions) throws ISException {
		List<Transmission> validTransmissions = transmissions.stream()
				.filter(e -> trilateration.getSatellites().stream().map(Satellite::getName)
						.anyMatch(name -> name.equals(e.getName())) && e.getDistance() != null)
				.collect(Collectors.toList());

		if (validTransmissions.size() < Trilateration.QUANTITY) {
			log.error("No hay suficiente información.");
			throw new ISException();
		}
		return validTransmissions;
	}

	public String resolveMessage(List<Transmission> transmissions) throws ISException {
		List<List<String>> messages = transmissions.stream().map(Transmission::getMessage).collect(Collectors.toList());

		boolean sameLength = messages.stream().allMatch(l -> l.size() == messages.get(0).size());

		if (!sameLength) {
			log.error("Mensajes dispares.");
			throw new ISException();
		}

		return messageService.getMessage(messages);
	}

	public Position resolvePosition(List<Transmission> transmissions) {
		List<Double> distances = transmissions.stream().sorted().map(Transmission::getDistance)
				.collect(Collectors.toList());

		double[] centroid = locationService.getLocation(trilateration.getPositions(),
				distances.stream().mapToDouble(d -> d).toArray());

		return new Position(centroid[0], centroid[1]);
	}

}
