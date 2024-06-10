import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-invoice-history',
  templateUrl: './invoice-history.component.html',
  styleUrls: ['./invoice-history.component.css']
})
export class InvoiceHistoryComponent implements OnInit {
  invoices: any[] = [];

  constructor() {}

  ngOnInit(): void {
    // Exemple de données pour les factures
    this.invoices = [
      {
        date: '2024-06-01',
        reference: 'INV-001',
        description: 'Facture de services informatiques',
        creditor: 'Tech Solutions',
        amount: 2000,
        status: 'Payée'
      },
      {
        date: '2024-06-05',
        reference: 'INV-002',
        description: 'Fourniture de bureau',
        creditor: 'Office Supplies Co.',
        amount: 1500,
        status: 'Payée'
      },
      {
        date: '2024-06-10',
        reference: 'INV-003',
        description: 'Consultation financière',
        creditor: 'Finance Experts',
        amount: 3000,
        status: 'Payée'
      },
      {
        date: '2024-06-12',
        reference: 'INV-004',
        description: 'Maintenance informatique',
        creditor: 'IT Services',
        amount: 2500,
        status: 'Payée'
      }
    ];
  }

  payInvoice(invoice: any) {
    // Implémenter la logique de paiement de facture
    console.log('Payer la facture', invoice);
  }

  goBack() {
    // Implémenter la logique pour retourner à la page précédente
    console.log('Retour à la page précédente');
  }
}
