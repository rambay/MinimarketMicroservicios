import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class PaymentService {
  private baseUrl = environment.apiUrl;
  private boletas = `${this.baseUrl}/boletas`;

  constructor(private http: HttpClient) {}

  obtenerBoletas(): Observable<any> {
    return this.http.get<any>(this.boletas);
  }

  registrarBoletas(request: any): Observable<any> {
    return this.http.post<any>(this.boletas, request);
  }

  obtenerBoletaPorId(id: number): Observable<any> {
    return this.http.get<any>(`${this.boletas}/${id}`);
  }

  actualizarStock(id: number, request: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/productos/${id}`, request);
  }
}
