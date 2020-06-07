package com.trainingProject.moviesWebApp.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.StringJoiner;

@Entity(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    @Column(name = "title", nullable = false, unique = true, length = 100)
    private String title;

    @Column(name = "director", nullable = false, length = 50)
    private String director;

    @Column(name = "year_of_publication", nullable = false, length = 4)
    private int yearOfPublication;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @ManyToOne()
    @JoinColumn(name = "user", nullable = false, updatable = false)
    private User user;

    @Column(name = "likes", nullable = false)
    private Long likes;

    @Column(name = "dislikes", nullable = false)
    private Long dislikes;

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    @Version
    @Column(name = "version")
    private Long version;

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User username) {
        this.user = username;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getDislikes() {
        return dislikes;
    }

    public void setDislikes(Long dislikes) {
        this.dislikes = dislikes;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @PrePersist
    public void onPrePersist() {
        if (createdAt == null) {
            createdAt = currentTime();
        }
    }

    static LocalDate currentTime() {
        return LocalDate.now();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Movie.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("director='" + director + "'")
                .add("yearOfPublication='" + yearOfPublication + "'")
                .add("description='" + description + "'")
                .add("user=" + user)
                .add("likes=" + likes)
                .add("dislikes=" + dislikes)
                .add("createdAt=" + createdAt)
                .add("version=" + version)
                .toString();
    }

}
