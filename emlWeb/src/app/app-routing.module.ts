import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { BusinessInfComponent } from './business-inf/business-inf.component';
import { BusinessListComponent } from './business-list/business-list.component';
import { HistoricalOrdersComponent } from './historical-orders/historical-orders.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { OrderComponent } from './order/order.component';
import { PaymentComponent } from './payment/payment.component';
import { RegisterComponent } from './register/register.component';
//配置路由，默认路由为login
const routes: Routes = [
  {path:'',pathMatch:"full",redirectTo:"login"},
  {path:'businessinf',component: BusinessInfComponent},
  {path:'businesslist',component: BusinessListComponent},
  {path:'historicalorders',component: HistoricalOrdersComponent},
  {path:'index',component: IndexComponent},
  {path:'login',component: LoginComponent},
  {path:'order',component: OrderComponent},
  {path:'payment',component: PaymentComponent},
  {path:'register',component: RegisterComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
