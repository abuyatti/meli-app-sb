package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.exception.ISException;
import com.example.model.PositionMessage;
import com.example.model.Transmission;
import com.example.model.TransmissionWrapper;
import com.example.service.IntelligenceService;
import com.example.service.TransmissionService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "${api.base.path}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CommunicationController {

	@Autowired
	private IntelligenceService intelligenceService;

	@Autowired
	private TransmissionService transmissionService;

	@ApiOperation(value = "Retorna la fuente y el contenido del mensaje a partir de la informacion recibida de la nave.")
	@PostMapping(path = "/topsecret")
	public ResponseEntity<PositionMessage> topsecret(RequestEntity<TransmissionWrapper> requestEntity)
			throws Exception {

		if (requestEntity.hasBody()) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(intelligenceService.decode(requestEntity.getBody().getTransmissions()));
		}
		throw new ISException();
	}

	@ApiOperation(value = "Recibe información parcial de la nave.")
	@PostMapping(path = "/topsecret_split/{satellite_name}")
	public ResponseEntity<Transmission> store(RequestEntity<Transmission> requestEntity,
			@PathVariable String satellite_name) throws Exception {

		if (requestEntity.hasBody()) {
			Transmission tx = requestEntity.getBody();
			tx.setName(satellite_name);
			if (transmissionService.storeTransmission(tx)) {
				return ResponseEntity.status(HttpStatus.OK).body(tx);
			}
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transmisión inválida.");
	}

	@ApiOperation(value = "Retorna la fuente y el contenido del mensaje.")
	@GetMapping(path = "/topsecret_split", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<PositionMessage> get() throws Exception {

		return ResponseEntity.status(HttpStatus.OK)
				.body(intelligenceService.decode(transmissionService.getTransmissions()));
	}

}
