import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-changepasswordclient',
  templateUrl: './changepasswordclient.component.html',
  styleUrls: ['./changepasswordclient.component.css']
})
export class ChangepasswordclientComponent {
  oldPassword: string = '';
  newPassword: string = '';

  constructor(private authService: AuthService, private router: Router) {}
  changePassword() {
    // Simuler le changement de mot de passe
    // Remplacez cette logique par une requête réelle à votre backend
    console.log('Mot de passe changé');
    this.authService.completeFirstLogin();
    this.router.navigate(['/app-create-payment-account']);
  }
}
