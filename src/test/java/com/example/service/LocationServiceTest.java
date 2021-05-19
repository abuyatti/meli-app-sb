package com.example.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LocationServiceTest {

	private LocationService locationService;

	@BeforeAll
	void setup() {
		this.locationService = new LocationService();
	}

	@Test
	void testGetLocation() {
		double[][] positions = new double[][] { { 4, 8 }, { 11, 7 }, { 16, 17 } };
		double[] distances = new double[] { 5, 5, 10 };
		double[] expected = new double[] { 8.0, 11.0 };
		double[] actual = locationService.getLocation(positions, distances);
		assertArrayEquals(expected, actual);
	}

}
