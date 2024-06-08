import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';


export interface Agent {
  lastName: string;
  firstName: string;
  idType: string;
  idNumber: string;
  birthDate: string;
  address: string;
  email: string;
  confirmEmail: string;
  phone: string;
  registrationNumber: string;
  patentNumber: string;
}

@Injectable({
  providedIn: 'root'
})
export class AgentService {

  
  
    private agents: Agent[] = [
      {
        lastName: 'Doe',
        firstName: 'John',
        idType: 'CIN',
        idNumber: 'CIN123456',
        birthDate: '1985-05-15',
        address: '123 Main St',
        email: 'john.doe@example.com',
        confirmEmail: 'john.doe@example.com',
        phone: '555-555-5555',
        registrationNumber: 'REG123456',
        patentNumber: 'PAT123456'
      },
      {
        lastName: 'Smith',
        firstName: 'Jane',
        idType: 'Passport',
        idNumber: 'PAS123456',
        birthDate: '1990-08-25',
        address: '456 Elm St',
        email: 'jane.smith@example.com',
        confirmEmail: 'jane.smith@example.com',
        phone: '555-555-1234',
        registrationNumber: 'REG654321',
        patentNumber: 'PAT654321'
      },
      {
        lastName: 'Smith',
        firstName: 'Jane',
        idType: 'Passport',
        idNumber: 'PAS123456',
        birthDate: '1990-08-25',
        address: '456 Elm St',
        email: 'jane.smith@example.com',
        confirmEmail: 'jane.smith@example.com',
        phone: '555-555-1234',
        registrationNumber: 'REG654321',
        patentNumber: 'PAT654321'
      },
      {
        lastName: 'Smith',
        firstName: 'Jane',
        idType: 'Passport',
        idNumber: 'PAS123456',
        birthDate: '1990-08-25',
        address: '456 Elm St',
        email: 'jane.smith@example.com',
        confirmEmail: 'jane.smith@example.com',
        phone: '555-555-1234',
        registrationNumber: 'REG654321',
        patentNumber: 'PAT654321'
      }
    ];
  constructor() { }






  getAgents(): Observable<Agent[]> {
    return of(this.agents);
  }

  addAgent(agent: Agent): void {
    this.agents.push(agent);
  }

  editAgent(index: number, updatedAgent: Agent): void {
    this.agents[index] = updatedAgent;
  }

  deleteAgent(index: number): void {
    this.agents.splice(index, 1);
  }
}







  



