import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ContactRequest {
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  roles: string[];
  interests: string[];
}

export interface BookingRequest {
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  serviceType: string;
  preferredDate: string;
  preferredTime: string;
  notes: string;
}

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  submitContact(request: ContactRequest): Observable<{ message: string }> {
    return this.http.post<{ message: string }>(`${this.apiUrl}/contact`, request);
  }

  submitBooking(request: BookingRequest): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/bookings`, request);
  }
}
