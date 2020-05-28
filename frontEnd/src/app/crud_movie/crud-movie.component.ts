import { AfterViewInit, ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieService } from '@app/_services/movie.service';
import { SnackBarService } from '@app/_services/snack-bar.service';
import { MovieDto } from '@app/dto/movieDto';
import { FirtstChildComponent } from '@app/firtst-child/firtst-child.component';
import { SecondChildComponent } from '@app/second-child/second-child.component';

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

    @ViewChild('movieName') public child1: FirtstChildComponent;
    @ViewChild('directorsName') public child2: SecondChildComponent;

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
        directorsName: ['', Validators.required],
        name: ['', Validators.required],
        yearOfPublication: ['',
        Validators.compose( [Validators.required, Validators.minLength(4), Validators.maxLength(4), Validators.pattern('^[0-9]{4}$')])],
        });
    }

    get f() { return this.movieForm.controls; }

    public onSubmit() {
      this.f.name.patchValue(this.child1.movieForm.controls.name.value);
      this.f.directorsName.setValue(this.child2.movieForm.controls.directorsName.value);

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
      this.f.yearOfPublication.setValue(movieDto.yearOfPublication);
      this.child1.setName(movieDto.name);
      this.child2.setDirectorsName(movieDto.directorsName);
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
