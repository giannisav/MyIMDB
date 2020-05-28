import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-firtst-child',
  templateUrl: './firtst-child.component.html',
  styleUrls: ['./firtst-child.component.scss'],
})
export class FirtstChildComponent implements OnInit {

  public movieForm: FormGroup = this.formBuilder.group({
    name: ['', Validators.required],
  });

  constructor(private formBuilder: FormBuilder) { }

  public ngOnInit(): void { }

  public setName(name: string) {
    this.movieForm.controls.name.setValue(name);
  }

}
