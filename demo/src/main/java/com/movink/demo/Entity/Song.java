package com.movink.demo.Entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Song {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String song_name;
    private String description;
    private String cotegory;
    private String download_Link;
    private String Rating;
    private String Status;
    private String Thumbnail_link;
    private List<String> Sample_images;


 

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSong_name() {
        return song_name;
    }
    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCotegory() {
        return cotegory;
    }
    public void setCotegory(String cotegory) {
        this.cotegory = cotegory;
    }
    public String getDownload_Link() {
        return download_Link;
    }
    public void setDownload_Link(String download_Link) {
        this.download_Link = download_Link;
    }
    public String getRating() {
        return Rating;
    }
    public void setRating(String rating) {
        Rating = rating;
    }
    public String getStatus() {
        return Status;
    }
    public void setStatus(String status) {
        Status = status;
    }
    public String getThumbnail_link() {
        return Thumbnail_link;
    }
    public void setThumbnail_link(String thumbnail_link) {
        Thumbnail_link = thumbnail_link;
    }
    public List<String> getSample_images() {
        return Sample_images;
    }
    public void setSample_images(List<String> sample_images) {
        Sample_images = sample_images;
    }

    

    public Song() {
    }

    @Override
    public String toString() {
        return "Song [id=" + id + ", song_name=" + song_name + ", description=" + description + ", cotegory=" + cotegory
                + ", download_Link=" + download_Link + ", Rating=" + Rating + ", Status=" + Status + ", Thumbnail_link="
                + Thumbnail_link + ", Sample_images=" + Sample_images + "]";
    }

    

    @ManyToOne
    @JoinColumn(name = "Added_song_list")
    private Movink_user Added_song_list;


    public Long getCreated_by() {
        return this.Added_song_list.getId();
    }
    public String getCreater_name(){
        return this.Added_song_list.getUsername();
    }

    // @ManyToOne
    // @JoinColumn(name = "watching_song_list")
    // private Movink_user watching_song_list;

    // @ManyToOne
    // @JoinColumn(name = "Recommended_song_items")
    // private Movink_user Recommended_song_items;

   
    public Long getAdded_song_list() {
        return this.Added_song_list.getId();
    }
    public void setAdded_song_list(Movink_user added_song_list) {
        this.Added_song_list = added_song_list;
    }
    // public Movink_user getWatching_song_list() {
    //     return watching_song_list;
    // }
    // public void setWatching_song_list(Movink_user watching_song_list) {
    //     this.watching_song_list = watching_song_list;
    // }
    // public Movink_user getRecommended_song_items() {
    //     return Recommended_song_items;
    // }
    // public void setRecommended_song_items(Movink_user recommended_song_items) {
    //     Recommended_song_items = recommended_song_items;
    // }

}
