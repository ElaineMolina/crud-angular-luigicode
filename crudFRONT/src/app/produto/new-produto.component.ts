import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

import { Produto } from '../model/produto';
import { ProdutoService } from './../service/produto.service';

@Component({
  selector: 'app-new-produto',
  templateUrl: './new-produto.component.html',
  styleUrls: ['./new-produto.component.css']
})
export class NewProdutoComponent implements OnInit {


  name = ' ';
  preco: number | any = null;

  constructor(
    private produtoService: ProdutoService,
    private toastr: ToastrService,
    private router: Router
    ) { }

  ngOnInit() {
  }

  onCreate(): void {
    const produto = new Produto(this.name, this.preco);
    this.produtoService.save(produto).subscribe(
      data => {
        this.toastr.success('Produto Criado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/']);
      }
    );
  }

}