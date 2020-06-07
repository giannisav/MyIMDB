package com.trainingProject.moviesWebApp.repository;

import com.trainingProject.moviesWebApp.entity.UserMovieKey;
import com.trainingProject.moviesWebApp.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, UserMovieKey> {
    Optional<Vote> findVoteById(UserMovieKey id);
}
