import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ProveedoresService } from 'src/app/services/proveedores.service';
import Swal from 'sweetalert2';

declare var bootstrap: any;

@Component({
  selector: 'app-proveedores',
  templateUrl: './proveedores.component.html',
  styleUrls: ['./proveedores.component.css'],
})
export class ProveedoresComponent implements OnInit {
  listaProveedores: any[] = [];
  formProveedor: FormGroup;
  title: any;
  nameBoton: any;
  id: number;

  constructor(private _proveedorService: ProveedoresService) {}

  ngOnInit(): void {
    this.obtenerProveedores();
    this.initForm();
  }

  initForm() {
    this.formProveedor = new FormGroup({
      nombre: new FormControl(null, [Validators.required]),
      ruc: new FormControl(null, [Validators.required]),
      activo: new FormControl(null, [Validators.required]),
    });
  }

  obtenerProveedores() {
    this._proveedorService.listarProveedores().subscribe((data) => {
      this.listaProveedores = data.proveedores;
      if (this.listaProveedores.length == 0) {
        console.log('No hay datos');
      }
    });
  }

  guardar(formulario: any): void {
    if (this.formProveedor.valid) {
      this._proveedorService.guardarProveedor(formulario).subscribe(
        (response) => {
          this.cerrarModal();
          this.obtenerProveedores();
          this.resetForm();
          console.log('Proveedor registrado', response);
        },
        (error) => {
          console.log(formulario);
          console.error('Error al registrar proveedor', error);
        }
      );
    }
  }

  editar(id: number, formulario: any): void {
    if (this.formProveedor.valid) {
      this._proveedorService.editarProveedor(id, formulario).subscribe(
        (response) => {
          this.cerrarModal();
          this.obtenerProveedores();
          this.resetForm();
          console.log('Proveedor modificado', response);
        },
        (error) => {
          console.error('Error al modificar proveedor', error);
        }
      );
    }
  }

  crearEditarProveedor(boton: any) {
    if (boton == 'Guardar') {
      this.alertRegistro();
    } else {
      this.alertModificar();
    }
  }

  obtenerProveedorPorId(id: any) {
    let form = this.formProveedor;

    this._proveedorService.obtenerProveedorPorId(id).subscribe(
      (data) => {
        if (data) {
          form.controls['nombre'].setValue(data.proveedor.nombre || '');
          form.controls['ruc'].setValue(data.proveedor.ruc || '');
          form.controls['activo'].setValue(data.proveedor.activo || '');
        } else {
          console.error('El proveedor no se encontró o los datos son nulos');
        }
      },
      (error) => {
        console.error('Error al obtener el proveedor', error);
      }
    );
  }

  alertModificar() {
    if (this.formProveedor.valid) {
      Swal.fire({
        title: '¿Estás seguro de modificar el proveedor?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Sí, modificar',
        cancelButtonText: 'Cancelar',
      }).then((result) => {
        if (result.isConfirmed) {
          this.editar(this.id, this.formProveedor.value);
          this.alertaExitosa('modificado');
        }
      });
    }
  }

  alertRegistro() {
    if (this.formProveedor.valid) {
      Swal.fire({
        title: '¿Estás seguro de registrar el proveedor?',
        icon: 'success',
        showCancelButton: true,
        confirmButtonText: 'Sí, confirmar',
        cancelButtonText: 'Cancelar',
      }).then((result) => {
        if (result.isConfirmed) {
          this.guardar(this.formProveedor.value);
          this.alertaExitosa('registrado');
        }
      });
    }
  }

  alertaExitosa(titulo: any) {
    Swal.fire({
      position: 'center',
      icon: 'success',
      title: 'Proveedor ' + titulo,
      showConfirmButton: false,
      timer: 1500,
    });
  }

  titulo(titulo: any, id: number) {
    this.title = `${titulo} proveedor`;
    titulo == 'Crear'
      ? (this.nameBoton = 'Guardar')
      : (this.nameBoton = 'Modificar');
    if (id != null) {
      this.id = id;
      this.obtenerProveedorPorId(id);
    }
  }

  cerrarModal() {
    const modalElement = document.getElementById('modalProducto');
    const modal = bootstrap.Modal.getInstance(modalElement);
    modal.hide();
  }

  resetForm(): void {
    this.formProveedor.reset();
  }

  cerrarBoton() {
    this.resetForm();
    this.cerrarModal();
  }

  eliminarProveedores(id: number): void {
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
        this._proveedorService.eliminarProveedor(id).subscribe(
          (response) => {
            Swal.fire('Eliminado!', response, 'success');
            this.obtenerProveedores();
          },
          (error) => {
            Swal.fire(
              'Error!',
              `No se pudo eliminar el proveedor: ${
                error.message || error.error
              }`,
              'error'
            );
          }
        );
      }
    });
  }
}
