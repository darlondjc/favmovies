package br.com.favmovies.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.favmovies.model.Movie;
import br.com.favmovies.repository.MovieRepository;

@RestController
@RequestMapping("movie")
public class MovieController {

	@Autowired
	MovieRepository movieRepository;
	
	@GetMapping
	public List<Movie> movies() {
		return movieRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Movie movie(@PathVariable(value = "id") Long id) {
		Optional<Movie> movieOptional = movieRepository.findById(id);
		/*
		if(movieOptional.isEmpty()) {
			return "redirect:/usuario/home";
		}
		*/
		return movieOptional.get();
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/usuario/home";
	}
}
