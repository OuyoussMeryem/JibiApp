import { Component, OnInit } from '@angular/core';
import { AgenceService } from '../../services/agence.service';
import { Agence } from '../../models/agence.model';

@Component({
  selector: 'app-agence-list',
  templateUrl: './agence-list.component.html',
  styleUrls: ['./agence-list.component.css']
})
export class AgenceListComponent implements OnInit {
  agences: Agence[] = [];
  displayedColumns: string[] = ['nom', 'description','Logo','actions'];

  constructor(private agenceService: AgenceService) {}

  ngOnInit(): void {
    this.agenceService.getAgences().subscribe((agences) => {
      this.agences = agences;
      console.log('agenceeeee',this.agences)
    });
  }

  editAgence(agence: Agence) {
    alert('Edit functionality will be implemented here.');
  }

  deleteAgence(agence: Agence) {
    alert('Delete functionality will be implemented here.');
  }
}
