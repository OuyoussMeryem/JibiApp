import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PaymentService } from '../../services/payment.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-payment-account',
  templateUrl: './create-payment-account.component.html',
  styleUrls: ['./create-payment-account.component.css']
})
export class CreatePaymentAccountComponent {
  createAccountForm: FormGroup;

  constructor(private fb: FormBuilder, private paymentService: PaymentService, private router: Router) {
    this.createAccountForm = this.fb.group({
      accountType: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required]
    });
  }

  onSubmit() {
    this.router.navigate(['/client']);
    //if (this.createAccountForm.valid) {


    //   this.paymentService.createPaymentAccount(this.createAccountForm.value).subscribe(response => {
    //     this.router.navigate(['/client']);
    //   }, error => {
    //     console.error('Erreur lors de la création du compte de paiement', error);
    //   });
    // }
  }

  onCancel(){
    this.router.navigate(['/']);
  }
}
