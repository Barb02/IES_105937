package com.pt.ua.moviequotegenerator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pt.ua.moviequotegenerator.domain.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findByMovie(Long id);
}
