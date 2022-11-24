import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';

import { AppComponent } from './app.component';
import { DetailProdutoComponent } from './produto/detail-produto.component';
import { EditProdutoComponent } from './produto/edit-produto.component';
import { ListaProdutoComponent } from './produto/lista-produto.component';
import { NewProdutoComponent } from './produto/new-produto.component';

@NgModule({
  declarations: [
    AppComponent,
    ListaProdutoComponent,
    DetailProdutoComponent,
    NewProdutoComponent,
    EditProdutoComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule, 
    ToastrModule.forRoot(),
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
