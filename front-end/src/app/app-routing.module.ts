import { RouterModule, Routes } from '@angular/router';
import { CrudMovieComponent } from './crud_movie/crud-movie.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: '', component: LoginComponent },
    { path: 'users/register', component: RegisterComponent },
    { path: 'movies/add', component: CrudMovieComponent },
    { path: 'movies/edit/', component: CrudMovieComponent },
    { path: 'movies/delete/:id', component: HomeComponent },

    // otherwise redirect to home
    { path: '**', redirectTo: '' },
];

export const appRoutingModule = RouterModule.forRoot(routes);
