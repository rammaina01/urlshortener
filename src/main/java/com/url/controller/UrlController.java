package com.url.controller;

import com.url.entity.UrlDto;
import com.url.model.Url;
import com.url.service.IUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class UrlController {

    @Autowired
    IUrlService service;

    @PostMapping("/shortenurl")
    public ResponseEntity<Url> createShortener(@RequestBody Url url) {
        Url response = service.createShortenUrl(url);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/shortenurl")
    public ResponseEntity<List<UrlDto>> getAllUrl() {
        List<UrlDto> response = service.getAllShortenUrls();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{shortenurl}")
    public ResponseEntity<Url> getUrl(@PathVariable String shortenurl) {
        Url response = service.getShortenUrl(shortenurl);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
