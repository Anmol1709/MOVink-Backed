package com.movink.demo.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String movie_name;

    private String description;
    private String cotegory;
    private String download_Link;
    private String Rating;
    private String Status = "Pending";
    private String Thumbnail_link;
    private List<String> Sample_images;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Movink_user created_by;

     
    // @ManyToOne
    // @JoinColumn(name="Watched_by")
    // private Movink_user Watched_by;


  
   


    public Movie(Long id, String movie_name, String description, String cotegory, String download_Link, String rating,
            String status, String thumbnail_link, List<String> sample_images, Movink_user created_by) {
        this.id = id;
        this.movie_name = movie_name;
        this.description = description;
        this.cotegory = cotegory;
        this.download_Link = download_Link;
        Rating = rating;
        Status = status;
        Thumbnail_link = thumbnail_link;
        Sample_images = sample_images;
        this.created_by = created_by;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCotegory(String cotegory) {
        this.cotegory = cotegory;
    }

    public void setDownload_Link(String download_Link) {
        this.download_Link = download_Link;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setThumbnail_link(String thumbnail_link) {
        Thumbnail_link = thumbnail_link;
    }

    public void setSample_images(List<String> sample_images) {
        Sample_images = sample_images;
    }

  

    public Long getId() {
        return id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public String getDescription() {
        return description;
    }

    public String getCotegory() {
        return cotegory;
    }

    public String getDownload_Link() {
        return download_Link;
    }

    public String getRating() {
        return Rating;
    }

    public String getStatus() {
        return Status;
    }

    public String getThumbnail_link() {
        return Thumbnail_link;
    }

    public List<String> getSample_images() {
        return Sample_images;
    }

    public Long getCreated_by() {
        return this.created_by.getId();
    }
    public String getCreater_name(){
        return this.created_by.getUsername();
    }
    public void setCreated_by(Movink_user created_by) {
        this.created_by = created_by;
    }

    // public Long getRecommends_movie_list() {
    //     return this.Recommends_movie_list.getId();
    // }
    // public void setRecommends_movie_list(Movink_user recommends_movie_list) {
    //     this.Recommends_movie_list = recommends_movie_list;
    // }

    //  public Long getWatching_movie_list() {
    //     return this.Watched_by.getId();
    // }
    //  public void setWatching_movie_list(Movink_user Watched_by) {
    //     this.Watched_by = Watched_by;
    // }




    public Movie() {
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", movie_name=" + movie_name + ", description=" + description + ", cotegory="
                + cotegory + ", download_Link=" + download_Link + ", Rating=" + Rating + ", Status=" + Status
                + ", Thumbnail_link=" + Thumbnail_link + ", Sample_images=" + Sample_images + "]";
    }

  
  

}
