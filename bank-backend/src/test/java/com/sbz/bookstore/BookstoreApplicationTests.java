package com.sbz.bookstore;

import com.sbz.bank.model.Location;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Location.class)
class BookstoreApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void checkDistanceCalculator() {
		Location location = new Location();
		location.setLatitude(0);
		location.setLongitude(0);
		Location otherLocation = new Location();
		otherLocation.setLatitude(0);
		otherLocation.setLongitude(0);
		System.out.println(location.getDistance(otherLocation));
	}

}
