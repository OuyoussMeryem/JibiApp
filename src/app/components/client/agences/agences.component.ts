import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-agences',
  templateUrl: './agences.component.html',
  styleUrls: ['./agences.component.css']
})
export class AgencesComponent implements OnInit {
  creditors: any[] = [];

  constructor(private router: Router) {}

  ngOnInit(): void {
    // Exemple de données pour les créanciers
    this.creditors = [
      {
        image: 'assets/images/AMANDIS.png',
        name: 'AMANDIS',
        services: [
          { id: 1, name: 'FACTURES AMANDIS' },
          { id: 2, name: 'FACTURES EAU' }
        ]
      },
      {
        image: 'assets/images/IAM-image.jpg',
        name: 'IAM RECHARGES',
        services: [
          { id: 3, name: 'Téléphone SIM' },
          { id: 4, name: 'Internet' }
        ]
      },
      {
        image: 'assets/images/LYDEC.jpg',
        name: 'LYDEC',
        services: [
          { id: 5, name: 'FACTURES LYDEC' },
          { id: 6, name: 'FACTURES EAU' }
        ]
      },{
        image: 'assets/images/REDAL.jpeg',
        name: 'REDAL',
        services: [
          { id: 7, name: 'FACTURES REDAL' },
          { id: 8, name: 'FACTURES EAU' }
        ]
      },{
        image: 'assets/images/AMANDIS.png',
        name: 'AMANDIS',
        services: [
          { id: 9, name: 'FACTURES AMANDIS' },
          { id: 10, name: 'FACTURES EAU' }
        ]
      },{
        image: 'assets/images/IAM-image.jpg',
        name: 'IAM FACTURES',
        services: [
          { id: 11, name: 'PRODUIT FIX SIM' },
          { id: 12, name: 'PRODUIT INTERNET SIM' }
        ]
      }

    ];
  }

  goservice(){
this.router.navigate(['/facturesnonpaye']);
  }

}
