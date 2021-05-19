package com.url.service;

import com.url.entity.UrlDetails;
import com.url.model.Url;

import java.util.List;

public interface IUrlShortenerService {
    Url createShortenUrl(Url url);

    Url getShortenUrl(String url);

    List<UrlDetails> getAllShortenUrls();
}
