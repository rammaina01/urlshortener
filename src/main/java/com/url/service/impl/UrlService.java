package com.url.service.impl;

import com.url.entity.UrlDto;
import com.url.exception.UrlAlreadyExistException;
import com.url.model.Url;
import com.url.repository.IUrlRepository;
import com.url.service.IUrlService;
import com.url.util.MD5Has;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UrlService implements IUrlService {

    private static final String baseurl = "http://localhost:8080/";
    @Autowired
    IUrlRepository repository;

    @Override
    public Url createShortenUrl(Url logUrl) {
        String hashedString = MD5Has.getHashedString(logUrl.getUrl());
        Date d = new Date();
        String shortenUrl = hashedString.substring(0, 8);
        Url u = new Url();

        if (!checkIfIdAvailable(shortenUrl)) {
            UrlDto obj = new UrlDto(shortenUrl, baseurl + shortenUrl,
                    logUrl.getUrl(), 0, d, d);
            repository.save(obj);
            u.setUrl(shortenUrl);
            return u;
        } else {
            UrlDto urlDto = repository.findById(shortenUrl).get();
            throw new UrlAlreadyExistException(HttpStatus.CONFLICT.value(),
                    "Url Already Exist : " + urlDto.getShortUrl(), HttpStatus.CONFLICT);
        }
    }

    private boolean checkIfIdAvailable(String shortenUrl) {
        Optional<UrlDto> urlDto = repository.findById(shortenUrl);
        if (urlDto.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public Url getShortenUrl(String shortenUrl) {
        Optional<UrlDto> urlDto = repository.findById(shortenUrl);
        if (urlDto.isPresent()) {
            Url u = new Url();
            u.setUrl(urlDto.get().getLongUrl());
            storeUsed(urlDto.get());
            return u;
        } else {
            throw new UrlAlreadyExistException(HttpStatus.BAD_REQUEST.value(),
                    "Url NotFound : " + shortenUrl, HttpStatus.BAD_REQUEST);
        }
    }

    private void storeUsed(UrlDto urlDto) {
        long count = urlDto.getUsedCount();
        urlDto.setUsedCount(count + 1);
        Date d = new Date();
        urlDto.setUpdatedDt(d);
        repository.save(urlDto);
    }

    @Override
    public List<UrlDto> getAllShortenUrls() {
        return repository.findAll().stream().collect(Collectors.toList());
    }
}
