import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Movie } from '@app/_models/movie';
import { MovieDto } from '@app/dto/movieDto';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class MovieService {

    private movieUrl = 'http://localhost:8080';

    constructor(private http: HttpClient) {}

    public getAll(): Observable<Movie[]> {
        return this.http.get<Movie[]>(`${this.movieUrl}/home`);
    }

    public getMovieById(id: number): Observable<Movie> {
        return this.http.get<Movie>(`${this.movieUrl}/movies/${id}`);
    }

    public addMovie(movieDto: MovieDto): Observable<MovieDto> {
        return this.http.post<MovieDto>(`${this.movieUrl}/movies/add`, movieDto);
    }

    public updateMovie(movieDto: MovieDto): Observable<MovieDto> {
        return this.http.put<MovieDto>(`${this.movieUrl}/movies/edit`, movieDto);
    }

    public deleteMovie(id: number): Observable<Movie[]> {
        return this.http.delete<Movie[]>(`${this.movieUrl}/movies/delete/${id}`);
    }

}
