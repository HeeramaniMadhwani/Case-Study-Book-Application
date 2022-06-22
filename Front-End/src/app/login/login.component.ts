import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  jwt: any;


  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {

  }
  loginForm = new FormGroup({
    email: new FormControl('',[Validators.required,Validators.pattern('[(a-z)(A-Z)(0-9).]+@[a-z]+.[a-z]{2,3}')]),
    password: new FormControl('', Validators.required)
  })
  users:any;
  login(){
    
    this.http.post('http://localhost:8089/api/v1/token', this.loginForm.value).subscribe((data)=>{
      this.jwt = data;
      localStorage.setItem('token', this.jwt.token);
      window.alert("Login Succeed!");
      /* if(this.jwt.token!==localStorage.key)
       {
          this.userService.getUsers().subscribe( response => 
          {    
               console.log(response);
               this.users=response;
          });
        this.router.navigate(['users-register']);
       }  
        */
      //console.log(this.loginForm.value);
      this.router.navigate(['/book']);
    },  error => console.log(error)); 
  }

  get email(){

    return this.loginForm.get('email');

  }
  get password(){

    return this.loginForm.get('password');

  }
}
