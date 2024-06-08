import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Client, ClientService } from '../../services/client.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css']
})
export class AddClientComponent {
  agentoffice!: FormGroup;

  constructor(private fb: FormBuilder, private clientService: ClientService,private router: Router) {
    this.agentoffice = this.fb.group({
      lastName: ['', Validators.required],
      firstName: ['', Validators.required],
      idType: ['', Validators.required],
      idNumber: ['', Validators.required],
      birthDate: ['', Validators.required],
      address: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      confirmEmail: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required],
      registrationNumber: ['', Validators.required],
      
      fileDescription: ['']
    }, { validators: this.emailsMatchValidator });
  }

  emailsMatchValidator(group: FormGroup) {
    const email = group.get('email')?.value;
    const confirmEmail = group.get('confirmEmail')?.value;
    return email === confirmEmail ? null : { emailMismatch: true };
  }

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length) {
      const file = input.files[0];
      console.log('File selected:', file);
    }
  }

  addFile() {
    console.log('Add file clicked');
  }

  onSubmit() {
    if (this.agentoffice.valid) {
      const newClient: Client = this.agentoffice.value;
      this.clientService.addClient(newClient);
      alert('Client ajouté avec succès');
    }
  }

  onCancel() {
    this.agentoffice.reset();
  }

  goBack() {
    // Implémentez la logique pour revenir à la page précédente
    this.router.navigate(['/']);
  }
}
