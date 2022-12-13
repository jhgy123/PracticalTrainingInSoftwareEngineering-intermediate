import { Component } from '@angular/core';
import {MyserviceService} from "../myservice.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent {
  constructor(public myservice:MyserviceService,
              public router:Router,
              public routeInfo:ActivatedRoute) {
  }
  total:number=0;
  busname:string='';
  deprice:number=0;
  cartids:any[]=[];
  cartidsnum:number[]=[];
  businessid:number=0;
  usernameid:any;
  foods:any[]=[];
  addressid:number=0;//记录订单对应的地址id
  orderid:number=0;
  ngOnInit() {
    //接收传递过来的参数值，接收商家编号,用户名id
    this.usernameid=localStorage.getItem("authusername");//获取本地的用户名信息
    this.cartids=this.routeInfo.snapshot.queryParams['cartids'];
    this.busname=this.routeInfo.snapshot.queryParams['busname'];
    this.deprice=this.routeInfo.snapshot.queryParams['dprice'];
    this.total=this.routeInfo.snapshot.queryParams['totalfee'];
    this.businessid=this.routeInfo.snapshot.queryParams['businessid'];
    this.addressid=this.routeInfo.snapshot.queryParams['addid'];
    //创建订单
    this.myservice.doaddorder(this.businessid,this.usernameid,this.addressid,this.total)
      .subscribe((response:any)=>{
        console.log(response);
        this.orderid=response.id;
      },
      (error) => {
        console.log("Some error in catch");
        if (error.status === 401 || error.status === 403){
          this.router.navigate(['/login']);//没有权限，跳转登录页面
        }
      });
    this.myservice.dogetcart(this.businessid,this.usernameid).subscribe((response:any)=>{
      this.foods=response;
      console.log(response);
      //创建详细订单
      for(let i=0;i<this.foods.length;++i){
        this.myservice.doaddorderdetail(this.foods[i].food.id,this.orderid,this.foods[i].quantity).subscribe((response:any)=>{
            console.log(response);
          },
          (error) => {
            console.log("Some error in catch");});
      }
    }, (error) => {
      console.log("Some error in catch");
      if (error.status === 401 || error.status === 403){
        this.router.navigate(['/login']);//没有权限，跳转登录页面
      }
    });
    for(let i=0;i<this.cartids.length;++i){
      this.cartidsnum.push(Number(this.cartids[i]));
    }
    console.log(this.cartidsnum);
    //删除购物车记录
    this.myservice.dodeletecart(this.cartidsnum).subscribe((response:any)=>{
      },
      (error) => {
        console.log("Some error in catch");
        if (error.status === 401 || error.status === 403){
          this.router.navigate(['/login']);//没有权限，跳转登录页面
        }
      });
  }
  //支付订单
  payorder(){
    this.myservice.dopayorder(this.orderid).subscribe((response:any)=>{
        console.log(response);
        this.router.navigate(['/index']);//跳转首页页面
      },
      (error) => {
        console.log("Some error in catch");});
  }
}
