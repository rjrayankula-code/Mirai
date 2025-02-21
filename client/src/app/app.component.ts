import { Component } from '@angular/core';
import {NavigationEnd, Router, RouterLink, RouterOutlet} from '@angular/router';
import {NgClass, NgIf} from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  imports: [
    RouterOutlet,
    NgClass,
    NgIf,
    RouterLink
  ]
})
export class AppComponent {
  isNavbarOpen: boolean = false; // Keep track of navbar state
  showMainLayout: boolean = true; // Control visibility of main layout components

  // Function to toggle the navbar's visibility
  toggleNavbar() {
    this.isNavbarOpen = !this.isNavbarOpen;
  }

  constructor(private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        // Hide the main layout (header, footer, content) when on "Get In Touch"
        this.showMainLayout = event.url !== '/get-in-touch';
      }
    });
  }

}



