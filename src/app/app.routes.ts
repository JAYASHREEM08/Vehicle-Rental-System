import { Routes } from '@angular/router';
import { BranchComponent } from './branch/branch';
import { VehicleComponent } from './vehicle/vehicle';
import { CustomerComponent } from './customer/customer';
import { RentalComponent } from './rental/rental';
import { PaymentComponent } from './payment/payment';

export const routes: Routes = [
  { path: '', redirectTo: 'branches', pathMatch: 'full' },
  { path: 'branches', component: BranchComponent },
  { path: 'vehicles', component: VehicleComponent },
  { path: 'customers', component: CustomerComponent },
  { path: 'rentals', component: RentalComponent },
  { path: 'payments', component: PaymentComponent }
];
