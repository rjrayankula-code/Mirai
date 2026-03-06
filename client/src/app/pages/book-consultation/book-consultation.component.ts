import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ApiService, BookingRequest } from '../../ApiService/ApiService';

@Component({
  selector: 'app-book-consultation',
  templateUrl: './book-consultation.component.html',
  styleUrls: ['./book-consultation.component.css'],
  imports: [FormsModule],
})
export class BookConsultationComponent {
  formData: BookingRequest = {
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
    serviceType: '',
    preferredDate: '',
    preferredTime: '',
    notes: '',
  };

  submitting = false;
  submitted = false;
  error = '';

  constructor(private apiService: ApiService) {}

  onSubmit() {
    this.submitting = true;
    this.error = '';

    this.apiService.submitBooking(this.formData).subscribe({
      next: () => {
        this.submitted = true;
        this.submitting = false;
      },
      error: () => {
        this.error = 'Something went wrong. Please try again.';
        this.submitting = false;
      },
    });
  }
}
