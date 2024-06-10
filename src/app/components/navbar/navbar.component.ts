import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  constructor(private authService: AuthService, private router: Router) {}

  isLoggedIn(): boolean {
    console.log('bbbbbbbbbbbb',this.authService.isLoggedIn());
    return this.authService.isLoggedIn();
  }

  isLoggedOut(): boolean {
    return this.authService.isLoggedOut();
  }
  

  isAdmin(): boolean {
    return this.authService.isAdmin();
  }

  isAgent(): boolean {
    return this.authService.isAgent();
  }

  isClient(): boolean {
    return this.authService.isClient();
  }
  
  // autres méthodes du composant

  logout() {
    this.authService.logout().subscribe(
      response => {
        
        // Supprimez les informations d'authentification stockées
        localStorage.removeItem('token');
        localStorage.removeItem('userRole');
  
        // Affichez une alerte pour informer l'utilisateur de la déconnexion réussie
        alert('Déconnexion réussie');
        
  
        // Redirigez l'utilisateur vers une page appropriée, comme la page d'accueil
        this.router.navigate(['/']);
      },
      error => {
        // En cas d'erreur, affichez un message d'erreur dans une alerte
        alert('Erreur lors de la déconnexion : ' + error);
  
        // Redirigez l'utilisateur vers une page appropriée, comme la page d'accueil
        this.router.navigate(['/']);
      }
    );
  }
  
  
}
