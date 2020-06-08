import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserDto } from '@app/dto/userDto';
import { UserLoginDto } from '@app/dto/userLoginDto';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {

    private baseUrl = 'http://localhost:8080';

    constructor(private http: HttpClient) {}

    public login(userLoginDto: UserLoginDto): Observable<UserDto> {
        return this.http.post<any>(`${this.baseUrl}/login`, userLoginDto);
    }

    public isUserLoggedIn() {
        if (sessionStorage.getItem('currentUserName')) {
            return true;
        } else {
            return false;
        }
    }

    public logout() {
        sessionStorage.removeItem('currentUserName');
    }
}
