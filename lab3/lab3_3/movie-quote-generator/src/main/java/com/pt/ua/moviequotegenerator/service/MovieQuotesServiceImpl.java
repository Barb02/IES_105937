package com.pt.ua.moviequotegenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import com.pt.ua.moviequotegenerator.domain.*;
import com.pt.ua.moviequotegenerator.repository.*;

@Service
public class MovieQuotesServiceImpl implements MovieQuotesService{

    private final QuoteRepository quoteRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public MovieQuotesServiceImpl(QuoteRepository quoteRepository, MovieRepository movieRepository) {
        this.quoteRepository = quoteRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Quote getRandomQuote() {
        List<Quote> quotes = quoteRepository.findAll();
        int random_int = (int)Math.floor(Math.random() * (quotes.size()));
        Quote random_quote = quotes.get(random_int);
        return random_quote;
    }

    @Override
    public Quote addQuote(Quote quote) throws NoSuchElementException {
        Long movieId = quote.getMovie();
        movieRepository.findById(movieId).get(); // Throws exception
        Quote new_quote = quoteRepository.save(quote);
        return new_quote;
    }

    @Override
    public List<Quote> getQuoteByMovie(Long movieId) {
        List<Quote> quotes = quoteRepository.findByMovie(movieId);
        return quotes;
    }

    @Override
    public Movie addMovie(Movie movie) {
        Movie new_movie = movieRepository.save(movie);
        return new_movie;
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies;
    }
}
