import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { Books } from '../books';
import { BookService } from '../book.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  books: any;

  constructor(private bookService: BookService, 
              private router : Router)
          { }

  ngOnInit(): void {
    this.bookService.getBooks().subscribe(
      response => {
      console.log(response);
      this.books = response;
      });
  }

  handleSuccessfulResponse(response: any) {
    this.books = response;
  } 

  viewBookDetail(books:any)
  {
    this.router.navigate(['book-details', books]);
  }

  updateBookRecord(book_id:number)
  {
    console.log("Book ID "  +book_id);
    this.router.navigate(['update-book', book_id]);
  }

  deleteBook(book_id:number)
  {
      this.bookService.deleteBook(book_id).subscribe(data => {
        console.log(data);
        this.alertConfirmation();
        //alert("Record Deleted Successfully..");
        this.router.navigate(['/book']);
      }) 
  }

  alertConfirmation() {
    Swal.fire({
      title: 'Are you sure?',
      text: 'This process is irreversible.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, go ahead.',
      cancelButtonText: 'No, let me think',
    }).then((result) => {
      if (result.value) {
        Swal.fire('Removed!', 'Book Record removed successfully.', 'success');
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('Cancelled', 'Book Record still in our database.)', 'error');
      }
    });
  }
}
