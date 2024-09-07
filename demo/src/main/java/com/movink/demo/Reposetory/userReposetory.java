package com.movink.demo.Reposetory;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.movink.demo.Entity.Movink_user;

import jakarta.transaction.Transactional;

@Service
@Repository
public interface  userReposetory extends JpaRepository<Movink_user,Long> {

    boolean existsByUsername(String username);

    @Query("SELECT U FROM Movink_user U WHERE U.username = :username")
    public Movink_user findByusername(@Param("username") String username);
    
    
    
    
    
    
    

    

}
