import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '@app/_services/authentication.service';
import { MovieService } from '@app/_services/movie.service';
import { SnackBarService } from '@app/_services/snack-bar.service';
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

  constructor(
      private formBuilder: FormBuilder,
      private authenticationService: AuthenticationService,
      private route: ActivatedRoute,
      private router: Router,
      private movieService: MovieService,
      private snackBar: SnackBarService,
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
        // this.movieForm.reset();
        this.isEditMode = this.route.snapshot.queryParamMap.get('edit') ? true : false;
        if (this.isEditMode) {
            this.updateFields();
        }
    }

    get f() { return this.movieForm.controls; }

    public onSubmit() {

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
        this.f.name.setValue(movieDto.name);
        this.f.directorsName.setValue(movieDto.directorsName);
        this.f.yearOfPublication.setValue(movieDto.yearOfPublication);
    }

    public saveMovie(movieDto: MovieDto) {
        this.movieService.saveMovie(movieDto)
            .subscribe(
            () => {
              if (this.isEditMode) {
                this.snackBar.openSnackBar('Movie updated successfully', 'Ok');
              } else {
                this.snackBar.openSnackBar('Movie saved successfully', 'Ok');
              }
              this.router.navigate(['home']);
            },
            (error) => {
              this.snackBar.openSnackBar('Movie did not saved sucessfully. Please try again!', 'Ok');
              this.loading = false;
            });
    }
}
