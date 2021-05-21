package com.url.service;

import com.url.entity.UrlDetails;
import com.url.exception.UrlAlreadyExistException;
import com.url.model.Url;
import com.url.repository.IUrlRepository;
import com.url.service.impl.UrlShortenerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UrlShortenerServiceTest {

    @InjectMocks
    private UrlShortenerService service = new UrlShortenerService();
    @Mock
    private IUrlRepository iUrlRepository;

    @Test
    public void testCreateShortenUrl() {

    }

    @Test
    public void TestGetAllShortenUrls(){
        UrlDetails obj1 = new UrlDetails("123","www.abc.com","abc",0,null, null);
        UrlDetails obj2 = new UrlDetails("123","www.abc.com","abc",0,null, null);

        Mockito.when(iUrlRepository.findAll()).thenReturn(Arrays.asList(obj1,obj2));
        List<UrlDetails> result = service.getAllShortenUrls();
        assertEquals(2, result.size(),"Validate size of the all stored objects");
    }

    @Test
    public void testGetShortenUrl() {
        UrlDetails obj = new UrlDetails("123","www.abc.com","abc",0,null, null);
        Mockito.when(iUrlRepository.findById("abc")).thenReturn(Optional.of(obj));
        Url result = service.getShortenUrl("abc");
        assertEquals("abc", result.getUrl(),"Validate the short url");
    }

    @Test
    public void testGetShortenUrlWhenUrlNotFound() {
        String shortenUrl = "abc";
        Mockito.when(iUrlRepository.findById("abc")).thenThrow(new UrlAlreadyExistException(HttpStatus.BAD_REQUEST.value(),
                "Url NotFound : " + shortenUrl, HttpStatus.BAD_REQUEST));
        try{
            service.getShortenUrl("abc");
        }catch (UrlAlreadyExistException ex){
            assertEquals("Url NotFound : abc", ex.getMsg(),"Validate the Exception msg");
        }
    }
}
