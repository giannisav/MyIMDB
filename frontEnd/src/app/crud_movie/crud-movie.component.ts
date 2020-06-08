import { AfterViewInit, ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieService } from '@app/_services/movie.service';
import { SnackBarService } from '@app/_services/snack-bar.service';
import { MovieDto } from '@app/dto/movieDto';

@Component({
  selector: 'app-crud-movie',
  styleUrls: ['./crud-movie.component.scss'],
  templateUrl: './crud-movie.component.html',
})
export class CrudMovieComponent implements OnInit, AfterViewInit {
    public isUserlogged: boolean;
    public movieForm: FormGroup;
    public id: number;
    public isEditMode = true;
    public loading = false;

  constructor(
      private formBuilder: FormBuilder,
      private route: ActivatedRoute,
      private router: Router,
      private movieService: MovieService,
      private snackBar: SnackBarService,
      private changeDetector: ChangeDetectorRef,
  ) {}

    public ngOnInit() {
      this.movieForm = this.formBuilder.group({
        title: ['', Validators.required],
        director: ['', Validators.required],
        yearOfPublication: ['',
        Validators.compose( [Validators.required, Validators.minLength(4), Validators.maxLength(4), Validators.pattern('^[0-9]{4}$')])],
        description: ['', Validators.required],
        });
    }

    get f() { return this.movieForm.controls; }

    public onSubmit() {
      if (this.movieForm.invalid) {
          return this.snackBar.openSnackBar('Invalid fields', 'Ok');
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
      this.movieForm.patchValue(movieDto);
    }

    public ngAfterViewInit() {
      this.route.queryParams.subscribe((data) => {
        this.isEditMode = data.edit;
      });
      this.isEditMode ? this.updateFields() : this.movieForm.reset();
      this.changeDetector.detectChanges();
    }

    public saveMovie(movieDto: MovieDto) {
      this.movieService.saveMovie(movieDto).subscribe(
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
