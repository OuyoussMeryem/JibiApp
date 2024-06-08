import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.component.html',
  styleUrls: ['./changepassword.component.css']
})
export class ChangepasswordComponent {
  oldPassword: string = '';
  newPassword: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  changePassword() {
    // Simuler le changement de mot de passe
    // Remplacez cette logique par une requête réelle à votre backend
    console.log('Mot de passe changé');
    this.authService.completeFirstLogin();
    this.router.navigate(['/agent']);
  }
}
