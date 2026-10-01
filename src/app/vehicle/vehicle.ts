import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService, Vehicle } from '../services/api';

@Component({
  selector: 'app-vehicle',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './vehicle.html',
  styleUrl: '../app.css'
})
export class VehicleComponent implements OnInit {
  items: Vehicle[] = [];
  filteredItems: Vehicle[] = [];
  searchQuery: string = '';
  
  isLoading: boolean = false;
  errorMessage: string = '';
  successMessage: string = '';

  isEditing: boolean = false;
  currentItem: any = {};
  
  customers: any[] = [];
  vehicles: any[] = [];
  rentals: any[] = [];
  branches: any[] = [];

  constructor(private api: ApiService) {}

  ngOnInit(): void {
    this.loadData();
    this.api.getBranches().subscribe(d => this.branches = d);
  }

  loadData(): void {
    this.isLoading = true;
    this.errorMessage = '';
    
    this.api.getVehicles().subscribe({
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
    if (!this.currentItem.vehicleId) {
      alert('Please fill out all fields.');
      return;
    }

    this.isLoading = true;
    if (this.isEditing) {
      this.api.updateVehicle(this.currentItem.vehicleId, this.currentItem).subscribe({
        next: () => {
          this.showSuccess('Vehicle updated successfully');
          this.resetForm();
          this.loadData();
        },
        error: () => this.handleError()
      });
    } else {
      this.api.addVehicle(this.currentItem).subscribe({
        next: () => {
          this.showSuccess('Vehicle added successfully');
          this.resetForm();
          this.loadData();
        },
        error: () => this.handleError()
      });
    }
  }

  editItem(item: Vehicle): void {
    this.isEditing = true;
    this.currentItem = { ...item };
  }

  deleteItem(id: string): void {
    if (confirm('Are you sure you want to delete this record?')) {
      this.isLoading = true;
      this.api.deleteVehicle(id).subscribe({
        next: () => {
          this.showSuccess('Vehicle deleted successfully');
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
