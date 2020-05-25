import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MovieService } from '@app/_services/movie.service';

@Component({ selector: 'app-home',
             styleUrls: ['./home.component.scss'],
             templateUrl: './home.component.html' })
export class HomeComponent implements OnInit {
    public movies = [];
    public displayedColumns: string[] = [ 'name', 'directorsName', 'yearOfPublication', 'Edit/Delete'];
    public currentUser: string = sessionStorage.getItem('currentUserName');

    constructor(
        private movieService: MovieService,
        private router: Router,
    ) {}

    public ngOnInit() {
        this.loadAllMovies();
    }

    private loadAllMovies() {
        this.movieService.getAll()
            .subscribe((movies) => this.movies = movies);
    }

    public deleteMovie(id: number) {
        this.movieService.deleteMovie(id)
            .subscribe(() => {
                this.movies = this.movies.filter((x) => x.id !== id);
            });
    }

    public getMovieById(id: number) {
        this.movieService.getMovieById(id)
            .subscribe((data) => {
                const movieDto = JSON.stringify(data);
                this.router.navigate(['/movies/save'], { queryParams: { movieDto: movieDto , edit: true } });
            });
    }

}
