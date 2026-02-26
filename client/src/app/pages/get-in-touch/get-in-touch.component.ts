import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ApiService, ContactRequest } from '../../ApiService/ApiService';

@Component({
  selector: 'app-get-in-touch',
  templateUrl: './get-in-touch.component.html',
  styleUrls: ['./get-in-touch.component.css'],
  imports: [FormsModule],
})
export class GetInTouchComponent {
  formData = {
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
    roles: { parent: false, therapist: false, teacher: false, other: false },
    interests: { resources: false, consultation: false, coaching: false, other: false },
  };

  submitting = false;
  submitted = false;
  error = '';

  constructor(private apiService: ApiService) {}

  onSubmit() {
    this.submitting = true;
    this.error = '';

    const roles = Object.entries(this.formData.roles)
      .filter(([, v]) => v)
      .map(([k]) => k);

    const interests = Object.entries(this.formData.interests)
      .filter(([, v]) => v)
      .map(([k]) => k);

    const request: ContactRequest = {
      firstName: this.formData.firstName,
      lastName: this.formData.lastName,
      email: this.formData.email,
      phone: this.formData.phone,
      roles,
      interests,
    };

    this.apiService.submitContact(request).subscribe({
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
