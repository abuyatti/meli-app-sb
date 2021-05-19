package com.example.config;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.example.model.Position;
import com.example.model.Satellite;

@Component
@ConfigurationProperties
public class Trilateration {

	public static final int QUANTITY = 3;

	private Set<Satellite> satellites = new TreeSet<>();

	public Set<Satellite> getSatellites() {
		return satellites;
	}

	public double[][] getPositions() {
		double[][] positions = new double[satellites.size()][];

		int i = 0;
		for (Iterator<Satellite> it = satellites.iterator(); it.hasNext();) {
			Position position = it.next().getPosition();
			positions[i++] = new double[] { position.getX(), position.getY() };
		}
		return positions;
	}

}
