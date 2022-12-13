import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from "@angular/forms";//MVVM
import { HttpClientModule } from '@angular/common/http';
import { IndexComponent } from './index/index.component';
import { BusinessInfComponent } from './business-inf/business-inf.component';
import { BusinessListComponent } from './business-list/business-list.component';
import { HistoricalOrdersComponent } from './historical-orders/historical-orders.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { OrderComponent } from './order/order.component';
import { PaymentComponent } from './payment/payment.component';
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    BusinessInfComponent,
    BusinessListComponent,
    HistoricalOrdersComponent,
    HomeComponent,
    LoginComponent,
    OrderComponent,
    PaymentComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
