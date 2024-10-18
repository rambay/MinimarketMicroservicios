import { Component, OnInit } from '@angular/core';
import { TiposproductosService } from 'src/app/services/tiposproductos.service';

@Component({
  selector: 'app-tiposproductos',
  templateUrl: './tiposproductos.component.html',
  styleUrls: ['./tiposproductos.component.css'],
})
export class TiposproductosComponent implements OnInit {
  listaTiposProductos: any[] = [];

  constructor(private _tiposProductosService: TiposproductosService) {}

  ngOnInit(): void {
    this.listarTiposProductos();
  }

  listarTiposProductos() {
    this._tiposProductosService.listarTiposProductos().subscribe((data) => {
      console.log(data);
      if (data.length == 0) {
        console.log('No hay datos');
      }
    });
  }
}
