package com.trainingProject.moviesWebApp.repository;

import com.trainingProject.moviesWebApp.entity.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findMovieById(Long id);

    List<Movie> findMoviesByUser_Id(Long id, Sort sort);
//    List<Movie> findMoviesByUser_IdOrderByDislikesDesc(Long id);
//    List<Movie> findMoviesByUser_IdOrderByDislikesAsc(Long id);
//    List<Movie> findMoviesByUser_IdOrderByLikesAsc(Long id);
//    List<Movie> findMoviesByUser_IdOrderByLikesDesc(Long id);
//    List<Movie> findMoviesByUser_IdOrderByCreatedAtDesc(Long id);
//    List<Movie> findMoviesByUser_IdOrderByCreatedAtAsc(Long id);
}

