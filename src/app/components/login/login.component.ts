import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    console.log('Username:', this.username);
    console.log('Password:', this.password);

    // Simuler une réponse de backend
    let response: { role: string, firstLogin?: boolean } | null = null;

    if (this.username === 'admin' && this.password === 'admin123') {
      response = { role: 'admin' };
    } else if (this.username === 'agent' && this.password === 'agent123') {
      response = { role: 'agent', firstLogin: true }; // Exemple avec firstLogin
    } else if (this.username === 'client' && this.password === 'client123') {
      response = { role: 'client' };
    } else {
      alert('Nom d\'utilisateur ou mot de passe incorrect');
      return;
    }

    this.authService.login(response.role, response.firstLogin || false);

    if (this.authService.isAgent() && this.authService.isFirstLogin()) {
      this.router.navigate(['/change-password']);
    } else if (this.authService.isAdmin()) {
      this.router.navigate(['/admin']);
    } else if (this.authService.isAgent()) {
      this.router.navigate(['/agent']);
    } else if (this.authService.isClient()) {
      this.router.navigate(['/client']);
    } else {
      alert('Rôle inconnu');
    }
  }
}
