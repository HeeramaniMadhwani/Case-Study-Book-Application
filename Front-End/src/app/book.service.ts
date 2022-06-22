import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Books } from './books';
//import { Books } from './books';

/* export class Books {
  constructor(
   book_id: number,
   title: string,
   author: string,
   price: number,
   genre: number,
   year: number,
  ){}
} */

@Injectable({
  providedIn: 'root'
})


export class BookService {

  private baseUrl = "http://localhost:8085/api/v1/books";
  
  private baseURL = "http://localhost:8085/api/v1/book";

  constructor(private httpClient: HttpClient) { }

  
  getBooks(): Observable<Books[]>{
    console.log('Getting Books List');
    return this.httpClient.get<Books[]>('http://localhost:8085/api/v1/allBooks');
  }

  createBook(book: Books): Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}`, book);
  }
  
  getBookById(book_id:number): Observable<Books>
  {
    return this.httpClient.get<Books>(`${this.baseURL}/${book_id}`);
  }

  updateBookRecord(book_id:number, book:Books) :Observable<Object>
  {
    
      return this.httpClient.put(`${this.baseUrl}/${book_id}`, book);
  }

  deleteBook(book_id:number): Observable<Object>
  {
    return this.httpClient.delete(`${this.baseURL}/${book_id}`);
  }

  viewBookDetail(book_id:number):Observable<Object>
  {
      return this.httpClient.get<Books>(`${this.baseURL}/${book_id}`);
  }
}
