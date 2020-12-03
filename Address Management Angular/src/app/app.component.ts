import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from './model/address';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'addressMGT';
  router:Router;
  constructor(router:Router ){
this.router=router;
  }

  ngOnInit(){
    this.router.navigate([]);
  }
 
}
