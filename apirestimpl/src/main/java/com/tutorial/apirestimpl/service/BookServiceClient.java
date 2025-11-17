package com.tutorial.apirestimpl.service;

import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tutorial.apirestimpl.dto.BookDTO;

@Service
public class BookServiceClient {
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	private static final  String BOOKS_API_URL = "https://691af55f2d8d78557570e010.mockapi.io/api/v1/books";
	
	public Optional<BookDTO> findBookByTitle(String title){
		
		try {
			
			String encoded = URLEncoder.encode(title, StandardCharsets.UTF_8);
			
			String url = BOOKS_API_URL + "?search=" + encoded;
			
			ResponseEntity<BookDTO[]> response = restTemplate.getForEntity(url, BookDTO[].class);
			
			BookDTO[] books = response.getBody();
			
			if (books == null || books.length == 0) {
				return Optional.empty();
			}
			
			return Arrays.stream(books)
					.filter(b -> title.equalsIgnoreCase(b.getTitle()))
					.findFirst().or( () -> Optional.of(books[0]));
			
		} catch (Exception e) {
			// TODO: handle exception
			return Optional.empty();
		}
		
		
		
	}

}
