// import { NgModule } from '@angular/core';
// import { RouterModule, Routes } from '@angular/router';
// import {HomeComponent} from './pages/home/home.component';
// import {AboutComponent} from './pages/about/about.component';
// import {GetInTouchComponent} from './pages/get-in-touch/get-in-touch.component';
//
// const routes: Routes = [
//   { path: 'home', component: HomeComponent },
//   { path: 'about', component: AboutComponent },
//   // { path: 'services', component: ServicesComponent },
//   // { path: 'resources', component: ResourcesComponent },
//   { path: 'get-in-touch', component: GetInTouchComponent }, // Ensure this route exists
//   { path: '', redirectTo: '/home', pathMatch: 'full' }// Default route
//   // { path: '**', component: NotFoundComponent } // Catch-all for 404 errors (Optional)
// ];
//
// @NgModule({
//   imports: [RouterModule.forRoot(routes, { scrollPositionRestoration: 'enabled' })],
//   exports: [RouterModule]
// })
// export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AboutComponent } from './pages/about/about.component';
import { GetInTouchComponent } from './pages/get-in-touch/get-in-touch.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'get-in-touch', component: GetInTouchComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' }, // Default route loads HomeComponent
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
