import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../book.service';
import { Books } from '../books';
 
@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})
export class UpdateBookComponent implements OnInit {


  bid!: number;
  book: Books = new Books(); 
  btitle!: string;
  bauthor!: string;
  bprice!: number;
  bgenre!:string;
  byear! :number;


  constructor(private bookService: BookService, private router: Router, private route: ActivatedRoute) { }
   
  ngOnInit(): void {
    //this.bid = this.route.snapshot.params['book_id'];
    this.bid = this.route.snapshot['params']['id'];
    this.bookService.getBookById(this.bid).subscribe( data =>{
      console.log(data,"Hello...");
      this.book=data;
      console.log(this.book);
      } , (error: HttpErrorResponse)=> { 
           console.log(error['error']);
           this.bid=error['error'].book_id;
           this.btitle= error['error'].title;
           this.bauthor= error['error'].author;
           this.bgenre= error['error'].genre;
           this.bprice= error['error'].price;
           this.byear= error['error'].year;
      }); 

  }
  updateBookData = new FormGroup({
    book_id: new FormControl(),
    title: new FormControl(),
    author: new FormControl(),
    price: new FormControl(),
    genre: new FormControl(),
    year: new FormControl()
  })

  updateBookRecord(){
    console.log("Hello");
      console.log(this.book);
      this.router.navigate(['update-book/:id', this.book_id]);
  }
  
   onSubmit(){
    this.bookService.updateBookRecord(this.bid, this.book).subscribe( data => {
      //this.book=data;
      this.goToBooksList();
    },error => console.log(error));
  }
  goToBooksList()
  {
    this.router.navigate(['/book']);
  }   

  get book_id()
{ return this.updateBookData.get('book_id'); }

 get title()
{ return this.updateBookData.get('title'); }

get author()
{ return this.updateBookData.get('author'); }

get price()
{ return this.updateBookData.get('price'); }

get genre()
{ return this.updateBookData.get('genre'); }

get year()
{ return this.updateBookData.get('year'); }

}
