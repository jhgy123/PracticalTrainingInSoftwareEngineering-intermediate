# 软件工程实训-中级
## 龚尹鸿杰 （20201050449）
### 后端
* 主要技术：spring、hibernate、jpa、knife4j-springdoc-ui、spring scurity、日志文件
* 项目结构目录：
  ![002](images/readme/007.jpg)
* 主要实现：
1. 使用jpa完成对各实体及对应的访问层。
   ![](images/readme/001.jpg)
2. 完成jpa数据访问层中的方法扩展，完成了商家按点餐分类编号查询，收货地址按用户查询，订单按用户查询，订单详情按订单号查询，用户按照用户名与密码登录等业务要求。
   ![002](images/readme/005.jpg)
3. 实现了各访问层对应的Service层与Controller层，实现了相关的api。
   ![002](images/readme/006.jpg)
4. 具备日志、api文档功能、分页查询功能。
   ![002](images/readme/002.jpg)
   ![003](images/readme/003.jpg)
   ![004](images/readme/004.jpg)
5. 增加spring scurity内容，具备JWT认证功能。
   ![004](images/readme/008.jpg)
   ![004](images/readme/009.jpg)
   ![002](images/readme/010.jpg)
6. 运行截图.
   ![002](images/readme/011.jpg)

### 前端
* 主要技术：angular、TypeScript。
* 项目结构目录：
  ![002](images/readme/012.jpg)
* 主要实现：
1. 创建angular项目，配置tailwind到angular项目中。
`·ng new my-project`
` cd my-project`
`npm install -D tailwindcss postcss autoprefixer`
 `npx tailwindcss init`
   ![002](images/readme/013.jpg)
   ![002](images/readme/014.jpg)

2. 在app-routing.module.ts中配置路由。
```  
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
```  
3. 创建service服务,使不同页面组件可以调用service中访问后端api的方法。
   `ng generate service`
4. 在service编写访问api的方法。例如：
```
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
```    
6. 创建页面组件，将tailwind页面迁移至此项目中。`ng generate component`
7. 对每个页面编写TypeScript，同时使用**MVVM**对页面中的内容进行双向绑定。
8. 运行结果展示。
    - 登录页面
   ![002](images/readme/015.jpg)
    - 注册页面
    ![002](images/readme/016.jpg)
    - 首页面
    ![002](images/readme/017.jpg)
    - 商家列表页面
    ![002](images/readme/018.jpg)
    - 商家详情页面
    ![002](images/readme/019.jpg)
    - 订单页面
    ![002](images/readme/020.jpg)
    - 支付页面
    ![002](images/readme/021.jpg)
    - 历史订单页面
    ![002](images/readme/022.jpg)



