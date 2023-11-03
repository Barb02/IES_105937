package com.pt.ua.moviequotegenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pt.ua.moviequotegenerator.domain.*;
import com.pt.ua.moviequotegenerator.service.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class MoviesQuotesController {

    private final MovieQuotesService movieQuotesService;

    @Autowired
    public MoviesQuotesController(MovieQuotesService movieQuotesService) {
        this.movieQuotesService = movieQuotesService;
    }

    @GetMapping("/api/quote")
	public Quote getQuote() {
        Quote random_quote = movieQuotesService.getRandomQuote();
        if(random_quote != null)
            return random_quote;
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Couldn't fetch a quote");
	}

    @PostMapping("/api/quote")
	public Quote addQuote(@RequestBody Quote quote) {
        try{
            Quote newQuote = movieQuotesService.addQuote(quote);
            return newQuote;
        }
        catch(NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie doesn't exist");
        }
	}

    @GetMapping("/api/quotes")
    public List<Quote> getQuotesByMovie(@RequestParam(value = "movie", defaultValue = "1") long id) {
        List<Quote> quotes = movieQuotesService.getQuoteByMovie(id);
        if(quotes.size() > 0)
            return quotes;
        else if(quotes.size() == 0)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "There aren't any quotes listed for this movie");
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
    }

    @GetMapping("/api/movies")
	public List<Movie> getMovies() {
        List<Movie> movies = movieQuotesService.getAllMovies();
        if(movies.size() > 0)
            return movies;
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "There aren't any shows listed");
	}

    @PostMapping("/api/movie")
    public Movie addMovie(@RequestBody Movie movie) {
        Movie newMovie = movieQuotesService.addMovie(movie);
        return newMovie;
    }

}
