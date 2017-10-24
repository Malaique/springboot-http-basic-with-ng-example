import {Component} from '@angular/core';
import {Headers, Http, RequestOptions} from '@angular/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private http: Http) {
  }

  title = 'app';

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
