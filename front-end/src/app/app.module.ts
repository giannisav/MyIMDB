import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AlertComponent } from './_components/alert.component';
import { appRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CrudMovieComponent } from './crud_movie/crud-movie.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

@NgModule({
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        FormsModule,
        HttpClientModule,
        appRoutingModule,
    ],
    declarations: [
        AppComponent,
        HomeComponent,
        LoginComponent,
        RegisterComponent,
        AlertComponent,
        CrudMovieComponent,
    ],
    providers: [],
    bootstrap: [AppComponent],
})
export class AppModule { }