import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SignupService } from 'src/app/services/signup.service';
import { Router } from '@angular/router'; // Importa Router
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  constructor(private _usuarioService: SignupService, private router: Router) {}

  ngOnInit(): void {}

  registrarUsuario(form: NgForm): void {
    if (form.valid) {
      const usuario = {
        username: form.value.username,
        password: form.value.password,
        email: form.value.email,
        name: form.value.name,
        lastname: form.value.lastname,
        genero: form.value.genero,
        roles: [
          {
            id: 2,
          },
        ],
      };

      this._usuarioService.guardarUsuario(usuario).subscribe(
        (response) => {
          console.log('Usuario registrado con éxito', response);

          Swal.fire({
            title: 'Éxito!',
            text: 'Usuario registrado con éxito.',
            icon: 'success',
            confirmButtonText: 'Aceptar',
          }).then(() => {
            this.router.navigate(['/login']);
          });

          form.reset();
        },
        (error) => {
          console.error('Error al registrar usuario', error);
          Swal.fire({
            title: 'Error!',
            text: 'Hubo un problema al registrar el usuario.',
            icon: 'error',
            confirmButtonText: 'Aceptar',
          });
        }
      );
    } else {
      console.log('El formulario no es válido');
    }
  }
}
