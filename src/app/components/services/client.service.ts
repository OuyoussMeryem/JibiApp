import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

export interface Client {
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
  
}

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private clients: Client[] = [
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
      registrationNumber: 'REG123456'
      
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
      registrationNumber: 'REG654321'
      
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
      registrationNumber: 'REG654321'
      
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
      registrationNumber: 'REG654321'
      
    }
  ];

  constructor() { }

  getClients(): Observable<Client[]> {
    return of(this.clients);
  }

  addClient(client: Client): void {
    this.clients.push(client);
  }

  editClient(index: number, updatedClient: Client): void {
    this.clients[index] = updatedClient;
  }

  deleteClient(index: number): void {
    this.clients.splice(index, 1);
  }
}
