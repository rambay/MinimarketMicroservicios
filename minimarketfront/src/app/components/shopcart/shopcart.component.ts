import { Component, OnInit } from '@angular/core';
import { StoreService } from 'src/app/services/store.service';

@Component({
  selector: 'app-shopcart',
  templateUrl: './shopcart.component.html',
  styleUrls: ['./shopcart.component.css'],
})
export class ShopcartComponent implements OnInit {
  productosCart: any[] = [];

  constructor(private _storeService: StoreService) {}

  ngOnInit(): void {
    this._storeService.productosCarrito$.subscribe((productos) => {
      this.productosCart = productos;
    });
  }

  realizarCompra() {
    console.log('Compra realizada:', this.productosCart);
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
      producto.total = 1; // Evita que la cantidad sea menor que 1
    }

    if (producto.total > producto.cantidad) {
      producto.total = producto.cantidad; // No permite que supere la cantidad disponible
      alert(
        `Solo quedan ${producto.cantidad} unidades disponibles para ${producto.nombre}`
      );
    }

    this.calcularTotal(); // Recalcula el total después de la actualización
  }

  calcularTotalProductos(): number {
    return this.productosCart.length;
  }
}
