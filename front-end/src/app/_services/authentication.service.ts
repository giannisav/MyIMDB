import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserLoginDto } from '@app/dto/userLoginDto';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {

    private baseUrl = 'http://localhost:8080';

    constructor(private http: HttpClient) {}

    public login(userLoginDto: UserLoginDto): Observable<boolean> {
        // if(this.http.post<any>(`${this.baseUrl}/login`, { username, password } )) {
        //     sessionStorage.setItem('username',username);
        //     return true;
        // } else {
        //     return false;
        // }
        return this.http.post<any>(`${this.baseUrl}`, userLoginDto );
    }

    public isUserLoggedIn() {
        const user = sessionStorage.getItem('username');
        return !(user === null);
    }

    public logout() {
        sessionStorage.removeItem('username');
    }
}