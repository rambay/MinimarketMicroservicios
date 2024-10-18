import { Component, OnInit } from '@angular/core';
import { SigninService } from 'src/app/services/signin.service';
import { StoreService } from 'src/app/services/store.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  isLoginActive: string = 'false';
  myRol: string = '';
  countCarrito: number;

  constructor(
    private signinService: SigninService,
    private _storeService: StoreService
  ) {}

  ngOnInit(): void {
    this.signinService.isLoggedIn$.subscribe((isLoggedIn) => {
      this.isLoginActive = isLoggedIn;
    });

    this.signinService.myRol$.subscribe((rol) => {
      this.myRol = rol;
    });

    this._storeService.productosCarrito$.subscribe((productos) => {
      this.countCarrito = productos.length;
    });
  }

  logout() {
    this.signinService.logout();
  }
}
