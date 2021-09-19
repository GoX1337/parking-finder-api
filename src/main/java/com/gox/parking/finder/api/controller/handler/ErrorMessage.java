package com.gox.parking.finder.api.controller.handler;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class ErrorMessage {

    private final String error;
    private final LocalDateTime timestamp = LocalDateTime.now();

}
