import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ProductosService } from 'src/app/services/productos.service';
import { ProveedoresService } from 'src/app/services/proveedores.service';
import { TiposproductosService } from 'src/app/services/tiposproductos.service';
import Swal from 'sweetalert2';

declare var bootstrap: any;

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css'],
})
export class ProductosComponent implements OnInit {
  listaProductos: any[] = [];
  listaTiposProductos: any[] = [];
  listaProveedores: any[] = [];
  formProducto: FormGroup;
  title: any;
  nameBoton: any;
  id: number;

  constructor(
    private _productosService: ProductosService,
    private _tiposProductosService: TiposproductosService,
    private _proveedores: ProveedoresService
  ) {}

  ngOnInit(): void {
    this.obtenerProductos();
    this.listarTiposProductos();
    this.listarProveedores();
    this.initForm();
  }

  initForm() {
    this.formProducto = new FormGroup({
      cantidad: new FormControl(null, [Validators.required]),
      fechaVen: new FormControl(null, [Validators.required]),
      image: new FormControl(null, [Validators.required]),
      marca: new FormControl(null, [Validators.required]),
      nombre: new FormControl(null, [Validators.required]),
      precio: new FormControl(null, [Validators.required]),
      tipoProd: new FormGroup({
        id: new FormControl(null, [Validators.required]),
      }),
      idProveedor: new FormControl(null, [Validators.required]),
    });
  }

  obtenerProductos() {
    this._productosService.listarProductos().subscribe((data) => {
      this.listaProductos = data.productos;
      if (this.listaProductos.length == 0) {
        console.log('No hay datos');
      }
    });
  }

  guardar(formulario: any): void {
    if (this.formProducto.valid) {
      this._productosService.guardarProducto(formulario).subscribe(
        (response) => {
          console.log(formulario);
          this.cerrarModal();
          this.obtenerProductos();
          this.resetForm();
          console.log('Producto registrado', response);
        },
        (error) => {
          console.log(formulario);
          console.error('Error al registrar producto', error);
        }
      );
    }
  }

  editar(id: number, formulario: any): void {
    if (this.formProducto.valid) {
      this._productosService.editarProducto(id, formulario).subscribe(
        (response) => {
          this.cerrarModal();
          this.obtenerProductos();
          this.resetForm();
          console.log('Producto modificado', response);
        },
        (error) => {
          console.error('Error al modificar producto', error);
        }
      );
    }
  }

  crearEditarProducto(boton: any) {
    if (boton == 'Guardar') {
      this.alertRegistro();
    } else {
      this.alertModificar();
    }
  }

  obtenerProductoPorId(id: any) {
    let form = this.formProducto;

    this._productosService.obtenerProducto(id).subscribe(
      (data) => {
        if (data) {
          console.log(data);

          form.controls['cantidad'].setValue(data.producto.cantidad || '');
          const fechaVenc = new Date(data.producto.fechaVen);
          const fechaISO = fechaVenc.toISOString().split('T')[0];
          this.formProducto.controls['fechaVen'].setValue(fechaISO);

          form.controls['image'].setValue(data.producto.image || '');
          form.controls['marca'].setValue(data.producto.marca || '');
          form.controls['nombre'].setValue(data.producto.nombre || '');
          form.controls['precio'].setValue(data.producto.precio || '');
          form.controls['idProveedor'].setValue(
            data.producto.idProveedor || ''
          );
          form.get('tipoProd.id').setValue(data.producto.tipoProd.id || '');

          console.log('Formulario a editar:', form);
        } else {
          console.error('El producto no se encontró o los datos son nulos');
        }
      },
      (error) => {
        console.error('Error al obtener el producto', error);
      }
    );
  }

  alertModificar() {
    if (this.formProducto.valid) {
      Swal.fire({
        title: '¿Estás seguro de modificar el producto?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Sí, modificar',
        cancelButtonText: 'Cancelar',
      }).then((result) => {
        if (result.isConfirmed) {
          this.editar(this.id, this.formProducto.value);
          this.alertaExitosa('modificado');
        }
      });
    }
  }

  alertRegistro() {
    if (this.formProducto.valid) {
      Swal.fire({
        title: '¿Estás seguro de registrar el producto?',
        icon: 'success',
        showCancelButton: true,
        confirmButtonText: 'Sí, confirmar',
        cancelButtonText: 'Cancelar',
      }).then((result) => {
        if (result.isConfirmed) {
          this.guardar(this.formProducto.value);
          this.alertaExitosa('registrado');
        }
      });
    }
  }

  alertaExitosa(titulo: any) {
    Swal.fire({
      position: 'center',
      icon: 'success',
      title: 'Producto ' + titulo,
      showConfirmButton: false,
      timer: 1500,
    });
  }

  titulo(titulo: any, id: number) {
    this.title = `${titulo} producto`;
    titulo == 'Crear'
      ? (this.nameBoton = 'Guardar')
      : (this.nameBoton = 'Modificar');
    if (id != null) {
      this.id = id;
      this.obtenerProductoPorId(id);
    }
  }

  cerrarModal() {
    const modalElement = document.getElementById('modalProducto');
    const modal = bootstrap.Modal.getInstance(modalElement);
    modal.hide();
  }

  resetForm(): void {
    this.formProducto.reset();
  }

  cerrarBoton() {
    this.resetForm();
    this.cerrarModal();
  }

  eliminarProductos(id: number): void {
    Swal.fire({
      title: '¿Estás seguro?',
      text: '¡No podrás revertir esto!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminarlo',
      cancelButtonText: 'Cancelar',
    }).then((result) => {
      if (result.isConfirmed) {
        this._productosService.eliminarProducto(id).subscribe(
          (response) => {
            Swal.fire('Eliminado!', response, 'success');
            this.obtenerProductos();
          },
          (error) => {
            Swal.fire(
              'Error!',
              `No se pudo eliminar el producto: ${
                error.message || error.error
              }`,
              'error'
            );
          }
        );
      }
    });
  }

  listarTiposProductos(): void {
    this._tiposProductosService.listarTiposProductos().subscribe(
      (data) => {
        this.listaTiposProductos = data.tipoproducto;
      },
      (error) => {
        console.error('Error al cargar tipos de productos', error);
      }
    );
  }

  listarProveedores(): void {
    this._proveedores.listarProveedores().subscribe(
      (data) => {
        this.listaProveedores = data.proveedores;
      },
      (error) => {
        console.error('Error al cargar proveedores', error);
      }
    );
  }
}
