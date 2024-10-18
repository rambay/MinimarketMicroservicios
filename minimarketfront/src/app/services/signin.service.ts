import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class SigninService {
  private signin = environment.apiLogin;

  private isLoginSubject = new BehaviorSubject<string>('false');
  private myRolSubject = new BehaviorSubject<string>('');

  constructor(private http: HttpClient) {
    const isLoggedIn =
      localStorage.getItem('isLogin') === 'true' ? 'true' : 'false';
    this.isLoginSubject.next(isLoggedIn);
    const storedRol = localStorage.getItem('rol') || 'Usuario';
    this.myRolSubject.next(storedRol);
  }

  isLoggedIn$ = this.isLoginSubject.asObservable();
  myRol$ = this.myRolSubject.asObservable();

  ingresar(request: any): Observable<any> {
    return this.http.post(`${this.signin}`, request).pipe(
      map((response: any) => {
        const token = response.token;
        if (token) {
          console.log('Token encontrado: ', token);
          localStorage.setItem('token', token);
          localStorage.setItem('isLogin', 'true');
          this.isLoginSubject.next('true');

          const user = this.decodeToken(token);
          localStorage.setItem('rol', user.rol);
          this.myRolSubject.next(user.rol);
        } else {
          console.log('No se encontró token en la respuesta.');
        }
        return response;
      })
    );
  }

  private decodeToken(token: string): any {
    const payload = token.split('.')[1];
    const decodedPayload = atob(payload);
    return JSON.parse(decodedPayload);
  }

  token() {
    return localStorage.getItem('token');
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.setItem('isLogin', 'false');
    localStorage.setItem('rol', '');

    this.isLoginSubject.next('false');
    this.myRolSubject.next('');
  }
}
