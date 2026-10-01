import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private apiUrl = 'http://localhost:8092/api/v1/payments';

  constructor(private http: HttpClient) {}

  getPayments() {
    return this.http.get<any[]>(this.apiUrl);
  }

  getPaymentById(id: number) {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  addPayment(payment: any) {
    return this.http.post<any>(this.apiUrl, payment);
  }

  updatePayment(id: number, payment: any) {
    return this.http.put<any>(`${this.apiUrl}/${id}`, payment);
  }

  deletePayment(id: number) {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }
}