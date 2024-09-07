package com.movink.demo.Controllers;

import java.util.List;

import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movink.demo.Entity.Movie;
import com.movink.demo.Entity.Movink_user;
import com.movink.demo.Entity.Song;
import com.movink.demo.Reposetory.Movie_reposetory;
import com.movink.demo.Reposetory.Songs_reposetory;
import com.movink.demo.Reposetory.userReposetory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private userReposetory userRepository;

    @Autowired
    private Movie_reposetory movie_reposetory;

    @Autowired
    private Songs_reposetory song_reposetory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    UserController() {
    }

    @GetMapping("/findall")
    public List<Movink_user> getMethodName() {

        return userRepository.findAll();

    }

    @PostMapping("/register")
    public ResponseEntity<Movink_user> register(@RequestBody Movink_user request) {

        try {

            if (userRepository.existsByUsername(request.getUsername())) {
                throw new RuntimeException("Username already exists");
            }

            request.setPassword(passwordEncoder.encode(request.getPassword()));
            Movink_user kk = userRepository.save(request);
            System.out.println("registered");

            return ResponseEntity.status(HttpStatus.CREATED).body(kk);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(request);

        }

    }

    @PostMapping("/signin")
    public ResponseEntity<Movink_user> signin(@RequestBody Movink_user loginRequest) {
        try {

            Movink_user user = userRepository.findByusername(loginRequest.getUsername());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(loginRequest);
            }

            // Check if password matches
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginRequest);
            }

            return ResponseEntity.status(HttpStatus.OK).body(loginRequest);
        } catch (Exception e) {
            return new ResponseEntity<Movink_user>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/Add_movie")
    public ResponseEntity<Movie> Add_movie(@RequestBody Movie entity, @RequestHeader("username") String username) {

        try {

            Movink_user loggedin_user = userRepository.findByusername(username);
            if (loggedin_user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Movie());
            }

            Movie exist = movie_reposetory.findByMovie_name(entity.getMovie_name());

            if (exist != null)
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new Movie());

            // entity.setCreated_by(loggedin_user);
            System.out.println("REQUEST ENTITY: " + entity);
            loggedin_user.setAdded_movie_list(List.of(entity));
            entity.setCreated_by(loggedin_user);
            System.out.println("loggedin_user: " + loggedin_user);

            try {
                // userRepository.save(loggedin_user);
                Movie res = movie_reposetory.save(entity);
                System.out.println("MOVIES AFTER SAVED: " + res);

            } catch (Exception e) {
                return new ResponseEntity<Movie>(HttpStatus.INTERNAL_SERVER_ERROR);
                       
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(entity);

        } catch (Exception e) {
            return  return new ResponseEntity<Movie>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SuppressWarnings("null")
    @DeleteMapping("/delete_movie")
    public ResponseEntity<String> delete_movie(@RequestParam String movie_name,
            @RequestHeader("username") String username) {

        try {

            Movink_user loggedin_user = userRepository.findByusername(username);
            if (loggedin_user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            Movie exist = movie_reposetory.findByMovie_name(movie_name);
            System.out.println("movie_name" + movie_name);
            System.out.println("exist" + exist);
            if (exist == null) {

                return ResponseEntity.status(HttpStatus.CONFLICT).body("A movie with the same name does not  exists");
            }

            if (!exist.getCreater_name().equals(username)) {
                System.out.println("creater:- " + exist.getCreater_name());
                System.out.println("username: -" + username);

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("movie is not made by loggedin user");
            }

            try {
                // userRepository.save(loggedin_user);
                movie_reposetory.deleteById(exist.getId());

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error while deleting movie " + e.getMessage());
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("Movie deleted successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error-" + e.getMessage());
        }
    }

    @PostMapping("/Add_song")
    public ResponseEntity<String> Add_song(@RequestBody Song entity, @RequestHeader("username") String username) {

        try {

            Movink_user loggedin_user = userRepository.findByusername(username);
            if (loggedin_user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            Song exist = song_reposetory.findBySong_name(entity.getSong_name());

            if (exist != null)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("A song with the same name already exists");

            // entity.setCreated_by(loggedin_user);
            System.out.println("REQUEST ENTITY: " + entity);
            loggedin_user.setAdded_song_list(List.of(entity));
            entity.setAdded_song_list(loggedin_user);
            System.out.println("loggedin_user: " + loggedin_user);

            try {
                // userRepository.save(loggedin_user);
                var res = song_reposetory.save(entity);
                System.out.println("MOVIES AFTER SAVED: " + res);

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error while saving movie " + e.getMessage());
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("Song created and added successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error-" + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @DeleteMapping("/delete_song")
    public ResponseEntity<String> delete_song(@RequestParam String song_name,
            @RequestHeader("username") String username) {

        try {

            Movink_user loggedin_user = userRepository.findByusername(username);
            if (loggedin_user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            Song exist = song_reposetory.findBySong_name(song_name);
            // System.out.println("movie_name"+ movie_name);
            // System.out.println("exist"+ exist);
            if (exist == null) {

                return ResponseEntity.status(HttpStatus.CONFLICT).body("A song with the same name does not  exists");
            }

            if (!exist.getCreater_name().equals(username)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Song is not made by loggedin user");
            }

            try {
                // userRepository.save(loggedin_user);
                song_reposetory.deleteById(exist.getId());

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error while deleting song " + e.getMessage());
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("Song deleted successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error-" + e.getMessage());
        }
    }

    @Transactional
    @PutMapping("/Recommend_Movie")
    public ResponseEntity<String> Recommend_Movie(@RequestParam Long entity_id,
            @RequestHeader("username") String username) {

        try {

            Movink_user recommend_to_user = userRepository.findByusername(username);
            if (recommend_to_user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recommended User not found");
            }

            System.out.println("REQUEST ENTITY: " + entity_id);

            recommend_to_user.addRecommends_movie_list(entity_id);

            return ResponseEntity.status(HttpStatus.CREATED).body("Rrecommended movie  added successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error-" + e.getMessage());
        }
    }

    @Transactional
    @PutMapping("/delete_recommended_movie")
    public ResponseEntity<String> delete_recommended_movie(@RequestParam Long movie_id,
            @RequestHeader("username") String username) {

        try {

            Movink_user loggedin_user = userRepository.findByusername(username);
            if (loggedin_user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            Movie exist = movie_reposetory.findByMovie_id(movie_id);
            System.out.println("movie_name" + exist.getMovie_name());
            System.out.println("exist" + exist);
            System.out.println("pre list " + loggedin_user.getRecommends_movie_list());
            if (exist == null) {

                return ResponseEntity.status(HttpStatus.CONFLICT).body("A movie with the same name does not  exists");
            }

            if (!exist.getCreater_name().equals(username)) {
                System.out.println("creater:- " + exist.getCreater_name());
                System.out.println("username: -" + username);

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("movie is not made by loggedin user");
            }

            // userRepository.save(loggedin_user);
            loggedin_user.remove_recommended_movie(movie_id);

            System.out.println("list===" + loggedin_user.getRecommends_movie_list());

            return ResponseEntity.status(HttpStatus.CREATED).body("Movie deleted from recommended list  successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error-" + e.getMessage());
        }

    }

    @GetMapping("/getMovie_by_id")
    public Movie getMovie_by_id(@RequestParam Long id) {
        return movie_reposetory.findByMovie_id(id);
    }

    @GetMapping("/All_movie")
    public List<Movie> getAllMovie() {
        return movie_reposetory.findAll();

    }

    @GetMapping("/All_songs")
    public List<Song> All_songs() {
        return song_reposetory.findAll();

    }

    @Transactional
    @PutMapping("/Add_watch_movie")
    public ResponseEntity<String> Add_watch_movie(@RequestParam Long entity_id,
            @RequestHeader("username") String username) {
        try {

            Movink_user loggedin_user = userRepository.findByusername(username);
            if (loggedin_user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Watched User not found");
            }

            System.out.println("REQUEST ENTITY: " + entity_id);

            System.out.println("before ");
            loggedin_user.addWatching_movie_list(entity_id);
            System.out.println("After ");

            return ResponseEntity.status(HttpStatus.CREATED).body("watching movie  added successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error-" + e.getMessage());
        }
    }

    @Transactional
    @PutMapping("/delete_watch_movie")
    public ResponseEntity<String> delete_watch_movie(@RequestParam Long movie_id,
            @RequestHeader("username") String username) {

        try {

            Movink_user loggedin_user = userRepository.findByusername(username);
            if (loggedin_user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            Movie exist = movie_reposetory.findByMovie_id(movie_id);
            System.out.println("movie_name" + exist.getMovie_name());
            System.out.println("exist" + exist);
            System.out.println("pre list " + loggedin_user.getWatching_movie_list());
            if (exist == null) {

                return ResponseEntity.status(HttpStatus.CONFLICT).body("A movie with the same name does not  exists");
            }

            if (!exist.getCreater_name().equals(username)) {
                System.out.println("creater:- " + exist.getCreater_name());
                System.out.println("username: -" + username);

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("movie is not made by loggedin user");
            }

            // userRepository.save(loggedin_user);
            loggedin_user.remove_Watching_movie(movie_id);

            System.out.println("list===" + loggedin_user.getWatching_movie_list());

            return ResponseEntity.status(HttpStatus.CREATED).body("Movie deleted from watching list  successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error-" + e.getMessage());
        }

    }

}
