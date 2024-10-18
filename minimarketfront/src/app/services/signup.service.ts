import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class SignupService {
  private baseUrl = environment.apiUrl;
  private usuarios: string = `${this.baseUrl}/usuarios`;

  constructor(private http: HttpClient) {}

  guardarUsuario(request: any): Observable<any> {
    return this.http.post(this.usuarios, request);
  }
}
