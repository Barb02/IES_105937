package com.pt.ua.moviequotegenerator.service;

import java.util.List;

import com.pt.ua.moviequotegenerator.domain.*;

public interface MovieQuotesService {
    public Quote getRandomQuote();
    public Quote addQuote(Quote quote);
    public List<Quote> getQuoteByMovie(Long movieId);
    public Movie addMovie(Movie movie);
    public List<Movie> getAllMovies();
}
