import { Component } from '@angular/core';
import { MyserviceService } from '../myservice.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  constructor(public myservice:MyserviceService) {
  }
  username:string='';
  password:string='';
  password1:string='';
  sex:boolean=false;
  inf:string='';
  //用户注册
  register(){
    if(this.password!=this.password1){
      this.inf="两次密码输入不一致！"
    }
    else{
      this.myservice.doregister(this.username,this.password,this.sex)
    }
  }

}
