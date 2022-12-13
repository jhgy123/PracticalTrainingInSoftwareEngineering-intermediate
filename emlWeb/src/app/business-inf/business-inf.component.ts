import { Component } from '@angular/core';
import {MyserviceService} from "../myservice.service";
import {Router,ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-business-inf',
  templateUrl: './business-inf.component.html',
  styleUrls: ['./business-inf.component.css']
})
export class BusinessInfComponent {

  constructor(public myservice:MyserviceService,
              public router:Router,
              public routeInfo:ActivatedRoute) {

  }
  allfood:any[]=[];
  bid:number=0;//商家编号
  img:string='';//商家图片
  name:string='';//商家名称
  starPrice:number=0;
  deliveryPrice:number=0;
  explain:string='';
  foodids:number[]=[];
  prices:number[]=[];
  foodnums:number[]=[];
  totalfee:number=0;//总计费用
  authusernameid:string='';

  ngOnInit(){
    //接收传递过来的参数值，接收商家编号
    this.bid=this.routeInfo.snapshot.queryParams['busid'];
    console.log(this.bid)
    this.myservice.getallfoodbybusinessid(this.bid).subscribe((response:any)=>{
        console.log(response);
        this.allfood=response;
        this.img=this.allfood[0].business.img;
        this.name=this.allfood[0].business.name;
        this.starPrice=this.allfood[0].business.starPrice;
        this.deliveryPrice=this.allfood[0].business.deliveryPrice;
        this.explain=this.allfood[0].business.explain;
        for(let i=0;i<this.allfood.length;i++){
          this.foodids[i]=this.allfood.at(i).id;
          this.prices[i]=this.allfood.at(i).foodPrice;
          this.foodnums[i]=0;
        }
        console.log(this.foodids);
        console.log(this.foodnums);
      },
      (error) => {
        console.log("Some error in catch");
        if (error.status === 401 || error.status === 403){
          this.router.navigate(['/login']);//没有权限，跳转登录页面
        }
      })
  }

  add(index:number){
    this.totalfee=this.totalfee+this.prices[index];
    this.foodnums[index]++;
  }

  sub(index:number){
    this.totalfee=this.totalfee-this.prices[index];
    this.foodnums[index]--;
  }

  addcart(){
    // @ts-ignore
    this.authusernameid=localStorage.getItem("authusername");//获取本地的用户名信息
    for(let i=0;i<this.foodids.length;++i){
      if(this.foodnums[i]!=0){
        this.myservice.doaddcart(this.bid,this.foodids[i],this.authusernameid,this.foodnums[i]).subscribe(
          (response:any)=>{
            console.log(response);
          },
          (error) => {
            console.log("Some error in catch");
            if (error.status === 401 || error.status === 403){
              this.router.navigate(['/login']);//没有权限，跳转登录页面
            }
          });
      }
    }
    this.router.navigate(['/order'],{ queryParams:{busid:this.bid,userid:this.authusernameid} });//跳转订单页面并传参
  }
}
