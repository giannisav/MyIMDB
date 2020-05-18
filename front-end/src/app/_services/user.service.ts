import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '@app/_models/user';
import { UserDto } from '@app/dto/userDto';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class UserService {

    private baseUrl = 'http://localhost:8080/users';

    constructor(private http: HttpClient) { }

    public register(userDto: UserDto): Observable<User> {
        return this.http.post<User>(`${this.baseUrl}/register`, userDto);
    }
}
