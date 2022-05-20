package com.capgemini.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.capgemini.model.Book;

@Service
public class FavoriteService 
{
	  // Creating an instance of RestTemplate class
	  RestTemplate restTemplate = new RestTemplate();
	  
	  //request url
	  String url = "http://localhost:8085/api/v1/books";
	  
	  public ResponseEntity<String> getFavorites()
	  {
		  HttpHeaders headers = new HttpHeaders();
		  headers.setContentType(MediaType.APPLICATION_JSON);
		  HttpEntity<String> entity = new  HttpEntity<String>("parameters",headers);
		  ResponseEntity<String> response = restTemplate.exchange("http://localhost:8085/api/v1/allBooks" , HttpMethod.GET, entity, String.class);
		  return response;
	  }
	  
	  
}
