import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-about',
  standalone: true, // Declared as standalone
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css'],
  imports: [
    RouterLink,
    NgClass
  ]
})
export class AboutComponent {
  protected isNavbarOpen: boolean = false;

  toggleNavbar() {
    this.isNavbarOpen = !this.isNavbarOpen;
  }
}
