import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private userRole: string | null = null;
  private firstLogin: boolean = false; // Ajout de la variable de première connexion

  constructor() { }

  login(role: string, firstLogin: boolean = false) {
    this.userRole = role;
    this.firstLogin = firstLogin;
    localStorage.setItem('userRole', role);
    localStorage.setItem('firstLogin', JSON.stringify(firstLogin));
  }

  logout() {
    this.userRole = null;
    localStorage.removeItem('userRole');
    localStorage.removeItem('firstLogin');
  }

  getRole(): string | null {
    return localStorage.getItem('userRole');
  }

  isLoggedIn(): boolean {
    return this.userRole !== null;
  }

  isLoggedOut(): boolean {
    return this.userRole === null;
  }

  isAdmin(): boolean {
    return this.userRole === 'admin';
  }

  isAgent(): boolean {
    return this.userRole === 'agent';
  }

  isClient(): boolean {
    return this.userRole === 'client';
  }

  isFirstLogin(): boolean {
    return JSON.parse(localStorage.getItem('firstLogin') || 'false');
  }

  completeFirstLogin() {
    this.firstLogin = false;
    localStorage.setItem('firstLogin', 'false');
  }
}
