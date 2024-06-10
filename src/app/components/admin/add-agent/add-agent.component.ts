import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AgentService } from '../../services/agent.service';

@Component({
  selector: 'app-add-agent',
  templateUrl: './add-agent.component.html',
  styleUrls: ['./add-agent.component.css']
})
export class AddAgentComponent {
  backoffice!: FormGroup;
  selectedFiles: { [key: string]: File } = {};

  constructor(private fb: FormBuilder, private agentService: AgentService, private router: Router) {
    this.backoffice = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      numPieceIdentite: ['', Validators.required],
      dateNaissance: ['', Validators.required],
      adresse: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      confirmEmail: ['', [Validators.required, Validators.email]],
      telephone: ['', Validators.required],
      numPattente: ['', Validators.required],
      pieceIdentiteFaceOne: ['', Validators.required],
      pieceIdentiteFaceTwo: ['', Validators.required]
    }, { validators: this.emailsMatchValidator });
  }

  emailsMatchValidator(group: FormGroup) {
    const email = group.get('email')?.value;
    const confirmEmail = group.get('confirmEmail')?.value;
    return email === confirmEmail ? null : { emailMismatch: true };
  }

  onFileSelected(event: Event, field: string) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length) {
      this.selectedFiles[field] = input.files[0];
    }
  }

  onSubmit() {
    if (this.backoffice.valid) {
      const formData = new FormData();
      Object.keys(this.backoffice.controls).forEach(key => {
        if (key !== 'pieceIdentiteFaceOne' && key !== 'pieceIdentiteFaceTwo') {
          formData.append(key, this.backoffice.get(key)?.value);
        }
      });
      formData.append('pieceIdentiteFaceOne', this.selectedFiles['pieceIdentiteFaceOne']);
      formData.append('pieceIdentiteFaceTwo', this.selectedFiles['pieceIdentiteFaceTwo']);

      this.agentService.addAgent(formData).subscribe(response => {
        alert('Agent ajouté avec succès');
        this.router.navigate(['/agents']);
      }, error => {
        console.error('Erreur lors de l\'ajout de l\'agent', error);
        alert('Une erreur est survenue lors de l\'ajout de l\'agent');
      });
    }
  }

  onCancel() {
    this.backoffice.reset();
  }

  goBack() {
    this.router.navigate(['/']);
  }
}
