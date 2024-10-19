import { Component, OnInit } from '@angular/core';
import { UsuariosService } from 'src/app/services/usuarios.service';

@Component({
  selector: 'app-shipping',
  templateUrl: './shipping.component.html',
  styleUrls: ['./shipping.component.css'],
})
export class ShippingComponent implements OnInit {
  nombresUsuario: string = '';
  idUser: any;
  dataUsuario: any;
  recipientAddress: string = '';
  distrito: string = '';
  piso: string = '';
  phoneNumber: string = '';

  constructor(private _usuarioService: UsuariosService) {}

  ngOnInit(): void {
    this.idUser = localStorage.getItem('userdata');

    this._usuarioService
      .obtenerUsuarioPorId(JSON.parse(this.idUser))
      .subscribe((data) => {
        this.nombresUsuario = `${data.usuario.name} ${data.usuario.lastname}`;
      });
  }

  onSubmit(): void {
    const formData = {
      recipientName: this.nombresUsuario,
      recipientAddress: this.recipientAddress,
      distrito: this.distrito,
      piso: this.piso,
      phoneNumber: this.phoneNumber,
    };

    localStorage.setItem('shippingData', JSON.stringify(formData));
  }
}
