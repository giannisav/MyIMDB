import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '@app/_services/authentication.service';
import { MovieService } from '@app/_services/movie.service';
import { first } from 'rxjs/operators';
import { MovieDto } from '@app/dto/movieDto';

@Component({ templateUrl: 'home.component.html' })
export class HomeComponent implements OnInit {
    public isUserlogged: boolean;

    public movies = [];

    constructor(
        private authenticationService: AuthenticationService,
        private movieService: MovieService,
        private router: Router,
    ) {
        this.isUserlogged = this.authenticationService.isUserLoggedIn();
        if (!this.isUserlogged) {
            this.router.navigate(['']);
        }
    }

    public ngOnInit() {
        this.loadAllMovies();
    }

    private loadAllMovies() {
        this.movieService.getAll()
            .pipe(first())
            .subscribe((movies) => this.movies = movies);
    }

    public deleteMovie(id: number) {
        this.movieService.deleteMovie(id)
            .subscribe(() => {
                this.movies = this.movies.filter((x) => x.id !== id)
            });
    }
    
    public updateMovie(movieDto: MovieDto) {
        this.movieService.updateMovie(movieDto);
    }

}
