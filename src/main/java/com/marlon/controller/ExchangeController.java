package com.marlon.controller;

import com.marlon.environment.InstanceInformationService;
import com.marlon.model.Exchange;
import com.marlon.repository.ExchangeRepository;
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

    @Autowired
    private ExchangeRepository repository;

    @GetMapping(value = "/{amount}/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Exchange exchangeCurrency(
            @PathVariable BigDecimal amount,
            @PathVariable String from,
            @PathVariable String to) {

        Exchange exchange = repository.findByFromAndTo(from, to);

        if(exchange == null)
            throw new RuntimeException("Currency Unsupported.");

        BigDecimal convertedValue = exchange.getConversionFactor().multiply(amount);

        exchange.setConvertedValue(convertedValue);
        exchange.setEnvironment("PORT " + informationService.retrieveServerPort());

        return exchange;
    }
}
