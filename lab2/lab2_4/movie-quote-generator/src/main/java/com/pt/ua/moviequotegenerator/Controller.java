package com.pt.ua.moviequotegenerator;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    private static HashMap<String, ArrayList<Quote>> QUOTES_BY_SHOW = new HashMap<>();
    private static ArrayList<Quote> QUOTES = new ArrayList<>();
    private static ArrayList<Show> SHOWS = new ArrayList<>();

    private void addData(){

        try{
            Path path = Paths.get("src/main/resources/static/quotes.txt");
    
            BufferedReader reader = Files.newBufferedReader(path);
            long quoteCounter = 0;
            long showsCounter = 0;
            String line;
            
            while((line = reader.readLine()) != null){
                String[] splittedLine = line.split("-");
                String quote = splittedLine[0].trim();
                String show = splittedLine[1].trim();
                
                ArrayList<Quote> quotesArray;
                Quote quoteObject = new Quote(quoteCounter++, quote);         
                QUOTES.add(quoteObject);

                if(QUOTES_BY_SHOW.containsKey(show)){
                    quotesArray = QUOTES_BY_SHOW.get(show);
                } 
                else{
                    quotesArray = new ArrayList<>();
                    SHOWS.add(new Show(showsCounter++, show));
                }
                quotesArray.add(quoteObject);
                QUOTES_BY_SHOW.put(show, quotesArray);
            }
        }
        catch(IOException e){
            System.err.println("Could not open file!");
            System.exit(0);
        } 

    }

    @GetMapping("/api/quote")
	public Quote getQuote() {
		if(SHOWS.size() == 0 || QUOTES.size() == 0) addData();

        int random_int = (int)Math.floor(Math.random() * (QUOTES.size()));
        Quote random_quote = QUOTES.get(random_int);

        if(random_quote != null)
            return random_quote;
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Couldn't fetch a quote");
	}

    @GetMapping("/api/shows")
	public ArrayList<Show> getShows() {
		if(SHOWS.size() == 0 || QUOTES.size() == 0) addData();

        if(SHOWS.size() > 0)
            return SHOWS;
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "There aren't any shows listed");
	}

    @GetMapping("/api/quotes")
	public List<Quote> getQuotesByShows(@RequestParam(value = "show", defaultValue = "0") long id) {
		if(SHOWS.size() == 0 || QUOTES.size() == 0) addData();

        if(SHOWS.size() == 0) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "There aren't any shows listed");

        for (Show show : SHOWS) {
            if(show.id() == id) return QUOTES_BY_SHOW.get(show.name());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found");
	}
}
