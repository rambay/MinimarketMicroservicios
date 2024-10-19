import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SigninService } from 'src/app/services/signin.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css'],
})
export class SigninComponent implements OnInit {
  usuario: any[] = [];
  formLogin: FormGroup;

  constructor(private _signinService: SigninService, private router: Router) {}

  ngOnInit(): void {
    this.initForm();
  }

  initForm() {
    this.formLogin = new FormGroup({
      email: new FormControl(null, [Validators.required]),
      password: new FormControl(null, [Validators.required]),
    });
  }

  login() {
    if (this.formLogin.valid) {
      this._signinService.ingresar(this.formLogin.value).subscribe({
        next: (res) => {
          this.router.navigate(['/']);
        },
        error: (err: HttpErrorResponse) => {
          this.alertaError();
        },
      });
    }
  }

  alertaError() {
    Swal.fire({
      position: 'top-end',
      icon: 'error',
      title: 'Correo o contraseña incorrecta',
      showConfirmButton: false,
      timer: 1500,
    });
    this.formLogin.reset();
  }
}
