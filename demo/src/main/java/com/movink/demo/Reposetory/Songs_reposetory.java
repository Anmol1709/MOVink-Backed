package com.movink.demo.Reposetory;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.movink.demo.Entity.Song;

import jakarta.transaction.Transactional;

@Service
@Repository
public interface Songs_reposetory extends JpaRepository<Song,Long> {

     @Query("SELECT U FROM Song U WHERE U.song_name = :song_name")
    Song findBySong_name(@Param("song_name") String song_name);


}
