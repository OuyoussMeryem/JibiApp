import { Component, OnInit } from '@angular/core';
import { Agent, AgentService } from '../../services/agent.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-agent-list',
  templateUrl: './agent-list.component.html',
  styleUrls: ['./agent-list.component.css']
})
export class AgentListComponent implements OnInit {

  agents: Agent[] = [];
  displayedColumns: string[] = ['lastName', 'firstName', 'idType', 'idNumber', 'birthDate', 'address', 'email', 'phone','patentNumber', 'actions'];


  constructor(private agentService: AgentService,private router: Router) {}

  ngOnInit(): void {
    this.agentService.getAgents().subscribe((agents) => {
      console.log("Agents:", agents);
      this.agents = agents;
    });
  }

  editAgent(agent: Agent) {
    // Implémentez la logique pour modifier l'agent ici
    alert('Fonctionnalité de modification de l\'agent sera implémentée ici.');
  }

  deleteAgent(agent: Agent) {
    // Implémentez la logique pour supprimer l'agent ici
    const index = this.agents.indexOf(agent);
    if (index !== -1) {
      this.agents.splice(index, 1);
      // Vous pouvez également appeler une méthode de service pour supprimer l'agent du backend
      // this.agentService.deleteAgent(agent);
    }
  }

  goBack() {
    // Implémentez la logique pour revenir à la page précédente
    this.router.navigate(['/admin']);
  }
}
