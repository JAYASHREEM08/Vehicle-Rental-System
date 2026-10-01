import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  private apiUrl = 'http://localhost:8092/api/v1/vehicles';

  constructor(private http: HttpClient) {}

  getVehicles() {
    return this.http.get<any[]>(this.apiUrl);
  }

  getVehicleById(id: string) {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  addVehicle(vehicle: any) {
    return this.http.post<any>(this.apiUrl, vehicle);
  }

  updateVehicle(id: string, vehicle: any) {
    return this.http.put<any>(`${this.apiUrl}/${id}`, vehicle);
  }

  deleteVehicle(id: string) {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }
}