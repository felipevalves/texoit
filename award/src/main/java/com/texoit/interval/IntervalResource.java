package com.texoit.interval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/award/v1/intervals")
public class IntervalResource {

    @Autowired
    private IntervalService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IntervalContainer> getAwardInterval() {

        var container = service.getContainerInterval();

        return new ResponseEntity<>(container, HttpStatus.OK);

    }

}
