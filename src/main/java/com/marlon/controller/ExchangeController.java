package com.marlon.controller;

import com.marlon.environment.InstanceInformationService;
import com.marlon.model.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("exchange-service")
public class ExchangeController {

    @Autowired
    private InstanceInformationService informationService;

    @GetMapping(value = "/{amount}/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Exchange exchangeCurrency(
            @PathVariable BigDecimal amount,
            @PathVariable String from,
            @PathVariable String to) {
        return new Exchange(1L, from, to, BigDecimal.ONE,
                BigDecimal.ONE, "PORT " + informationService.retrieveServerPort());
    }
}
