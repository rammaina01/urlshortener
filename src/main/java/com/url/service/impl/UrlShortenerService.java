package com.url.service.impl;

import com.url.entity.UrlDetails;
import com.url.exception.UrlAlreadyExistException;
import com.url.model.Url;
import com.url.repository.IUrlRepository;
import com.url.service.IUrlShortenerService;
import com.url.util.MD5Has;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UrlShortenerService implements IUrlShortenerService {

    @Value("${base-url}")
    private String baseurl;

    @Autowired
    IUrlRepository repository;

    @Override
    public Url createShortenUrl(Url logUrl) {

        String hashedString = MD5Has.getHashedString(logUrl.getUrl());
        Date d = new Date();
        String shortenUrl = hashedString.substring(0, 8);

        if (!checkIfIdAvailable(shortenUrl)) {
            log.info("ShortUrl for {} for longUrl {}",shortenUrl ,logUrl.getUrl());
            UrlDetails obj = new UrlDetails(shortenUrl, shortenUrl,
                    logUrl.getUrl(), 0, d, d);
            repository.save(obj);

            Url u = new Url();
            Optional<UrlDetails> url =  repository.findById(shortenUrl);
            u.setUrl(url.get().getShortUrl());
            return u;
        } else {
            UrlDetails urlDto = repository.findById(shortenUrl).get();
            throw new UrlAlreadyExistException(HttpStatus.CONFLICT.value(),
                    "Url Already Exist : " + urlDto.getShortUrl(), HttpStatus.CONFLICT);
        }
    }

    private boolean checkIfIdAvailable(String shortenUrl) {
        Optional<UrlDetails> urlDto = repository.findById(shortenUrl);
        if (urlDto.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public Url getShortenUrl(String shortenUrl) {
        Optional<UrlDetails> url = repository.findById(shortenUrl);
        if (url.isPresent()) {
            Url u = new Url();
            u.setUrl(url.get().getLongUrl());
            storeUsed(url.get());
            return u;
        } else {
            throw new UrlAlreadyExistException(HttpStatus.BAD_REQUEST.value(),
                    "Url NotFound : " + shortenUrl, HttpStatus.BAD_REQUEST);
        }
    }

    private void storeUsed(UrlDetails urlDto) {
        long count = urlDto.getUsedCount();
        urlDto.setUsedCount(count + 1);
        Date d = new Date();
        urlDto.setUpdatedDt(d);
        repository.save(urlDto);
    }

    @Override
    public List<UrlDetails> getAllShortenUrls() {
        return repository.findAll().stream().collect(Collectors.toList());
    }
}
