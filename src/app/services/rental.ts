import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RentalService {

  private apiUrl = 'http://localhost:8092/api/v1/rentals';

  constructor(private http: HttpClient) {}

  getRentals() {
    return this.http.get<any[]>(this.apiUrl);
  }

  getRentalById(id: number) {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  addRental(rental: any) {
    return this.http.post<any>(this.apiUrl, rental);
  }

  updateRental(id: number, rental: any) {
    return this.http.put<any>(`${this.apiUrl}/${id}`, rental);
  }

  deleteRental(id: number) {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }
}