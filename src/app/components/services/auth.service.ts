import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8089/api/auth';
  private userRole: string | null = null;
  private firstLogin: boolean = false;

  constructor(private http: HttpClient) {}


  
  login(username: string, password: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, { username, password }).pipe(
      tap((response: any) => { // Utilisation d'une fonction fléchée pour maintenir le contexte de this
        this.userRole = response.role;
        localStorage.setItem('userRole', this.userRole as string);
        localStorage.setItem('authToken', response.token);
      })
    );
  }
  

  logout(): Observable<any> {
    return this.http.get(`${this.apiUrl}/logout`).pipe(
      tap(() => {
        // Supprimer le rôle utilisateur du localStorage
        localStorage.removeItem('userRole');
        // Mettre à jour la variable userRole
        this.userRole = null;
      })
    );
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
    return this.userRole === 'ADMIN';
  }

  isAgent(): boolean {
    return this.userRole === 'AGENT';
  }

  isClient(): boolean {
    return this.userRole === 'CLIENT';
  }

  isFirstLogin(): boolean {
    return JSON.parse(localStorage.getItem('firstLogin') || 'false');
  }

  completeFirstLogin() {
    this.firstLogin = false;
    localStorage.setItem('firstLogin', 'false');
  }
}
