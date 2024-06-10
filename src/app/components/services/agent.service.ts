import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
export interface Agent {
  nom: string;
  prenom: string;
  email: string;
  telephone: string;
  pieceIdentiteFaceOne: File | null; // Assurez-vous que le type est correct pour les fichiers
  pieceIdentiteFaceTwo: File | null; // Assurez-vous que le type est correct pour les fichiers
  numPieceIdentite: string;
  dateNaissance: string; // Ou vous pouvez utiliser le type Date
  adresse: string;
  numPattente: string;

}


@Injectable({
  providedIn: 'root'
})
export class AgentService {

  private apiUrl = 'http://localhost:8089/api/BackOffice/listAgents';

  


  private apiUrl1 = 'http://localhost:8089/api/BackOffice'; // Assurez-vous que l'URL est correcte

  constructor(private http: HttpClient) {}

  addAgent(formData: FormData): Observable<any> {
    return this.http.post(`${this.apiUrl1}/createAgent`, formData);
  }
  getAgents(): Observable<Agent[]> {
    console.log('vvvvvvvv',this.http.get<Agent[]>(this.apiUrl));
    return this.http.get<Agent[]>(this.apiUrl);
  }

  // Ajoutez d'autres méthodes pour manipuler les agents si nécessaire
}
