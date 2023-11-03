package com.pt.ua.moviequotegenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pt.ua.moviequotegenerator.domain.Movie;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {}
