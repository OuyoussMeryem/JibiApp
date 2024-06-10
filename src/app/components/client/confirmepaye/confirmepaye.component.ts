import { Component } from '@angular/core';

@Component({
  selector: 'app-confirmepaye',
  templateUrl: './confirmepaye.component.html',
  styleUrls: ['./confirmepaye.component.css']
})
export class ConfirmepayeComponent {
  confirmPayment() {
    // Confirmer le paiement (ici, afficher une alerte)
    alert('Paiement confirmé!');
  }

  cancelPayment() {
    // Annuler le paiement et réinitialiser les champs du formulaire
    // this.firstName = '';
    // this.lastName = '';
    // this.accountNumber = '';
    // this.amount = 0;
    // this.showConfirmation = false;
  }
}
