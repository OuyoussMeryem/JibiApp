import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';
  loginForm!: FormGroup;

  constructor(private authService: AuthService, private router: Router, private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
    console.log(this.username);
  }

  login(): void {
    console.log('Tentative de connexion avec les identifiants :', this.username, this.password);
  
    this.authService.login(this.username, this.password).subscribe(response => {
      console.log('Réponse de l\'API de connexion :', response);
      localStorage.setItem('token', response.token);

      // Ajoutez les lignes suivantes pour extraire le rôle de l'utilisateur du token JWT
      const tokenPayload = JSON.parse(atob(response.token.split('.')[1]));
      const userRole = response.role;
      
  
      if (response.responseType === 'redirect') {
        console.log('Redirection vers la page de changement de mot de passe');
        this.router.navigate(['/change-password']);
      } else {
        console.log('Rôle de l\'utilisateur :', userRole);
  
        if (userRole === 'ADMIN') {
          console.log('Redirection vers la page d\'administration');
          this.router.navigate(['/admin']);
        } else if (userRole === 'CLIENT') {
          console.log('Redirection vers la page client');
          this.router.navigate(['/app-changepasswordclient']);
        } else if (userRole === 'AGENT') {
          console.log('Redirection vers la page agent');
          this.router.navigate(['/change-password']);
        }
      }
    }, error => {
      console.error('Erreur lors de la connexion :', error);
    });
  }
}
