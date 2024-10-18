import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthServiceService {
  private isLoginSubject = new BehaviorSubject<string>('false');

  constructor() {
    const isLoggedIn =
      localStorage.getItem('isLogin') === 'true' ? 'true' : 'false';
    this.isLoginSubject.next(isLoggedIn);
  }

  isLoggedIn$ = this.isLoginSubject.asObservable();

  login() {
    localStorage.setItem('isLogin', 'true');
    this.isLoginSubject.next('true');
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.setItem('isLogin', 'false');
    this.isLoginSubject.next('false');
  }
}
