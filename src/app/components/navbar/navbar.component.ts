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

  logout() {
    this.authService.logout();
    this.router.navigate(['/']); 
  }
}
