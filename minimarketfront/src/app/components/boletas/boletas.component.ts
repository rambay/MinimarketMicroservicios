import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { BoletasService } from 'src/app/services/boletas.service';

@Component({
  selector: 'app-boletas',
  templateUrl: './boletas.component.html',
  styleUrls: ['./boletas.component.css'],
})
export class BoletasComponent implements OnInit {
  listaBoletas: any[] = [];
  listaDetalles: any[] = [];
  formBoleta: FormGroup;

  constructor(private _boletasService: BoletasService) {}

  ngOnInit(): void {
    this.obtenerBoletas();
  }

  obtenerBoletas() {
    this._boletasService.listarBoletas().subscribe(
      (data) => {
        this.listaBoletas = data.boletas;

        this.listaDetalles = data.boletas.flatMap(
          (boleta: any) => boleta.boletaDetalleDTO
        );
      },
      (error) => {
        console.error('Error al obtener las boletas', error);
      }
    );
  }
}
