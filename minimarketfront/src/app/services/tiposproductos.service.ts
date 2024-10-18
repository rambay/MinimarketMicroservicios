import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class TiposproductosService {
  private baseUrl = environment.apiUrl;
  private tiposProductos: string = `${this.baseUrl}/tiposproductos`;

  constructor(private http: HttpClient) {}

  listarTiposProductos(): Observable<any> {
    return this.http.get(this.tiposProductos);
  }
}
