import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AboutComponent } from './pages/about/about.component';
import { GetInTouchComponent } from './pages/get-in-touch/get-in-touch.component';
import { BookConsultationComponent } from './pages/book-consultation/book-consultation.component';

export const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  // { path: 'services', component: ServicesComponent },
  // { path: 'resources', component: ResourcesComponent },
  { path: 'get-in-touch', component: GetInTouchComponent },
  { path: 'book-consultation', component: BookConsultationComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
];
