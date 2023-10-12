package com.pt.ua.moviequotegenerator;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.ArrayList;

// TODO: fazer tratamento de erros

@RestController
public class Controller {

    private static HashMap<String, Integer> SHOWS = new HashMap<>();  // nome : show_id
    private static HashMap<Integer, ArrayList<String>> QUOTES = new HashMap<>(); // show_id : quotes

    private void addData(){

        try{
            Path path = Paths.get("shows.txt");
    
            BufferedReader reader = Files.newBufferedReader(path);
            int counter = 0;
            String line;
            
            while((line = reader.readLine()) != null){
                SHOWS.put(line, counter++);
            }
    
            path = Paths.get("quotes.txt");
            reader = Files.newBufferedReader(path);
    
            while((line = reader.readLine()) != null){
                String[] splittedLine = line.split("-");
    
                if(SHOWS.containsKey(splittedLine[1].trim())){
                    int show_id = SHOWS.get(splittedLine[1].trim());
    
    
                    ArrayList<String> quotes = new ArrayList<>();
                    if(QUOTES.containsKey(show_id)){
                        quotes = QUOTES.get(show_id);
                        quotes.add(splittedLine[0].trim());
                        QUOTES.put(show_id, quotes);
                    }
                    else{
                        quotes.add(splittedLine[0].trim());
                        QUOTES.put(show_id, quotes);
                    }
                }
            }
        }
        catch(IOException e){
            System.err.println("Could not open file!");
        }
        

    }

    @GetMapping("/api/quote")
	public Quote getQuote() {
		if(SHOWS.size() == 0 || QUOTES.size() == 0) addData();
	}
}
