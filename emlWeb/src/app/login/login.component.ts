import { Component } from '@angular/core';
import { MyserviceService } from '../myservice.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(public myservice:MyserviceService) {
    this.inf=this.myservice.logininf;
  }
  username:any;
  password:any;
  inf:string='';

  login(){
    this.myservice.dologin(this.username,this.password);
    this.inf=this.myservice.logininf;
  }

}
