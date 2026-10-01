import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService, Customer } from '../services/api';

@Component({
  selector: 'app-customer',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './customer.html',
  styleUrl: '../app.css'
})
export class CustomerComponent implements OnInit {
  items: Customer[] = [];
  filteredItems: Customer[] = [];
  searchQuery: string = '';
  
  isLoading: boolean = false;
  errorMessage: string = '';
  successMessage: string = '';

  isEditing: boolean = false;
  currentItem: any = {};
  
  customers: any[] = [];
  vehicles: any[] = [];
  rentals: any[] = [];

  constructor(private api: ApiService) {}

  ngOnInit(): void {
    this.loadData();
    
    
  }

  loadData(): void {
    this.isLoading = true;
    this.errorMessage = '';
    
    this.api.getCustomers().subscribe({
      next: (data) => {
        this.items = data;
        this.filteredItems = data;
        this.isLoading = false;
      },
      error: (err) => {
        this.errorMessage = err.error?.message || err.error || err.message || 'An error occurred while connecting to the database.';
        this.isLoading = false;
      }
    });
  }

  filterData(): void {
    const q = this.searchQuery.toLowerCase();
    this.filteredItems = this.items.filter(item => 
      Object.values(item).some(val => String(val).toLowerCase().includes(q))
    );
  }

  saveItem(): void {
    if (!this.currentItem.customerId) {
      alert('Please fill out all fields.');
      return;
    }

    this.isLoading = true;
    if (this.isEditing) {
      this.api.updateCustomer(this.currentItem.customerId, this.currentItem).subscribe({
        next: () => {
          this.showSuccess('Customer updated successfully');
          this.resetForm();
          this.loadData();
        },
        error: (err) => this.handleError(err)
      });
    } else {
      this.api.addCustomer(this.currentItem).subscribe({
        next: () => {
          this.showSuccess('Customer added successfully');
          this.resetForm();
          this.loadData();
        },
        error: (err) => this.handleError(err)
      });
    }
  }

  editItem(item: Customer): void {
    this.isEditing = true;
    this.currentItem = { ...item };
  }

  deleteItem(id: string): void {
    if (confirm('Are you sure you want to delete this record?')) {
      this.isLoading = true;
      this.api.deleteCustomer(id).subscribe({
        next: () => {
          this.showSuccess('Customer deleted successfully');
          this.loadData();
        },
        error: (err) => this.handleError(err)
      });
    }
  }

  resetForm(): void {
    this.isEditing = false;
    this.currentItem = {};
  }

  showSuccess(msg: string): void {
    this.successMessage = msg;
    setTimeout(() => { this.successMessage = ''; }, 3000);
  }

  handleError(err: any): void {
    this.isLoading = false;
    this.errorMessage = err?.error?.message || err?.error || err?.message || 'An error occurred while connecting to the database.';
  }
}
