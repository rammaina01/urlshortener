# Backend Service

## Steps to Build and run the application 

## Build the application image
sudo docker build -t api-docker-image .

## Run docker compose to start the application and mongo-db database container
docker-compose up

## What is covered as part of the given assignment

Created Backend web application using spring-boot and java and React client to call the to short the long urls to the short urls, used the mongo db
urlshortener

### Clone from here:
https://github.com/rammaina01/urlshortener.git

Idea is to expose the and points which can serve our purpose
### post: /api/shortenurl : to create a short url
### Get: /api/shortenurl : to list the all urls 
### Get:/api/{shortenurl} : to get the specific long url for short url


# Frontend client
Front ent client application has been written in React which will call the backend service

###Clone from here..
https://github.com/rammaina01/urlshortener-app.git