import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService, Rental } from '../services/api';

@Component({
  selector: 'app-rental',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './rental.html',
  styleUrl: '../app.css'
})
export class RentalComponent implements OnInit {
  items: Rental[] = [];
  filteredItems: Rental[] = [];
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
    this.api.getCustomers().subscribe(d => this.customers = d);
    this.api.getVehicles().subscribe(d => this.vehicles = d);
    
    
  }

  loadData(): void {
    this.isLoading = true;
    this.errorMessage = '';
    
    this.api.getRentals().subscribe({
      next: (data) => {
        this.items = data;
        this.filteredItems = data;
        this.isLoading = false;
      },
      error: (err) => {
        this.errorMessage = 'Unable to load data. Please check whether the backend server is running on port 8092.';
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
    if (!this.currentItem.rentalId) {
      alert('Please fill out all fields.');
      return;
    }

    this.isLoading = true;
    if (this.isEditing) {
      this.api.updateRental(this.currentItem.rentalId, this.currentItem).subscribe({
        next: () => {
          this.showSuccess('Rental updated successfully');
          this.resetForm();
          this.loadData();
        },
        error: () => this.handleError()
      });
    } else {
      this.api.addRental(this.currentItem).subscribe({
        next: () => {
          this.showSuccess('Rental added successfully');
          this.resetForm();
          this.loadData();
        },
        error: () => this.handleError()
      });
    }
  }

  editItem(item: Rental): void {
    this.isEditing = true;
    this.currentItem = { ...item };
  }

  deleteItem(id: string): void {
    if (confirm('Are you sure you want to delete this record?')) {
      this.isLoading = true;
      this.api.deleteRental(id).subscribe({
        next: () => {
          this.showSuccess('Rental deleted successfully');
          this.loadData();
        },
        error: () => this.handleError()
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

  handleError(): void {
    this.isLoading = false;
    this.errorMessage = 'An error occurred while connecting to the database.';
  }
}
