import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Produto } from '../model/produto';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  produtoURL = 'http://localhost:8080/produto/';

  constructor(
    private httpClient: HttpClient){}
    
    public list(): Observable<Produto[]>{
      return this.httpClient.get<Produto[]>(this.produtoURL + 'list');
    }

    public detail(id: number): Observable<Produto>{
      return this.httpClient.get<Produto>(this.produtoURL + `detail/${id}`);
    }
  
    public detailName(name: string): Observable<Produto>{
      return this.httpClient.get<Produto>(this.produtoURL + `detailname/${name}`);
    }

    public save(produto: Produto) : Observable<any>{
      return this.httpClient.post<any>(this.produtoURL + 'create', produto);
    }

    public update(id: number, produto: Produto) : Observable<any>{
      return this.httpClient.put<any>(this.produtoURL + `update/${id}`, produto);
    }

    public delete(id: number): Observable<any>{
      return this.httpClient.delete<any>(this.produtoURL + `delete/${id}`);
    }
  }
