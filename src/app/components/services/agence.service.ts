import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Agence } from '../models/agence.model';


@Injectable({
  providedIn: 'root'
})
export class AgenceService {
  private baseUrl = 'http://localhost:8089/api/BackOffice';

  constructor(private http: HttpClient) {}

  getAgences(): Observable<Agence[]> {
    return this.http.get<Agence[]>(`${this.baseUrl}/agences`);
  }

  addAgence(agence: Agence): Observable<Agence> {
    const formData = new FormData();
    formData.append('nom', agence.nom);
    formData.append('description', agence.description);
    formData.append('image', agence.image);

    return this.http.post<Agence>(`${this.baseUrl}/createAgence`, formData);
  }
}
