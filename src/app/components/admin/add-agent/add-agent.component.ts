import { Component } from '@angular/core';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Agent, AgentService } from '../../services/agent.service';

@Component({
  selector: 'app-add-agent',
  templateUrl: './add-agent.component.html',
  styleUrls: ['./add-agent.component.css']
})
export class AddAgentComponent {
  backoffice!: FormGroup;

  constructor(private fb: FormBuilder, private agentService: AgentService,private router: Router) {
    this.backoffice = this.fb.group({
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
      patentNumber: ['', Validators.required],
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
    if (this.backoffice.valid) {
      const newAgent: Agent = this.backoffice.value;
      this.agentService.addAgent(newAgent);
      alert('Client ajouté avec succès');
    }
  }

  onCancel() {
    this.backoffice.reset();
  }

  goBack() {
    // Implémentez la logique pour revenir à la page précédente
    this.router.navigate(['/']);
  }
}






