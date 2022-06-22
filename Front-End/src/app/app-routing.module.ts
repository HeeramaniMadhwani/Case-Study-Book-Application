import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddBookComponent } from './add-book/add-book.component';
import { BookComponent } from './book/book.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { AboutComponent } from './about/about.component';
import { UpdateBookComponent } from './update-book/update-book.component';
import { ContactComponent } from './contact/contact.component';
import { ViewBookDetailsComponent } from './view-book-details/view-book-details.component';

const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignUpComponent },
  { path:'about', component:AboutComponent},
  { path: 'contact', component:ContactComponent},
  { path:'book', component:BookComponent },
  { path: 'add-book', component: AddBookComponent},
  { path: 'update-book/:id',component: UpdateBookComponent },
  { path: 'book-details/:id', component: ViewBookDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
