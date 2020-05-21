import { Component, OnDestroy, OnInit } from '@angular/core';
import { AlertService } from '@app/_services/alert.service';
import { Subscription } from 'rxjs';

@Component({ selector: 'app-alert',
             styleUrls: ['./alert.component.scss'],
             templateUrl: './alert.component.html' })
export class AlertComponent implements OnInit, OnDestroy {
    private subscription: Subscription;
    public message: any;

    constructor(private alertService: AlertService) { }

    public ngOnInit() {
        this.subscription = this.alertService.getAlert()
            .subscribe((message) => {
                switch (message && message.type) {
                    case 'success':
                        message.cssClass = 'alert alert-success';
                        break;
                    case 'error':
                        message.cssClass = 'alert alert-danger';
                        break;
                }
                this.message = message;
            });
    }

    public ngOnDestroy() {
        this.subscription.unsubscribe();
    }
}
