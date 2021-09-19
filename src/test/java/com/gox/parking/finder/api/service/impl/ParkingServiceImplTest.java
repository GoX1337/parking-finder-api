package com.gox.parking.finder.api.service.impl;

import com.gox.parking.finder.api.dto.ParkingDto;
import com.gox.parking.finder.api.exception.CityNotSupportedException;
import com.gox.parking.finder.api.exception.NoParkingFoundException;
import com.gox.parking.finder.api.service.BordeauxParkingService;
import com.gox.parking.finder.api.service.ParkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParkingServiceImplTest {

    private ParkingService parkingService;

    @Mock
    private BordeauxParkingService bordeauxParkingService;

    @BeforeEach
    void setUp(){
        parkingService = new ParkingServiceImpl(bordeauxParkingService);
    }

    @Test
    void findAllBordeauxParkings() throws CityNotSupportedException, NoParkingFoundException {
        List<ParkingDto> pkgs = List.of(
                new ParkingDto(1L, "pkg1", "parking 1", null, "32 rue a droite", 100, 200, "LIBRE"),
                new ParkingDto(2L, "pkg2", "parking 2", null, "666 avenue du général", 50, 300, "LIBRE")
        );
        when(bordeauxParkingService.findAllParkings()).thenReturn(pkgs);

        List<ParkingDto> pkgsResult = parkingService.findAllParkings("bordeaux");

        verify(bordeauxParkingService).findAllParkings();
        assertEquals(pkgs.size(), pkgsResult.size());
        assertEquals("parking 1", pkgs.get(0).getName());
        assertEquals(100, pkgs.get(0).getFree());
        assertEquals("LIBRE", pkgs.get(1).getState());
        assertEquals("pkg2", pkgs.get(1).getIdent());
    }

    @Test
    void tryToFindUnsuportedCityParkings() {
        Exception e = assertThrows(CityNotSupportedException.class, () -> {
            parkingService.findAllParkings("paris");
        });
        assertEquals("The city paris is not supported", e.getMessage());
        verify(bordeauxParkingService, never()).findAllParkings();
    }

    @Test
    void findAllBordeauxParkingsNear() throws CityNotSupportedException, NoParkingFoundException {
        List<ParkingDto> pkgs = List.of(
                new ParkingDto(1L, "pkg1", "parking 1", null, "32 rue a droite", 100, 200, "LIBRE"),
                new ParkingDto(2L, "pkg2", "parking 2", null, "666 avenue du général", 50, 300, "LIBRE")
        );
        when(bordeauxParkingService.findAllParkingNear(40, 70, 1000)).thenReturn(pkgs);

        List<ParkingDto> pkgsResult = parkingService.findAllParkingNear("bordeaux", 40, 70, 1000);

        verify(bordeauxParkingService).findAllParkingNear(40, 70, 1000);
        assertEquals(pkgs.size(), pkgsResult.size());
        assertEquals("parking 1", pkgs.get(0).getName());
        assertEquals(100, pkgs.get(0).getFree());
        assertEquals("LIBRE", pkgs.get(1).getState());
        assertEquals("pkg2", pkgs.get(1).getIdent());
    }

    @Test
    void tryToFindUnsuportedCityParkingsNear() throws CityNotSupportedException {
        Exception e = assertThrows(CityNotSupportedException.class, () -> {
            parkingService.findAllParkingNear("paris", 78, 99, 5555);
        });
        assertEquals("The city paris is not supported", e.getMessage());
        verify(bordeauxParkingService, never()).findAllParkingNear(78, 99, 5555);
    }
}