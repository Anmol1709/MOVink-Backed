package com.movink.demo.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Movink_user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;
    private String role;

    @OneToMany(mappedBy = "created_by", cascade = CascadeType.ALL)
    List<Movie> Added_movie_list= new ArrayList<>();

    //  @OneToMany(mappedBy = "Watched_by", cascade = CascadeType.ALL)
    // List<Movie>Watching_movie_list= new ArrayList<>();


  
    List<Long>Recommends_movie_list= new ArrayList<>();
    List<Long>Watching_movie_list= new ArrayList<>();




    @OneToMany(mappedBy = "Added_song_list", cascade = CascadeType.ALL)
    List<Song>Added_song_list= new ArrayList<>();

    //@OneToMany(mappedBy = "watching_song_list", cascade = CascadeType.ALL)
    // List<Song>watching_song_list= new ArrayList<>();

    // @OneToMany(mappedBy = "Recommended_song_items", cascade = CascadeType.ALL)
    // List<Song>Recommended_song_items= new ArrayList<>();
      


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public Movink_user(Long id, String username, String password, String role, List<Movie> added_movie_list,List<Long> rec,List<Long> wat) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.Added_movie_list = added_movie_list;
        this.Recommends_movie_list=rec;
        this.Watching_movie_list=wat;
      }

    public Movink_user() {
    }

    @Override
    public String toString() {
        return "Movink_user [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role
            ;
    }





    public List<Song> getAdded_song_list() {
        return Added_song_list;
    }

 

    public void setAdded_song_list(List<Song> ff ) {
  
        this.Added_song_list.addAll(ff);
    }



    //  public List<Movie> getWatching_movie_list() {
    //      return this.Watching_movie_list;
    //  }

    // public void setWatching_movie_list(List<Movie> watching_movie_list) {
    //     Watching_movie_list.addAll(watching_movie_list);
    //  }

    // public List<Movie> getRecommends_movie_list() {
    //     return this.Recommends_movie_list;
    // }

    // public void setRecommends_movie_list(List<Movie> recommends_movie_list) {
    //     Recommends_movie_list.addAll(recommends_movie_list);
    // }


    public List<Movie> getAdded_movie_list() {
        return Added_movie_list;
    }

    public void setAdded_movie_list(List<Movie> added_movie_list) {
        this.Added_movie_list.addAll(added_movie_list);
    }

    public List<Long> getRecommends_movie_list() {
        return Recommends_movie_list;
    }

    public void addRecommends_movie_list(Long ll) {
        Recommends_movie_list.add(ll);
    }
    public List<Long> getWatching_movie_list() {
        return Watching_movie_list;
    }

    public void addWatching_movie_list(Long ll) {
        Watching_movie_list.add(ll);
    }

    public void remove_recommended_movie(long ll)
    {
        Recommends_movie_list.removeIf(el->el==ll);
    }

    public void remove_Watching_movie(long ll)
    {
        Watching_movie_list.remove(ll);
    }


}
