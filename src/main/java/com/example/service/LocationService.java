package com.example.service;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;

@Service
public class LocationService {

	public double[] getLocation(double[][] positions, double[] distances) {

		NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
				new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());

		return solver.solve().getPoint().toArray();
	}

}
