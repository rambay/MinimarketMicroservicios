import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StoreService } from 'src/app/services/store.service';

@Component({
  selector: 'app-shopcart',
  templateUrl: './shopcart.component.html',
  styleUrls: ['./shopcart.component.css'],
})
export class ShopcartComponent implements OnInit {
  productosCart: any[] = [];

  constructor(private _storeService: StoreService, private router: Router) {}

  ngOnInit(): void {
    this._storeService.productosCarrito$.subscribe((productos) => {
      this.productosCart = productos;
    });
  }

  realizarCompra() {
    this.router.navigate(['cart/shipping']);
  }

  eliminarProducto(producto: any) {
    this._storeService.eliminarProducto(producto);
  }

  calcularTotal(): number {
    return this.productosCart.reduce((total, producto) => {
      return total + producto.precio * producto.total;
    }, 0);
  }

  actualizarCantidad(producto: any) {
    if (producto.total < 1) {
      producto.total = 1;
    }

    if (producto.total > producto.cantidad) {
      producto.total = producto.cantidad;
      alert(
        `Solo quedan ${producto.cantidad} unidades disponibles para ${producto.nombre}`
      );
    }

    this.calcularTotal();
  }

  calcularTotalProductos(): number {
    return this.productosCart.length;
  }
}
