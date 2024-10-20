import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProductosComponent } from './components/productos/productos.component';
import { UsuariosComponent } from './components/usuarios/usuarios.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TiposproductosComponent } from './components/tiposproductos/tiposproductos.component';
import { SigninComponent } from './components/signin/signin.component';
import { SignupComponent } from './components/signup/signup.component';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './components/home/home.component';
import { ProveedoresComponent } from './components/proveedores/proveedores.component';
import { NotfoundComponent } from './components/notfound/notfound.component';
import { StoreComponent } from './components/store/store.component';
import { ShopcartComponent } from './components/shopcart/shopcart.component';
import { ShippingComponent } from './components/shipping/shipping.component';
import { RouterModule } from '@angular/router';
import { PaymentComponent } from './components/payment/payment.component';
import { BoletasComponent } from './components/boletas/boletas.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ProductosComponent,
    UsuariosComponent,
    TiposproductosComponent,
    SigninComponent,
    SignupComponent,
    HomeComponent,
    ProveedoresComponent,
    NotfoundComponent,
    StoreComponent,
    ShopcartComponent,
    ShippingComponent,
    PaymentComponent,
    BoletasComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    AppRoutingModule,
    CommonModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
