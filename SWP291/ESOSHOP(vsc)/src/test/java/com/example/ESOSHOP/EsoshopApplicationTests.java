package com.example.ESOSHOP;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;

import com.shopping.esoshop.service2.IDaoService;


@RunWith(SpringRunner.class)
@SpringBootTest
@Import({IDaoService.class}) 
class EsoshopApplicationTests {

	@Autowired
	IDaoService daoService;

	@Test
	void contextLoads() {
		assertNotNull(null); // Check if the service is injected correctly
	}

	boolean checkDate(Date date) {
		int day = date.getDay();
		int month = date.getMonth();
		int year = date.getYear();
		int maxday[] = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (year % 4 == 0) {
			maxday[2] = 28;
		}
		if (year <= 0)
			return false;
		if (year > 0) {
			if (0 < month && month <= 12) {
				if (day > 0 && day <= maxday[month]) {
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}

	boolean checkFrom_To_Now(Date from, Date to) {
		Date now = Date.valueOf(LocalDate.now());

		if (from.before(to) && to.before(now))
			return true; // from before to and before now
		if (from.after(to) && to.before(now))
			return true; // from after to and before now
		if (from.before(to) && to.after(now))
			return true; // from before to and after now
		if (from.equals(to) && to.equals(now))
			return true; // from equals to equals now
		return false; // For all other cases
	}

	@Test
	void checkGetRevenues() {
		int testcase = 1;
		Date from;
		Date to;
		boolean result;

		switch (testcase) { // date now 2023-11-8
			case 1: // from before to and before now
				from = Date.valueOf("2023-2-30");
				to = Date.valueOf("2023-11-02");
				result = checkFrom_To_Now(from, to);
				assertTrue(checkDate(from));
				assertTrue(checkDate(to));
				assertTrue(result);
				assertNotNull(daoService.getRevenues(from, to));
				break;
			case 2: // from after to and before now
				from = Date.valueOf("2023-10-03");
				to = Date.valueOf("2023-01-02");
				result = checkFrom_To_Now(from, to);
				assertTrue(result);
				assertNotNull(daoService.getRevenues(from, to));
				break;
			case 3: // from before to and after now
				from = Date.valueOf("2023-11-10");
				to = Date.valueOf("2023-12-09");
				result = checkFrom_To_Now(from, to);
				assertTrue(result);
				assertNotNull(daoService.getRevenues(from, to));
				break;
			case 4: // from equals to equals now
				from = Date.valueOf("2023-11-08");
				to = Date.valueOf("2023-11-08");
				result = checkFrom_To_Now(from, to);
				assertTrue(result);
				assertNotNull(daoService.getRevenues(from, to));
				break;
			case 5: // Other cases for thorough testing
				// Add other test cases here
				break;
		}
	}
	@Test void testDate(){
		assertTrue(!checkDate(Date.valueOf("2023-2-30")));
	}
	
}
