package org.http.backend.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.http.backend.entity.Movie;
import org.http.backend.service.MovieService;
import org.http.backend.util.Rating;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> all() {
        return ResponseEntity.ok().body(movieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> one(@PathVariable String id) {
        return ResponseEntity.ok().body(movieService.findById(id));
    }

    @GetMapping("/name")
    public ResponseEntity<List<Movie>> findByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok().body(movieService.findByNameContainsIgnoreCase(name));
    }

    @PostMapping
    public ResponseEntity<Movie> save(@RequestBody String jsonString) throws JsonProcessingException {
        try{
            return ResponseEntity.status(201).body(movieService.save(jsonString));
        }catch (JsonMappingException | IllegalArgumentException e){
            return ResponseEntity.status(400).body(movieService.save(jsonString));
        }
    }

    @PutMapping("{movieId}/rating")
    public ResponseEntity<?> addRating(@PathVariable String movieId, @RequestBody Rating rating) {
      try {
          return ResponseEntity.ok().body(movieService.addRating(movieId, rating));
      } catch (Exception e) {
          return ResponseEntity.status(400).body("Error: " + e.getMessage());
      }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String id) {
        movieService.delete(id);
        return ResponseEntity.ok().build();
    }

}
