package com.capgemini.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.capgemini.service.FavoriteService;

@RestController
@RequestMapping("/favorite")
public class FavoriteController 
{
	   @Autowired
	   private FavoriteService favService;
	   
		/*
		 * @PostMapping("/addFav") public ResponseEntity<Book> addFavBook() { return
		 * favService.addFavorites(); }
		 */
	   
	   @GetMapping("/getFav")
	   public ResponseEntity<String> getFavBooks()
	   {
		     return favService.getFavorites();
	   }
}
