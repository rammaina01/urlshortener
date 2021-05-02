package com.url.repository;

import com.url.entity.UrlDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUrlRepository extends MongoRepository<UrlDto,String> {
}
