import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from '@app/_services/alert.service';
import { AuthenticationService } from '@app/_services/authentication.service';
import { MovieService } from '@app/_services/movie.service';
import { MovieDto } from '@app/dto/movieDto';

@Component({
  selector: 'app-crud-movie',
  styleUrls: ['./crud-movie.component.scss'],
  templateUrl: './crud-movie.component.html',
})
export class CrudMovieComponent implements OnInit {
    public isUserlogged: boolean;
    public movieForm: FormGroup;
    public id: number;
    public isEditMode = false;
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
        this.movieForm = this.formBuilder.group({
            directorsName: ['', Validators.required],
            name: ['', Validators.required],
            yearOfPublication: ['',
            Validators.compose( [Validators.required, Validators.minLength(4), Validators.maxLength(4), Validators.pattern('^[0-9]{4}$')])],
        });
        this.isEditMode = this.route.snapshot.queryParamMap.get('edit') ? true : false;
        if (this.isEditMode) {
            this.updateFields();
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
        if (this.isEditMode) {
            movieDto.id = JSON.parse(this.route.snapshot.queryParamMap.get('movieDto')).id;
        }
        this.loading = true;
        this.saveMovie(movieDto);
    }

    public updateFields() {
        const movieDto: MovieDto = JSON.parse(this.route.snapshot.queryParamMap.get('movieDto'));
        this.a.name.setValue(movieDto.name);
        this.a.directorsName.setValue(movieDto.directorsName);
        this.a.yearOfPublication.setValue(movieDto.yearOfPublication);
    }

    public saveMovie(movieDto: MovieDto) {
        this.movieService.saveMovie(movieDto)
            .subscribe(
            () => {
              if (this.isEditMode) {
                this.alertService.success('Movie updated successfully', true);
              } else {
                this.alertService.success('Movie added successfully', true);
              }
              this.router.navigate(['home']);
            },
            (error) => {
              this.alertService.error('Try to save again your movie!');
              this.loading = false;
            });
    }
}
