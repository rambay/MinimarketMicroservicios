import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class BoletasService {
  private apiBase = environment.apiUrl;
  private boletas = `${this.apiBase}/boletas`;

  constructor(private http: HttpClient) {}

  listarBoletas(): Observable<any> {
    return this.http.get(this.boletas);
  }
}
