import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProveedoresService {
  private baseUrl = environment.apiUrl;
  private proveedores: string = `${this.baseUrl}/proveedores`;

  constructor(private http: HttpClient) {}

  listarProveedores(): Observable<any> {
    return this.http.get(this.proveedores);
  }

  obtenerProveedorPorId(id: number): Observable<any> {
    return this.http.get(`${this.proveedores}/${id}`);
  }

  guardarProveedor(request: any): Observable<any> {
    return this.http.post(this.proveedores, request);
  }

  editarProveedor(id: number, request: any): Observable<any> {
    return this.http.put(`${this.proveedores}/${id}`, request);
  }

  eliminarProveedor(id: number): Observable<string> {
    return this.http.delete(`${this.proveedores}/${id}`, {
      responseType: 'text',
    });
  }
}
