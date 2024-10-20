import { Component, OnInit } from '@angular/core';
import { PaymentService } from 'src/app/services/payment.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { ProductosService } from 'src/app/services/productos.service';
import { StoreService } from 'src/app/services/store.service';

interface BoletaDetalleCreateDTO {
  cantidad: number;
  idproducto: number;
  precioTotal: number;
}

interface ModeloBoleta {
  clienteId: number;
  fechaEmision: string;
  boletaDetalleCreateDTO: BoletaDetalleCreateDTO[];
}

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css'],
})
export class PaymentComponent implements OnInit {
  selectedPaymentMethod: string = '';
  boletas: any[] = [];
  usuarioId: number = 1;

  modeloBoleta: ModeloBoleta = {
    clienteId: this.usuarioId,
    fechaEmision: '',
    boletaDetalleCreateDTO: [],
  };

  constructor(
    private _paymentService: PaymentService,
    private router: Router,
    private _productsService: ProductosService,
    private _storeService: StoreService
  ) {}

  ngOnInit(): void {
    this.cargarUsuarioId();
    this.establecerFechaEmision();
    this.cargarBoletas();
  }

  cargarUsuarioId() {
    const userdata = localStorage.getItem('userdata');
    if (userdata) {
      this.usuarioId = Number(userdata);
    }
  }

  establecerFechaEmision() {
    const today = new Date();
    this.modeloBoleta.fechaEmision = today.toISOString();
  }

  async onSubmit() {
    const carrito = localStorage.getItem('carrito');
    if (carrito) {
      const productos = JSON.parse(carrito);
      this.modeloBoleta.boletaDetalleCreateDTO = productos.map(
        (producto: any) => ({
          cantidad: producto.total,
          idproducto: producto.idProducto,
          precioTotal: producto.precio * producto.total,
        })
      );
    }

    if (
      !this.modeloBoleta.boletaDetalleCreateDTO ||
      this.modeloBoleta.boletaDetalleCreateDTO.length === 0
    ) {
      console.error(
        'La lista de detalles de la boleta está vacía o no inicializada'
      );
      return;
    }

    console.log('Modelo de Boleta:', this.modeloBoleta);

    this._paymentService.registrarBoletas(this.modeloBoleta).subscribe(
      async (res) => {
        console.log('Boleta registrada:', res);
        await this.actualizarStock();

        Swal.fire({
          title: '¡Compra Realizada!',
          text: 'Tu boleta ha sido registrada exitosamente.',
          icon: 'success',
          confirmButtonText: 'Aceptar',
        }).then(() => {
          this.router.navigate(['/']);
          localStorage.removeItem('carrito');
        });
      },
      (err) => {
        console.error('Error al registrar la boleta:', err);
        Swal.fire({
          title: 'Error',
          text: 'Hubo un error al registrar la boleta. Intente de nuevo.',
          icon: 'error',
          confirmButtonText: 'Aceptar',
        });
      }
    );

    this._storeService.vaciarCarrito();
  }

  actualizarStock() {
    const detalles = this.modeloBoleta.boletaDetalleCreateDTO;

    detalles.forEach(async (detalle) => {
      try {
        const response = await this._productsService
          .obtenerProducto(detalle.idproducto)
          .toPromise();

        const producto = response.producto;

        if (!producto || !producto.idProducto) {
          throw new Error(
            `Producto no encontrado o ID indefinido para ID: ${detalle.idproducto}`
          );
        }

        if (producto.cantidad >= detalle.cantidad) {
          producto.cantidad -= detalle.cantidad;

          await this._paymentService
            .actualizarStock(producto.idProducto, producto)
            .toPromise();
          console.log(
            `Stock actualizado para producto ID ${producto.idProducto}: ${producto.cantidad}`
          );
        } else {
          throw new Error(
            `No hay suficiente stock para el producto ID: ${producto.idProducto}`
          );
        }
      } catch (err) {
        console.error('Error en la actualización de stocks:', err);
        Swal.fire({
          title: 'Error',
          text: err.message,
          icon: 'error',
          confirmButtonText: 'Aceptar',
        });
      }
    });

    localStorage.removeItem('carrito');
  }

  cargarBoletas() {
    this._paymentService.obtenerBoletas().subscribe(
      (res) => {
        this.boletas = res;
        console.log(this.boletas);
      },
      (err) => {
        console.error('Error al cargar las boletas:', err);
      }
    );
  }
}
