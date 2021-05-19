package com.url.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@NoArgsConstructor
@Data
@Document(collection = "url_details")
public class UrlDetails {
    @Id
    private String id;
    private String shortUrl;
    private String longUrl;
    private long usedCount;
    private Date createdDt;
    private Date updatedDt;


    public UrlDetails(String id, String shortUrl, String longUrl, long usedCount, Date createdDt, Date updatedDt) {
        this.id = id;
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
        this.updatedDt = updatedDt;
        this.createdDt = createdDt;
        this.usedCount = usedCount;
    }
}
