package com.movink.demo.Reposetory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.movink.demo.Entity.Movie;

import jakarta.transaction.Transactional;

@Service
@Repository
public interface Movie_reposetory extends JpaRepository<Movie, Long> {

    @Query("SELECT U FROM Movie U WHERE U.movie_name = :movie_name")
    Movie findByMovie_name(@Param("movie_name") String movie_name); 
     
    @Query("SELECT U FROM Movie U WHERE U.id = :id")
    Movie findByMovie_id(@Param("id") Long id);
    
  

}
