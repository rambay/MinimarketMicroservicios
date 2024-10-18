import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class StoreService {
  private productosCarrito = new BehaviorSubject<any[]>(this.cargarCarrito());
  productosCarrito$ = this.productosCarrito.asObservable();

  constructor() {}

  cargarCarrito(): any[] {
    const carritoGuardado = localStorage.getItem('carrito');
    return carritoGuardado ? JSON.parse(carritoGuardado) : [];
  }

  agregarProducto(producto: any) {
    const carritoActual = this.productosCarrito.value;

    const productoExistente = carritoActual.find(
      (item) => item.idProducto === producto.idProducto
    );

    if (!productoExistente) {
      this.productosCarrito.next([
        ...carritoActual,
        { ...producto }, // Agrega el producto sin la cantidad
      ]);
    } else {
      console.log('Este producto ya está en el carrito.');
    }

    this.guardarCarrito();
  }

  obtenerCarrito() {
    return this.productosCarrito.value;
  }

  eliminarProducto(producto: any) {
    const carritoActual = this.productosCarrito.value.filter(
      (item) => item.idProducto !== producto.idProducto
    );
    this.productosCarrito.next(carritoActual);
    this.guardarCarrito();
  }

  guardarCarrito() {
    localStorage.setItem(
      'carrito',
      JSON.stringify(this.productosCarrito.value)
    );
  }
}
