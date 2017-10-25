import {Component} from '@angular/core';
import {Headers, Http} from '@angular/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private http: Http) {
  }

  title = 'app';

  submitData() {
    const cridentals = btoa('user:password');
    const headers = new Headers();

    headers.set('Authorization', `Basic ${cridentals}`);
    this.http.post('http://localhost:8080/api/buku/submit', {
      id: '213',
      nama: 'JavaEE introduction',
      pengarang: 'Dimas Maryanto',
      tahunTerbit: 2017
    }, {
      headers: headers
    }).subscribe(
      data => console.log(data),
      error => console.log(error)
    );
  }

  getData() {
    const cridentals = btoa('user:password');
    const headers = new Headers();

    headers.set('Authorization', `Basic ${cridentals}`);
    this.http.get('http://localhost:8080/api/buku/', {
      headers: headers,
      params: {
        id: '213',
        nama: 'JavaEE introduction',
        pengarang: 'Dimas Maryanto',
        tahunTerbit: 2017
      }
    }).subscribe(
      data => console.log(data),
      error => console.log(error)
    );
  }

  ambilData() {
    const cridentals = btoa('user:password');
    const headers = new Headers();

    headers.set('Authorization', `Basic ${cridentals}`);
    this.http.get('http://localhost:8080/api/buku/10', {
      headers: headers
    }).subscribe(
      data => console.log(data),
      error => console.log(error)
    );
  }
}
