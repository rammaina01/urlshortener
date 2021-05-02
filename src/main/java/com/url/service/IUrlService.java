package com.url.service;

import com.url.entity.UrlDto;
import com.url.model.Url;

import java.util.List;

public interface IUrlService {
    Url createShortenUrl(Url url);

    Url getShortenUrl(String url);

    List<UrlDto> getAllShortenUrls();
}
