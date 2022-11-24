import { ProdutoService } from './../service/produto.service';
import { Produto } from './../model/produto';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-lista-produto',
  templateUrl: './lista-produto.component.html',
  styleUrls: ['./lista-produto.component.css']
})
export class ListaProdutoComponent implements OnInit {

  produtos: Produto[] = [];

  constructor(
    private produtoService: ProdutoService,
    private toastr: ToastrService) { }

  ngOnInit(){
    this.chargeProdutos();
  }
 
 chargeProdutos(): void {
    this.produtoService.list().subscribe(
      data => {
        this.produtos = data;
      },
      err =>{
        console.log(err);
      }
    );
  }

  
  delete(id: any) {
    alert('deletar o ' + id);
  }
}
