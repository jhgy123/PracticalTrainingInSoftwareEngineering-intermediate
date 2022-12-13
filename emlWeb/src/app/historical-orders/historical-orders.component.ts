import {Component, ElementRef,ViewChild} from '@angular/core';
import {MyserviceService} from "../myservice.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-historical-orders',
  templateUrl: './historical-orders.component.html',
  styleUrls: ['./historical-orders.component.css']
})
export class HistoricalOrdersComponent {

  constructor(public myservice:MyserviceService,
              public router:Router,
              public el:ElementRef) {

  }
  detailetlist:any[]=[];
  userid:string='';
  payed:any[]=[];//支付了订单
  nopay:any[]=[];//未支付订单
  flag:boolean=false;
  ngOnInit(){
    // @ts-ignore
    this.userid=localStorage.getItem("authusername");//获取本地的用户id信息
    this.myservice.dogetorderbyuserandstate(this.userid,true).subscribe((response:any)=>{
      this.payed=response;
      console.log(response);
    },
    (error) => {
      console.log("Some error in catch");
      if (error.status === 401 || error.status === 403){
        this.router.navigate(['/login']);//没有权限，跳转登录页面
      }
    });
    this.myservice.dogetorderbyuserandstate(this.userid,false).subscribe((response:any)=>{
        this.nopay=response;
        console.log(response);
      },
      (error) => {
        console.log("Some error in catch");
        if (error.status === 401 || error.status === 403){
          this.router.navigate(['/login']);//没有权限，跳转登录页面
        }
      });
  }

  getlist(id:number) {
    this.myservice.dogetorderdetilbyorderid(id).subscribe((response: any) => {
      this.detailetlist = response;
      console.log(this.detailetlist);
    });
  }

}
