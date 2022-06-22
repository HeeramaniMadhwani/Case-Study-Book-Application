import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookService } from '../book.service';
import { Books } from '../books';

@Component({
  selector: 'app-view-book-details',
  templateUrl: './view-book-details.component.html',
  styleUrls: ['./view-book-details.component.css']
})
export class ViewBookDetailsComponent implements OnInit {

  book_id!: number;
  book: Books = new Books();
  title!: string;
  author!: string;
  price!: number;
  genre!:string;
  year! :number;

  constructor(private route:ActivatedRoute, private bookService: BookService) { }

  ngOnInit(): void {
    this.book_id = this.route.snapshot['params']['id'];
    console.log(this.route.snapshot);
    this.book = new Books();
    //console.log(this.book_id);
    this.bookService.getBookById(this.book_id).subscribe( data =>{
    console.log(data,"Hello...");
    this.book=data;
    console.log(this.book);
    }, (error: HttpErrorResponse)=> { 
         console.log(error['error']);
         this.title= error['error'].title;
         this.author= error['error'].author;
         this.genre= error['error'].genre;
         this.price= error['error'].price;
         this.year= error['error'].year;
    }); 
  }

}
