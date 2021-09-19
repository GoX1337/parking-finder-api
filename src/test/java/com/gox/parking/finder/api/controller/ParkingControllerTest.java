package com.gox.parking.finder.api.controller;

import com.gox.parking.finder.api.dto.ParkingDto;
import com.gox.parking.finder.api.exception.CityNotSupportedException;
import com.gox.parking.finder.api.exception.NoParkingFoundException;
import com.gox.parking.finder.api.service.ParkingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParkingController.class)
@ExtendWith(SpringExtension.class)
class ParkingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingService parkingService;

    @Test
    void parkings() throws Exception {
        List<ParkingDto> pkgs = List.of(
                new ParkingDto(1L, "pkg1", "parking 1", null, "32 rue a droite", 100, 200, "LIBRE"),
                new ParkingDto(2L, "pkg2", "parking 2", null, "666 avenue du général", 50, 300, "LIBRE")
        );
        when(parkingService.findAllParkings("bordeaux")).thenReturn(pkgs);
        RequestBuilder req = MockMvcRequestBuilders.get("/api/bordeaux/parking");
        mockMvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("parking 1"))
                .andExpect(jsonPath("$[0].ident").value("pkg1"))
                .andExpect(jsonPath("$[1].free").value(50))
                .andExpect(jsonPath("$[1].total").value(300));
    }

    @Test
    void parkingNear() throws Exception {
        List<ParkingDto> pkgs = List.of(
                new ParkingDto(1L, "pkg1", "parking 1", null, "32 rue a droite", 100, 200, "LIBRE"),
                new ParkingDto(2L, "pkg2", "parking 2", null, "666 avenue du général", 50, 300, "LIBRE")
        );
        when(parkingService.findAllParkingNear("bordeaux", 40, 50, 1000)).thenReturn(pkgs);
        RequestBuilder req = MockMvcRequestBuilders.get("/api/bordeaux/parking/near?latitude=40&longitude=50&distance=1000");
        mockMvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("parking 1"))
                .andExpect(jsonPath("$[0].ident").value("pkg1"))
                .andExpect(jsonPath("$[1].free").value(50))
                .andExpect(jsonPath("$[1].total").value(300));
    }

    @Test
    void noParking() throws Exception {
        when(parkingService.findAllParkings("bordeaux")).thenThrow(new NoParkingFoundException());
        RequestBuilder req = MockMvcRequestBuilders.get("/api/bordeaux/parking");
        mockMvc.perform(req)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("No parking found"));
    }

    @Test
    void noParkingNear() throws Exception {
        when(parkingService.findAllParkingNear("bordeaux", 40, 50, 1000)).thenThrow(new NoParkingFoundException());
        RequestBuilder req = MockMvcRequestBuilders.get("/api/bordeaux/parking/near?latitude=40&longitude=50&distance=1000");
        mockMvc.perform(req)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("No parking found"));
    }

    @Test
    void cityNotSupported() throws Exception {
        when(parkingService.findAllParkings("paris")).thenThrow(new CityNotSupportedException("paris"));
        RequestBuilder req = MockMvcRequestBuilders.get("/api/paris/parking");
        mockMvc.perform(req)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("The city paris is not supported"));
    }
}