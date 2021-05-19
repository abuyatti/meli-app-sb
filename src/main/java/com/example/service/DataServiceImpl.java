package com.example.service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.config.Trilateration;
import com.example.model.Satellite;
import com.example.model.Transmission;

@Service
public class DataServiceImpl implements DataService {

	@Autowired
	private Trilateration trilateration;

	private Set<Transmission> transmissions = new HashSet<>();

	private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
	private Lock readLock = reentrantReadWriteLock.readLock();
	private Lock writeLock = reentrantReadWriteLock.writeLock();

	@Override
	public boolean store(Transmission tx) {
		if (trilateration.getSatellites().stream().map(Satellite::getName).anyMatch(name -> name.equals(tx.getName()))
				&& tx.getDistance() != null && tx.getMessage() != null) {
			writeLock.lock();
			try {
				if (transmissions.contains(tx)) {
					transmissions.remove(tx);
				}
				transmissions.add(tx);
				return true;
			} finally {
				writeLock.unlock();
			}
		}
		return false;
	}

	@Override
	public Set<Transmission> get() {
		readLock.lock();
		try {
			return transmissions;
		} finally {
			readLock.unlock();
		}
	}

}
