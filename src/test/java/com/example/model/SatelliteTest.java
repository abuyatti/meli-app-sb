package com.example.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class SatelliteTest {

	@Test
	void testSetOrder() {
		Set<Satellite> satellites = new TreeSet<>();
		satellites.add(new Satellite("Ganímedes", null));
		satellites.add(new Satellite("Calisto", null));
		satellites.add(new Satellite("Io", null));
		satellites.add(new Satellite("Europa", null));

//		Satellite[] actual = new Satellite[satellites.size()];
//		satellites.toArray(actual);

//		assertEquals("Calisto", actual[0].getName());
//		assertEquals("Europa", actual[1].getName());
//		assertEquals("Ganímedes", actual[2].getName());
//		assertEquals("Io", actual[3].getName());

		List<String> actual = satellites.stream().map(Satellite::getName).collect(Collectors.toList());
		assertThat(actual, contains("Calisto", "Europa", "Ganímedes", "Io"));
	}

}
