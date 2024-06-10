import { Component } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent {
  agencies = [
    { name: 'IAM RECHARGES', imageUrl: 'assets/images/IAM-image.jpg' },
    { name: 'REDAL', imageUrl: 'assets/images/REDAL.jpeg' },
    { name: 'AMANDIS TANGER', imageUrl: 'assets/images/AMANDIS.png' },
    { name: 'IAM RECHARGES', imageUrl: 'assets/images/IAM-image.jpg' },
    { name: 'AMANDIS TANGER', imageUrl: 'assets/images/AMANDIS.png' },
    { name: 'REDAL', imageUrl: 'assets/images/REDAL.jpeg' }
    // Ajoutez d'autres agences ici
  ];
}
