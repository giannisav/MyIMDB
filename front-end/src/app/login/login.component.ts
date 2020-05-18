import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from '@app/_services/alert.service';
import { AuthenticationService } from '@app/_services/authentication.service';
import { UserLoginDto } from '@app/dto/userLoginDto';
import { first } from 'rxjs/operators';

@Component({ templateUrl: 'login.component.html' })
export class LoginComponent implements OnInit {
    public loginForm: FormGroup;
    public loading = false;
    public submitted = false;
    public returnUrl: string;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private alertService: AlertService,
    ) {

        if (!this.authenticationService.isUserLoggedIn()) {
            this.router.navigate(['']);
        }
    }

    public ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required],
        });

        // this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    get f() { return this.loginForm.controls; }

    public onSubmit() {
        this.submitted = true;

        this.alertService.clear();

        if (this.loginForm.invalid) {
            return;
        }
        const userLoginDto: UserLoginDto = this.loginForm.value;
        this.loading = true;
        this.authenticationService.login(userLoginDto)
            .subscribe(
                (data) => {
                    if (data) {
                    this.alertService.success('Sucessful logged in', true);
                    sessionStorage.setItem('username', userLoginDto.username);
                    this.router.navigate(['home']);
                    } else {
                        this.alertService.error('Wrong username or password');
                    }
                },
                (error) => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    }
}
