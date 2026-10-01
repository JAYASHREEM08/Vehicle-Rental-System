import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Branch { branchId: string; branchName: string; city: string; }
export interface Vehicle { vehicleId: string; vehicleNumber: string; vehicleType: string; rentPerDay: number; branchId: string; status?: string; }
export interface Customer { customerId: string; customerName: string; phone: string; city: string; }
export interface Rental { rentalId: string; customerId: string; vehicleId: string; startDate: string; returnDate: string; status?: string; }
export interface Payment { paymentId: string; rentalId: string; amount: number; paymentDate: string; }

@Injectable({ providedIn: 'root' })
export class ApiService {
  private readonly apiUrl = 'http://localhost:8092/api/v1';
  private readonly customerUrl = `${this.apiUrl}/customers`;

  constructor(private http: HttpClient) { }

  getBranches(): Observable<Branch[]> { return this.http.get<Branch[]>(`${this.apiUrl}/branches`); }
  addBranch(branch: Branch): Observable<Branch> { return this.http.post<Branch>(`${this.apiUrl}/branches`, branch); }
  updateBranch(id: string, branch: Branch): Observable<Branch> { return this.http.put<Branch>(`${this.apiUrl}/branches/${id}`, branch); }
  deleteBranch(id: string): Observable<any> { return this.http.delete(`${this.apiUrl}/branches/${id}`, { responseType: 'text' }); }

  getVehicles(): Observable<Vehicle[]> { return this.http.get<Vehicle[]>(`${this.apiUrl}/vehicles`); }
  addVehicle(vehicle: Vehicle): Observable<Vehicle> { return this.http.post<Vehicle>(`${this.apiUrl}/vehicles`, vehicle); }
  updateVehicle(id: string, vehicle: Vehicle): Observable<Vehicle> { return this.http.put<Vehicle>(`${this.apiUrl}/vehicles/${id}`, vehicle); }
  deleteVehicle(id: string): Observable<any> { return this.http.delete(`${this.apiUrl}/vehicles/${id}`, { responseType: 'text' }); }

  getCustomers(): Observable<Customer[]> { return this.http.get<Customer[]>(this.customerUrl); }
  addCustomer(customer: Customer): Observable<Customer> { return this.http.post<Customer>(this.customerUrl, customer); }
  updateCustomer(id: string, customer: Customer): Observable<Customer> { return this.http.put<Customer>(`${this.customerUrl}/${id}`, customer); }
  deleteCustomer(id: string): Observable<any> { return this.http.delete(`${this.customerUrl}/${id}`, { responseType: 'text' }); }

  getRentals(): Observable<Rental[]> { return this.http.get<Rental[]>(`${this.apiUrl}/rentals`); }
  addRental(rental: Rental): Observable<Rental> { return this.http.post<Rental>(`${this.apiUrl}/rentals`, rental); }
  updateRental(id: string, rental: Rental): Observable<Rental> { return this.http.put<Rental>(`${this.apiUrl}/rentals/${id}`, rental); }
  deleteRental(id: string): Observable<any> { return this.http.delete(`${this.apiUrl}/rentals/${id}`, { responseType: 'text' }); }

  getPayments(): Observable<Payment[]> { return this.http.get<Payment[]>(`${this.apiUrl}/payments`); }
  addPayment(payment: Payment): Observable<Payment> { return this.http.post<Payment>(`${this.apiUrl}/payments`, payment); }
  updatePayment(id: string, payment: Payment): Observable<Payment> { return this.http.put<Payment>(`${this.apiUrl}/payments/${id}`, payment); }
  deletePayment(id: string): Observable<any> { return this.http.delete(`${this.apiUrl}/payments/${id}`, { responseType: 'text' }); }
}
