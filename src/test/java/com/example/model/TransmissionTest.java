package com.example.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

class TransmissionTest {

	@Test
	void testSetOrder() {
		Transmission t1 = new Transmission("Webb", null, null);
		Transmission t2 = new Transmission("Cosmos 215", null, null);
		Transmission t3 = new Transmission("Hubble", null, null);

		Set<Transmission> transmissions = new TreeSet<>();
		transmissions.add(t1);
		transmissions.add(t2);
		transmissions.add(t3);

		Transmission[] actual = new Transmission[transmissions.size()];
		transmissions.toArray(actual);

		Transmission[] expected = { t2, t3, t1 };

		assertArrayEquals(expected, actual);
	}

}
