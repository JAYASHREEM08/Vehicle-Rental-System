import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BranchService {

  private apiUrl = 'http://localhost:8092/api/v1/branches';

  constructor(private http: HttpClient) {}

  getBranches() {
    return this.http.get<any[]>(this.apiUrl);
  }

  addBranch(branch: any) {
    return this.http.post(this.apiUrl, branch);
  }

  updateBranch(id: string, branch: any) {
    return this.http.put(`${this.apiUrl}/${id}`, branch);
  }

  deleteBranch(id: string) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}