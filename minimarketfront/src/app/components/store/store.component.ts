import { Component, OnInit } from '@angular/core';
import { ProductosService } from 'src/app/services/productos.service';
import { StoreService } from 'src/app/services/store.service';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css'],
})
export class StoreComponent implements OnInit {
  listadoProductos: any[] = [];
  cantidadSeleccionada: { [id: number]: number } = {};

  constructor(
    private _productsService: ProductosService,
    private _storeService: StoreService
  ) {}

  ngOnInit(): void {
    this.obtenerListadoProductos();
  }

  obtenerListadoProductos() {
    this._productsService.listarProductos().subscribe(
      (data) => {
        this.listadoProductos = data.productos;
        this.listadoProductos.forEach((producto) => {
          this.cantidadSeleccionada[producto.id] = 1;
        });
      },
      (error) => {
        console.log(error);
      }
    );
  }

  agregarAlCarrito(producto: any) {
    const total = this.cantidadSeleccionada[producto.id] || 1;
    this._storeService.agregarProducto({ ...producto, total });
  }
}
