import { Component } from '@angular/core';
import { Agent, AgentService } from '../../services/agent.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-agent-list',
  templateUrl: './agent-list.component.html',
  styleUrls: ['./agent-list.component.css']
})
export class AgentListComponent {

  agents: Agent[] = [];
  displayedColumns: string[] = ['lastName', 'firstName', 'idType', 'idNumber', 'birthDate', 'address', 'email', 'phone', 'registrationNumber', 'patentNumber', 'actions'];

  constructor(private agentService: AgentService,private router: Router) {}

  ngOnInit(): void {
    this.agentService.getAgents().subscribe((agents) => {
      this.agents = agents;
    });
  }

  editAgent(agents: Agent) {
    // Implémentez la logique pour modifier le client ici
    alert('Edit client functionality will be implemented here.');
  }

  deleteAgent(agent: Agent) {
    // Implémentez la logique pour supprimer le client ici
    const index = this.agents.indexOf(agent);
    if (index !== -1) {
      this.agents.splice(index, 1);
      // Vous pouvez également appeler une méthode de service pour supprimer le client du backend
      // this.clientService.deleteClient(client);
    }
  }


  goBack() {
    // Implémentez la logique pour revenir à la page précédente
    this.router.navigate(['/']);
  }

}









