import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-second-child',
  templateUrl: './second-child.component.html',
  styleUrls: ['./second-child.component.scss'],
})
export class SecondChildComponent implements OnInit {

  public movieForm: FormGroup = this.formBuilder.group({
    directorsName: ['', Validators.required],
  });

  constructor(private formBuilder: FormBuilder) { }

  public ngOnInit(): void {}

  public setDirectorsName(directorsName: string) {
    this.movieForm.controls.directorsName.setValue(directorsName);
  }

}
