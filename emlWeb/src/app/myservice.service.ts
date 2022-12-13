import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { Router } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class MyserviceService {

  constructor(public http:HttpClient,
              public  router:Router) {
  }
  baseurl:string="http://127.0.0.1:8081";
  authusername:string='';
  token:string='';
  public logininf:string='';


//用户登录
  dologin(name:string,password:string){
    let me=this;
    //默认是observe: 'body', responseType: 'json'，可以不用写
    const httpsoption={headers:new HttpHeaders({'Content-Type':'application/json',observe: 'body', responseType: 'json'})};
    var url=this.baseurl+'/pub/auth/auth-token';
    var mybody={
      "username": name,
      "password": password,
      "tokenType": "info",
      "grant_type": "password",
      "client_id": "oauth_isolationArea",
      "Access-Control-Allow-Origin": "*"
    };
    this.http.post(url,mybody,httpsoption).subscribe((response:any)=>{
      this.authusername = response.username;//获取responese的json中的属性
      const retoken = response.accessToken;//获取responese的json中的属性
      console.log(response);
      this.token=retoken;
      localStorage.setItem("mytoken", this.token);
      localStorage.setItem("authusername", this.authusername)
      console.log(this.token)
      me.router.navigate(['/index']);
      },
      (error) => {
        console.log("Some error in catch");
        if (error.status === 401 || error.status === 403){
          this.logininf="用户名或密码错误";
          me.router.navigate(['/login']);//验证失败，继续跳转至登录页面
          }
        }
      );
  }
  //用户注册
  doregister(username:string,password:string,sex:boolean){
    // @ts-ignore
    this.token=localStorage.getItem("mytoken");
    const httpsoption={headers:new HttpHeaders({'Content-Type':'application/json',Authorization:'Bearer ' + this.token})};
    var url=this.baseurl+'/user';
    var mybody={
      "createdDate": new Date(),
      "lastModifiedDate": new Date(),
      "id": username,
      "password": "{noop}"+password,
      "username": username,
      "sex": sex,
      "img": "",
      "delTag": false,
      "enabled": true,
      "credentialsNonExpired": true,
      "accountNonLocked": true,
      "accountNonExpired": true,
      "credentialsExpiredDate": "",
      "accountExpiredDate": "",
      "accessToken": "",
      "entityId": {}
    };
    this.http.post(url,mybody,httpsoption).subscribe((response:any)=>{
      console.log(response);
      this.router.navigate(['/login']);
    },
    (error) => {
      console.log("Some error in catch");
      if (error.status === 401 || error.status === 403){
        this.router.navigate(['/register']);//注册失败，继续跳转至注册页面
      }
    })
  }
  //获取所有商家信息
  getallbusiness(){
    // @ts-ignore
    this.token=localStorage.getItem("mytoken");
    const httpsoption={headers:new HttpHeaders({'Content-Type':'application/json',Authorization:'Bearer ' + this.token})};
    var url=this.baseurl+'/business/all';
    return this.http.get(url,httpsoption);
  }
  //根据商家编号获取所有的食物
  getallfoodbybusinessid(bid:number){
    // @ts-ignore
    this.token=localStorage.getItem("mytoken");
    const httpsoption={headers:new HttpHeaders({'Content-Type':'application/json',Authorization:'Bearer ' + this.token})};
    var url=this.baseurl+'/food/findbybusiness';
    var mybody={
      "id": bid
    };
    return this.http.post(url,mybody,httpsoption);
  }
//新增购物车内容
  doaddcart(businessid:number,foodid:number,username:string,quantity:number){
    // @ts-ignore
    this.token=localStorage.getItem("mytoken");//获取本地的用户token信息
    const httpsoption={headers:new HttpHeaders({'Content-Type':'application/json',Authorization:'Bearer ' + this.token})};
    var url=this.baseurl+'/cart';
    var mybody=
      {
        "createdDate": new Date(),
        "lastModifiedDate": new Date(),
        "food": {
          "id": foodid
        },
        "business": {
          "id": businessid
        },
        "user": {
          "id": username
        },
        "quantity": quantity
      };
      return this.http.post(url,mybody,httpsoption);
  }

  //根据商家和用户查询购物车
  dogetcart(businessid:number,userid:string){
    // @ts-ignore
    this.token=localStorage.getItem("mytoken");//获取本地的用户token信息
    const httpsoption={headers:new HttpHeaders({'Content-Type':'application/json',Authorization:'Bearer ' + this.token}),params: {businessid:businessid,userid:userid}};
    var url=this.baseurl+'/cart/findcartsbybusinessanduser';
    return this.http.get(url,httpsoption);
  }
  //获取用户的所有收货地址记录
  dogetaddresslist(userid:string){
    // @ts-ignore
    this.token=localStorage.getItem("mytoken");//获取本地的用户token信息
    const httpsoption={headers:new HttpHeaders({'Content-Type':'application/json',Authorization:'Bearer ' + this.token})};
    var url=this.baseurl+'/deliveryaddress/findbyuser';
    var mybody=
      {
        "id": userid
      }
    return this.http.post(url,mybody,httpsoption);
  }
  //根据购物车id集合删除购物车记录
  dodeletecart(ids:number[]){
    // @ts-ignore
    this.token=localStorage.getItem("mytoken");//获取本地的用户token信息
    const httpsoption={headers:new HttpHeaders({'Content-Type':'application/json',Authorization:'Bearer ' + this.token})};
    var url=this.baseurl+'/cart/batchdeletebycarts';
    var mybody=[];
    for(let i=0;i<ids.length;++i){
      mybody.push({"id": ids[i]});
    }
    return this.http.post(url,mybody,httpsoption);
  }

  //根据商家id,用户id,地址id创建订单
  doaddorder(businessid:number,userid:string,addressid:number,total:number){
    // @ts-ignore
    this.token=localStorage.getItem("mytoken");//获取本地的用户token信息
    const httpsoption={headers:new HttpHeaders({'Content-Type':'application/json',Authorization:'Bearer ' + this.token})};
    var url=this.baseurl+'/orders';
    var mybody=
      {
        "createdDate": new Date(),
        "lastModifiedDate": new Date(),
        "user": {
          "id": userid
        },
        "business": {
          "id": businessid
        },
        "orderDate": new Date(),
        "orderTotal": total,
        "deliveryaddress": {
          "id": addressid
        },
        "orderState": false
      }
    return this.http.post(url,mybody,httpsoption);
  }
  //根据食物id,订单id,数量创建订单详细
  doaddorderdetail(foodid:number,orderid:number,quantity:number){
    // @ts-ignore
    this.token=localStorage.getItem("mytoken");//获取本地的用户token信息
    const httpsoption={headers:new HttpHeaders({'Content-Type':'application/json',Authorization:'Bearer ' + this.token})};
    var url=this.baseurl+'/orderdetailet';
    var mybody=
      {
        "createdDate": new Date(),
        "lastModifiedDate": new Date(),
        "orders": {
          "id": orderid
        },
        "food": {
          "id": foodid
        },
        "quantity": quantity
      }
    return this.http.post(url,mybody,httpsoption);
  }
  //支付订单，修改订单状态
  dopayorder(orderid:number){
    // @ts-ignore
    this.token=localStorage.getItem("mytoken");//获取本地的用户token信息
    const httpsoption={headers:new HttpHeaders({'Content-Type':'application/json',Authorization:'Bearer ' + this.token})};
    var url=this.baseurl+'/orders/pay';
    var mybody=
      {
        "id":orderid,
        "orderState": true
      }
    return this.http.put(url,mybody,httpsoption);
  }

  //根据用户和订单状态查询所有订单
  dogetorderbyuserandstate(userid:string,state:boolean){
    // @ts-ignore
    this.token=localStorage.getItem("mytoken");//获取本地的用户token信息
    const httpsoption={headers:new HttpHeaders({'Content-Type':'application/json',Authorization:'Bearer ' + this.token}),params: {state:state}};
    var url=this.baseurl+'/orders/historyorders';
    var mybody=
      {
        "id": "test"
      };
    return this.http.post(url,mybody,httpsoption);

  }
  //根据订单编号查询详细订单列表
  dogetorderdetilbyorderid(order:number){
    // @ts-ignore
    this.token=localStorage.getItem("mytoken");//获取本地的用户token信息
    const httpsoption={headers:new HttpHeaders({'Content-Type':'application/json',Authorization:'Bearer ' + this.token})};
    var url=this.baseurl+'/orderdetailet/findbyorders';
    var mybody=
    {
      "id":order
    };
    return this.http.post(url,mybody,httpsoption);
  }




// dogetall(){
//   const httpsoption={headers:new HttpHeaders({'Content-Type':'application/json',Authorization:'Bearer ' + this.token})};
//   var url='http://127.0.0.1:8081/business/all';
//   // var mybody={
//   //   "username": "testuser",
//   //   "password": "www",
//   //   "tokenType": "info",
//   //   "grant_type": "password",
//   //   "client_id": "oauth_isolationArea",
//   //   "Access-Control-Allow-Origin": "*"
//   // };
//   this.http.get(url,httpsoption).subscribe((response:any)=>{
//     console.log(response);
//     this.data1=response;
//   })
// }

// getuser(){
//   const httpsoption={headers:new HttpHeaders({'Content-Type':'application/json',Authorization:'Bearer ' + this.token})};
//   var url='http://127.0.0.1:8081/user/all';
//   this.http.get(url,httpsoption).subscribe((response:any)=>{
//     console.log(response);
//     this.imagedata2=response;
//     this.im=this.imagedata2[0];
//   })
//
// }
}



