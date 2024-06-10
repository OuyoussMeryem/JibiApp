import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-facturesnonpaye',
  templateUrl: './facturesnonpaye.component.html',
  styleUrls: ['./facturesnonpaye.component.css']
})
export class FacturesnonpayeComponent implements OnInit {
  invoices: any[] = [];

  constructor(private router: Router) { }

  ngOnInit(): void {
    // Exemple de données pour les factures
    this.invoices = [
      {
        date: '2024-06-01',
        reference: 'INV-001',
        description: 'Facture de services informatiques',
        creditor: 'Tech Solutions',
        amount: 2000,
        status: 'En attente'
      },
      {
        date: '2024-06-05',
        reference: 'INV-002',
        description: 'Fourniture de bureau',
        creditor: 'Office Supplies Co.',
        amount: 1500,
        status: 'En attente'
      },
      {
        date: '2024-06-10',
        reference: 'INV-003',
        description: 'Consultation financière',
        creditor: 'Finance Experts',
        amount: 3000,
        status: 'En attente'
      },
      {
        date: '2024-06-12',
        reference: 'INV-004',
        description: 'Maintenance informatique',
        creditor: 'IT Services',
        amount: 2500,
        status: 'En attente'
      }
    ];
  }

  payee(){
this.router.navigate(['/paye-form'])
  }

  goBack() {
    this.router.navigate(['/crianciers'])
    // Implémenter la logique pour retourner à la page précédente
    console.log('Retour à la page précédente');
  }

}
