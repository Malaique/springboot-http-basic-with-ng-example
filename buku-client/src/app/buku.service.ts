import {Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Buku} from './buku';

@Injectable()
export class BukuService {
  private bukuContent: {
    content: Buku[],
    last: boolean,
    totalPages: number,
    totalElements: number,
    first: boolean,
    numberOfElements: number,
    size: number,
    number: number
  };

  constructor(private http: Http) {
  }

  getBukuContent(page: number = 0) {
    return this.http.get('http://localhost:8080/api/buku/list', {
      params: {
        size: 5,
        page: page
      }
    });
  }

  saveBuku(buku: Buku) {
    const cridentals = btoa('user:password');
    const headers = new Headers();

    headers.set('Authorization', `Basic ${cridentals}`);
    return this.http.post('http://localhost:8080/api/buku/submit', buku, {
      headers: headers
    });
  }
}
