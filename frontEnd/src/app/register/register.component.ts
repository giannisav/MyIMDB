import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthenticationService } from '@app/_services/authentication.service';
import { SnackBarService } from '@app/_services/snack-bar.service';
import { UserService } from '@app/_services/user.service';
import { UserDto } from '@app/dto/userDto';

@Component({ selector: 'app-register',
             styleUrls: ['./register.component.scss'],
             templateUrl: './register.component.html' })
export class RegisterComponent implements OnInit {
    public registerForm: FormGroup;
    public loading = false;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private authenticationService: AuthenticationService,
        private userService: UserService,
        private snackBar: SnackBarService,
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
            password: ['', [Validators.required, Validators.minLength(6)]],
        });
    }

    get f() { return this.registerForm.controls; }

    public onSubmit() {

        if (this.registerForm.invalid) {
            return;
        }
        const userDto: UserDto = this.registerForm.value;
        this.loading = true;
        this.userService.register(userDto)
            .subscribe(
                (data) => {
                    this.snackBar.openSnackBar('Registration successful', 'Ok');
                    this.router.navigate(['']);
                },
                (error) => {
                    this.snackBar.openSnackBar('Registration error, try again!', 'Ok');
                    this.loading = false;
                });
    }
}
