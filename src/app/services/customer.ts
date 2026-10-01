import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private apiUrl = 'http://localhost:8092/api/v1/customers';

  constructor(private http: HttpClient) {}

  getCustomers() {
    return this.http.get<any[]>(this.apiUrl);
  }

  getCustomerById(id: number) {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  addCustomer(customer: any) {
    return this.http.post<any>(this.apiUrl, customer);
  }

  updateCustomer(id: number, customer: any) {
    return this.http.put<any>(`${this.apiUrl}/${id}`, customer);
  }

  deleteCustomer(id: number) {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }
}