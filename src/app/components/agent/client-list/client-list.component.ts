import { Component } from '@angular/core';
import { Client, ClientService } from '../../services/client.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent {
  clients: Client[] = [];
  displayedColumns: string[] = ['lastName', 'firstName', 'idType', 'idNumber', 'birthDate', 'address', 'email', 'phone', 'registrationNumber','actions'];

  constructor(private clientService: ClientService,private router: Router) {}

  ngOnInit(): void {
    this.clientService.getClients().subscribe((clients) => {
      this.clients = clients;
    });
  }

  editClient(client: Client) {
    // Implémentez la logique pour modifier le client ici
    alert('Edit client functionality will be implemented here.');
  }

  deleteClient(client: Client) {
    // Implémentez la logique pour supprimer le client ici
    const index = this.clients.indexOf(client);
    if (index !== -1) {
      this.clients.splice(index, 1);
      // Vous pouvez également appeler une méthode de service pour supprimer le client du backend
      // this.clientService.deleteClient(client);
    }
  }


  goBack() {
    // Implémentez la logique pour revenir à la page précédente
    this.router.navigate(['/']);
  }
}
