import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from '@app/_services/alert.service';
import { AuthenticationService } from '@app/_services/authentication.service';
import { MovieService } from '@app/_services/movie.service';
import { MovieDto } from '@app/dto/movieDto';

@Component({
  selector: 'app-crud-movie',
  templateUrl: './crud-movie.component.html',
  styleUrls: ['./crud-movie.component.scss'],
})
export class CrudMovieComponent implements OnInit {
    public isUserlogged: boolean;
    public movieForm: FormGroup;
    public id: number;
    public isAddMode: boolean;
    public loading = false;
    public submitted = false;

  constructor(
      private formBuilder: FormBuilder,
      private authenticationService: AuthenticationService,
      private route: ActivatedRoute,
      private router: Router,
      private movieService: MovieService,
      private alertService: AlertService,
  ) {
    this.isUserlogged = this.authenticationService.isUserLoggedIn();
    if (!this.isUserlogged) {
        this.router.navigate(['']);
    }
}

    public ngOnInit() {
        this.id = this.route.snapshot.params.id;
        this.isAddMode = !this.id;

        this.movieForm = this.formBuilder.group({
            name: ['', Validators.required],
            yearOfPublication: ['', Validators.compose( [Validators.required, Validators.minLength(4), Validators.maxLength(4), Validators.pattern('^[0-9]{4}$')])],
            directorsName: ['', Validators.required],
        });

        if (!this.isAddMode) {
            this.movieService.getMovieById(this.id)
                .subscribe((x) => {
                    this.a.name.setValue(x.name);
                    this.a.yearOfPublication.setValue(x.yearOfPublication);
                    this.a.directorsName.setValue(x.directorsName);
                });
        }
    }

    get a() { return this.movieForm.controls; }

    public onSubmit() {
        this.submitted = true;
        this.alertService.clear();

        // stop here if form is invalid
        if (this.movieForm.invalid) {
            return;
        }
        const movieDto: MovieDto = this.movieForm.value;
        this.loading = true;
        if (this.isAddMode) {
            this.addMovie(movieDto);
        } else {
            this.updateMovie(movieDto);
        }
    }

    private addMovie(movieDto: MovieDto) {
        this.movieService.addMovie(movieDto)
      .subscribe(
          (data) => {
              this.alertService.success('Movie added successfully', true);
              this.router.navigate(['home']);
          },
          (error) => {
              this.alertService.error(error);
              this.loading = false;
          });
    }

    public updateMovie(movieDto: MovieDto) {
        this.movieService.updateMovie(movieDto)
            .subscribe(
                (data) => {
                    this.alertService.success('Movie updated successfully', true);
                    this.router.navigate(['home']);
                },
                (error) => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    }
}
