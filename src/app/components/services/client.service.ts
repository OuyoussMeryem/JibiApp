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
      lastName: 'rafiki',
      firstName: 'ibtissam',
      idType: 'CIN',
      idNumber: 'CIN123456',
      birthDate: '2002-10-24',
      address: 'JNAN AWRAD',
      email: 'ibtissamrafiki172@gmail.com',
      confirmEmail: 'john.doe@example.com',
      phone: '0664789541',
      registrationNumber: 'REG123456'
      
    },
    {
      lastName: 'meryem',
      firstName: 'ouyouss',
      idType: 'CIN',
      idNumber: 'CI123456',
      birthDate: '2002-05-15',
      address: '456 Elm St',
      email: 'meryem125@gmail.com',
      confirmEmail: 'jane.smith@example.com',
      phone: '0632541235',
      registrationNumber: 'REG654321'
      
    },
    {
      lastName: 'manal',
      firstName: 'manal',
      idType: 'CIN',
      idNumber: 'CI123456',
      birthDate: '2002-06-15',
      address: '456 Elm St',
      email: 'manal35@gmail.com',
      confirmEmail: 'jane.smith@example.com',
      phone: '0632154258',
      registrationNumber: 'REG654321'
      
    },
    {
      lastName: 'hoda',
      firstName: 'hoda',
      idType: 'CIN',
      idNumber: 'CI123456',
      birthDate: '2002-05-25',
      address: '456 Elm St',
      email: 'hoda124@gmail.com',
      confirmEmail: 'jane.smith@example.com',
      phone: '0632145235',
      registrationNumber: 'REG654321'
      
    }
    ,
    {
      lastName: 'zaineb',
      firstName: 'zaineb',
      idType: 'CIN',
      idNumber: 'CI123456',
      birthDate: '2002-05-10',
      address: '456 Elm St',
      email: 'zaineb35@gmail.com',
      confirmEmail: 'jane.smith@example.com',
      phone: '0632145235',
      registrationNumber: 'REG654321'
      
    }
    ,
    {
      lastName: 'mohamed',
      firstName: 'mohamed',
      idType: 'CIN',
      idNumber: 'CI123456',
      birthDate: '2002-02-14',
      address: '456 Elm St',
      email: 'mohamed65@gmail.com',
      confirmEmail: 'jane.smith@example.com',
      phone: '0632145835',
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
