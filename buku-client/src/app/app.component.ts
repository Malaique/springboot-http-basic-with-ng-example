import {Component, OnDestroy, OnInit} from '@angular/core';
import {Headers, Http} from '@angular/http';
import {Buku} from './buku';
import {Subscription} from 'rxjs/Subscription';
import {BukuService} from './buku.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  subscript: Subscription;
  title = 'app';
  listPages: { index: number, text: string }[];
  bukuContent: {
    content: Buku[],
    last: boolean,
    totalPages: number,
    totalElements: number,
    first: boolean,
    numberOfElement: number,
    size: number,
    number: number
  };

  ngOnDestroy(): void {
    // throw new Error("Method not implemented.");
    this.subscript.unsubscribe();
  }

  gotoPage(page: number = 0) {
    this.subscript = this.bukuService.getBukuContent(page).subscribe(
      data => {
        this.listPages = [];
        this.bukuContent = data.json();
        for (let halaman = 0; halaman < this.bukuContent.totalPages; halaman++) {
          this.listPages.push({index: halaman, text: `${halaman + 1}`});
        }
      },
      error => {
        console.log(error);
      }
    );
  }

  ngOnInit(): void {
    this.gotoPage();
  }

  constructor(private http: Http, private bukuService: BukuService) {
  }


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
