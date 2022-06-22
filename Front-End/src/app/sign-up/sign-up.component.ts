import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  //componentName="SignUp"
 
  constructor(private http: HttpClient,private router:Router ) { }

  ngOnInit(): void {
  }
  registerForm = new FormGroup({
    id: new FormControl('',[Validators.required, Validators.pattern("[0-9]{3}")]),
    name: new FormControl('', [Validators.required,Validators.minLength(4)]),
    email: new FormControl('',[Validators.required, Validators.pattern('[(a-z)(A-Z)(0-9).]+@[a-z]+.[a-z]{2,3}')]),
    mobileNo: new FormControl('', [Validators.required, Validators.pattern("[0-9]{10}")]),
    password: new FormControl('',Validators.required),
    role: new FormControl('', Validators.required)
  })

  register()
  {
    //console.log(this.registerForm.value);
     this.http.post('http://localhost:8089/api/v1/registration', this.registerForm.value).subscribe((data)=>{
    console.log(data);
    
    this.successNotification();
    //alert("Registered Successfully!")
    this.router.navigate(['/login']);
    if(this.registerForm.invalid){
      return;
    }
  }); 
  }

get id()
{ return this.registerForm.get('id') }

 get name()
{ return this.registerForm.get('name') }

get email()
{ return this.registerForm.get('email'); }

get mobileNo()
{ return this.registerForm.get('mobileNo'); }

get password()
{ return this.registerForm.get('password'); }

get role()
{ return this.registerForm.get('role'); }


get f() { return this.registerForm.controls; }
successNotification()
{
  this.f;
  if(this.registerForm != null)
  {
  //this.registerForm.value == true;
  Swal.fire('Registered Successfully!');
  }
}

}