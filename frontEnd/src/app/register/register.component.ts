import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from '@app/_services/alert.service';
import { AuthenticationService } from '@app/_services/authentication.service';
import { UserService } from '@app/_services/user.service';
import { UserDto } from '@app/dto/userDto';

@Component({ selector: 'app-register',
             styleUrls: ['./register.component.scss'],
             templateUrl: './register.component.html' })
export class RegisterComponent implements OnInit {
    public registerForm: FormGroup;
    public loading = false;
    public submitted = false;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private authenticationService: AuthenticationService,
        private userService: UserService,
        private alertService: AlertService,
    ) {
        if (this.authenticationService.isUserLoggedIn()) {
            this.router.navigate(['']);
        }
    }

    public ngOnInit() {
        this.registerForm = this.formBuilder.group({
            firstName: ['', Validators.required],
            lastName: ['', Validators.required],
            username: ['', Validators.required],
            password: ['', [Validators.required, Validators.minLength(3)]],
        });
    }

    get f() { return this.registerForm.controls; }

    public onSubmit() {
        this.submitted = true;

        this.alertService.clear();

        if (this.registerForm.invalid) {
            return;
        }
        const userDto: UserDto = this.registerForm.value;
        this.loading = true;
        this.userService.register(userDto)
            .subscribe(
                (data) => {
                    this.alertService.success('Registration successful', true);
                    this.router.navigate(['']);
                },
                (error) => {
                    this.alertService.error('Registration error, try again!');
                    this.loading = false;
                });
    }
}
