import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsuariosComponent } from './components/usuarios/usuarios.component';
import { ProductosComponent } from './components/productos/productos.component';
import { SigninComponent } from './components/signin/signin.component';
import { SignupComponent } from './components/signup/signup.component';
import { HomeComponent } from './components/home/home.component';
import { ProveedoresComponent } from './components/proveedores/proveedores.component';
import { AuthGuard } from './helpers/auth.guard';
import { NotfoundComponent } from './components/notfound/notfound.component';
import { StoreComponent } from './components/store/store.component';
import { ShopcartComponent } from './components/shopcart/shopcart.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: SigninComponent },
  { path: 'register', component: SignupComponent },
  { path: 'store', component: StoreComponent },
  { path: 'cart', component: ShopcartComponent },
  {
    path: 'productos',
    component: ProductosComponent,
    canActivate: [AuthGuard],
  },
  { path: 'usuarios', component: UsuariosComponent, canActivate: [AuthGuard] },
  {
    path: 'proveedores',
    component: ProveedoresComponent,
    canActivate: [AuthGuard],
  },
  { path: '**', component: NotfoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
