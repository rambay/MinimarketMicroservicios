import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UsuariosService } from 'src/app/services/usuarios.service';
import Swal from 'sweetalert2';

declare var bootstrap: any;

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css'],
})
export class UsuariosComponent implements OnInit {
  listarUsuarios: any[] = [];
  formUsuario: FormGroup;
  title: any;
  nameBoton: any;
  id: number;

  constructor(private _usuarioService: UsuariosService) {}

  ngOnInit(): void {
    this.obtenerUsuarios();
    this.initForm();
  }

  initForm() {
    this.formUsuario = new FormGroup({
      username: new FormControl(null, [Validators.required]),
      password: new FormControl(null, [Validators.required]),
      email: new FormControl(null, [Validators.required]),
      name: new FormControl(null, [Validators.required]),
      lastname: new FormControl(null, [Validators.required]),
      genero: new FormControl(null, [Validators.required]),
      rol: new FormGroup({
        id: new FormControl(null, [Validators.required]),
      }),
    });
  }

  obtenerUsuarios() {
    this._usuarioService.listarUsuarios().subscribe((data) => {
      this.listarUsuarios = data.usuarios;
      if (this.listarUsuarios.length == 0) {
        console.log('No hay datos');
      }
    });
  }

  guardar(formulario: any): void {
    if (this.formUsuario.valid) {
      this._usuarioService.guardarUsuario(formulario).subscribe(
        (response) => {
          console.log(formulario);
          this.cerrarModal();
          this.obtenerUsuarios();
          this.resetForm();
          console.log('Usuario registrado', response);
        },
        (error) => {
          console.log(formulario);
          console.error('Error al registrar usuario', error);
        }
      );
    }
  }

  editar(id: number, formulario: any): void {
    if (this.formUsuario.valid) {
      this._usuarioService.editarUsuario(id, formulario).subscribe(
        (response) => {
          this.cerrarModal();
          this.obtenerUsuarios();
          this.resetForm();
          console.log('Usuario modificado', response);
        },
        (error) => {
          console.error('Error al modificar usuario', error);
        }
      );
    }
  }

  crearEditarUsuario(boton: any) {
    if (boton == 'Guardar') {
      this.alertRegistro();
    } else {
      this.alertModificar();
    }
  }

  obtenerUsuarioPorId(id: any) {
    let form = this.formUsuario;

    this._usuarioService.obtenerUsuarioPorId(id).subscribe(
      (data) => {
        if (data) {
          console.log(data);

          form.controls['username'].setValue(data.usuario.username || '');
          form.controls['password'].setValue(data.usuario.password || '');
          form.controls['email'].setValue(data.usuario.email || '');
          form.controls['name'].setValue(data.usuario.name || '');
          form.controls['lastname'].setValue(data.usuario.lastname || '');
          form.controls['genero'].setValue(data.usuario.genero || '');
          form.get('rol.id').setValue(data.usuario.rol.id || '');

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
    if (this.formUsuario.valid) {
      Swal.fire({
        title: '¿Estás seguro de modificar el usuario?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Sí, modificar',
        cancelButtonText: 'Cancelar',
      }).then((result) => {
        if (result.isConfirmed) {
          this.editar(this.id, this.formUsuario.value);
          this.alertaExitosa('modificado');
        }
      });
    }
  }

  alertRegistro() {
    if (this.formUsuario.valid) {
      Swal.fire({
        title: '¿Estás seguro de registrar el usuario?',
        icon: 'success',
        showCancelButton: true,
        confirmButtonText: 'Sí, confirmar',
        cancelButtonText: 'Cancelar',
      }).then((result) => {
        if (result.isConfirmed) {
          this.guardar(this.formUsuario.value);
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
    this.title = `${titulo} usuario`;
    titulo == 'Crear'
      ? (this.nameBoton = 'Guardar')
      : (this.nameBoton = 'Modificar');
    if (id != null) {
      this.id = id;
      this.obtenerUsuarioPorId(id);
    }
  }

  cerrarModal() {
    const modalElement = document.getElementById('modalProducto');
    const modal = bootstrap.Modal.getInstance(modalElement);
    modal.hide();
  }

  resetForm(): void {
    this.formUsuario.reset();
  }

  cerrarBoton() {
    this.resetForm();
    this.cerrarModal();
  }

  eliminarUsuarios(id: number): void {
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
        this._usuarioService.eliminarUsuario(id).subscribe(
          (response) => {
            Swal.fire('Eliminado!', response, 'success');
            this.obtenerUsuarios();
          },
          (error) => {
            Swal.fire(
              'Error!',
              `No se pudo eliminar el usuario: ${error.message || error.error}`,
              'error'
            );
          }
        );
      }
    });
  }
}
