import { Component } from '@angular/core';
import {MyserviceService} from "../myservice.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent {
  constructor(public myservice:MyserviceService,
              public router:Router,
              public routeInfo:ActivatedRoute) {

  }

  businessid:number=0;//商家编号
  userid:string='';//用户id
  carts:any[]=[];//全部返回结果
  cartids:any[]=[];//相关的食物信息
  prices:number[]=[];//对于价格
  nums:number[]=[];//对应的数量
  businessname:string='';//商家名称
  deveprice:number=0;//配送费
  total:number=0;//总费用
  myaddress:any;
  myaddress_address:string='';
  myaddress_contactName: string='';
  myaddress_contactTel: string='';
  myaddress_id: number=0;
  ngOnInit(){
    //接收传递过来的参数值，接收商家编号,用户名id
    this.routeInfo.queryParams.subscribe(params => {
      this.businessid=params['busid'];
      this.userid=params['userid'];
    });
    this.myservice.dogetcart(this.businessid,this.userid).subscribe((response:any)=>{
        console.log(response);
        this.carts=response;
        this.businessname=response[0].business.name;
        this.deveprice=response[0].business.deliveryPrice;
        for(let i=0;i<this.carts.length;++i){
          this.cartids[i]=this.carts[i].id;
          this.total=this.total+this.carts[i].food.foodPrice*this.carts[i].quantity;
        }
        this.total=this.total+this.deveprice;
        console.log(this.cartids);
      },
      (error) => {
        console.log("Some error in catch");
        if (error.status === 401 || error.status === 403){
          this.router.navigate(['/login']);//没有权限，跳转登录页面
        }
      });
    this.getaddress(this.userid);
  }
  getaddress(userid:string){
    this.myservice.dogetaddresslist(userid).subscribe((response:any)=>{
        console.log(response);
        this.myaddress=response[0];
        this.myaddress_address=response[0].address;
        this.myaddress_contactName=response[0].contactName;
        this.myaddress_contactTel=response[0].contactTel;
        this.myaddress_id=response[0].id;
      },
      (error) => {
        console.log("Some error in catch");
        if (error.status === 401 || error.status === 403){
          this.router.navigate(['/login']);//没有权限，跳转登录页面
        }
      })
  }
}
