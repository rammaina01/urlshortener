package com.url.controller;

import com.url.entity.UrlDetails;
import com.url.model.Url;
import com.url.service.IUrlShortenerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api")
public class UrlShortenerController {

    @Autowired
    IUrlShortenerService service;

    @PostMapping("/shortenurl")
    public ResponseEntity<Url> createShortener(@NotNull @RequestBody(required = true) Url url) {
        log.info("Creating shorten url..");
        Url response = service.createShortenUrl(url);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/shortenurl")
    public ResponseEntity<List<UrlDetails>> getAllUrl() {
        log.info("Fetching all url list..");
        List<UrlDetails> response = service.getAllShortenUrls();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{shortenurl}")
    public ResponseEntity<Url> getUrl(@PathVariable String shortenurl) {
        log.info("Fetching shorten url for {}", shortenurl);
        Url res = service.getShortenUrl(shortenurl);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
