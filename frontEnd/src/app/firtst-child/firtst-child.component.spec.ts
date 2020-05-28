import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FirtstChildComponent } from './firtst-child.component';

describe('FirtstChildComponent', () => {
  let component: FirtstChildComponent;
  let fixture: ComponentFixture<FirtstChildComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FirtstChildComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FirtstChildComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
