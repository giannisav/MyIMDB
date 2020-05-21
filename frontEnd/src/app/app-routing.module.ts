import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './_services/auth.guard';
import { CrudMovieComponent } from './crud_movie/crud-movie.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
    { path: '', component: LoginComponent },
    { path: 'users/register', component: RegisterComponent },
    { path: 'movies/save', component: CrudMovieComponent, canActivate: [AuthGuard] },
    { path: 'movies/delete/:id', component: HomeComponent, canActivate: [AuthGuard] },

    // otherwise redirect to home
    { path: '**', redirectTo: '' },
];

export const appRoutingModule = RouterModule.forRoot(routes);
