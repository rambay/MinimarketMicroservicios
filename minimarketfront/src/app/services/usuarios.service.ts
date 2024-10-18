import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UsuariosService {
  private baseUrl = environment.apiUrl;
  private usuarios = `${this.baseUrl}/usuarios`;

  constructor(private http: HttpClient) {}

  private obtenerHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json',
    });
  }

  listarUsuarios(): Observable<any> {
    return this.http.get<any>(this.usuarios, {
      headers: this.obtenerHeaders(),
    });
  }
  obtenerUsuarioPorId(id: number): Observable<any> {
    return this.http.get<any>(`${this.usuarios}/${id}`, {
      headers: this.obtenerHeaders(),
    });
  }

  guardarUsuario(data: any): Observable<any> {
    return this.http.post<any>(this.usuarios, data, {
      headers: this.obtenerHeaders(),
    });
  }

  editarUsuario(id: number, data: any): Observable<any> {
    return this.http.put<any>(`${this.usuarios}/${id}`, data, {
      headers: this.obtenerHeaders(),
    });
  }

  eliminarUsuario(id: number): Observable<any> {
    return this.http.delete<any>(`${this.usuarios}/${id}`, {
      headers: this.obtenerHeaders(),
    });
  }
}
