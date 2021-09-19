package com.gox.parking.finder.api;

import com.gox.parking.finder.api.assembler.ParkingAssembler;
import com.gox.parking.finder.api.config.WfsRestTemplate;
import com.gox.parking.finder.api.controller.ParkingController;
import com.gox.parking.finder.api.service.BordeauxParkingService;
import com.gox.parking.finder.api.service.ParkingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
class ParkingFinderApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private WfsRestTemplate wfsRestTemplate;

	@Autowired
	private ParkingController parkingController;

	@Autowired
	private ParkingService parkingService;

	@Autowired
	private ParkingAssembler parkingAssembler;

	@Autowired
	private BordeauxParkingService bordeauxParkingService;

	@Test
	void contextLoads() {
		assertNotNull(parkingController);
		assertNotNull(parkingService);
		assertNotNull(parkingAssembler);
		assertNotNull(bordeauxParkingService);
	}
}
