import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
//import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Books } from '../books';
import { BookService } from '../book.service';


@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {
  book : Books = new Books();

  constructor(private bookService: BookService , private router:Router) { 
    //this.book = new Books();
  }

  ngOnInit(): void {
    
  }
  
  addBookData = new FormGroup({
    book_id: new FormControl(),
    title: new FormControl(),
    author: new FormControl(),
    price: new FormControl(),
    genre: new FormControl(),
    year: new FormControl()
  })
  test()
  {
    console.warn(this.addBookData.value +" Print this..");

  }
  saveBook(){
     this.test();
     this.bookService.createBook(this.addBookData.value).subscribe(data => {
     console.log("Print..");
     console.log(data);
     this.goToBookList();
    }, error => console.log(error));
  }
  
   goToBookList(){
     this.router.navigate(['/book']);
   }
   addBook()
   {

       console.log(this.book);
       this.saveBook();
   }

get book_id()
{ return this.addBookData.get('book_id') }

 get title()
{ return this.addBookData.get('title') }

get author()
{ return this.addBookData.get('author'); }

get price()
{ return this.addBookData.get('price'); }

get genre()
{ return this.addBookData.get('genre'); }

get year()
{ return this.addBookData.get('year'); }

}
