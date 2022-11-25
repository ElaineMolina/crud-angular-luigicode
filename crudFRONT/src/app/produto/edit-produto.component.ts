import { ToastrService } from 'ngx-toastr';
import { ProdutoService } from './../service/produto.service';
import { Component, OnInit } from '@angular/core';
import { Produto } from '../model/produto';
import { ActivatedRoute, Router } from '@angular/router';
import { subscribeOn } from 'rxjs';

@Component({
  selector: 'app-edit-produto',
  templateUrl: './edit-produto.component.html',
  styleUrls: ['./edit-produto.component.css']
})
export class EditProdutoComponent implements OnInit {

  produto!: Produto;
  
  constructor(
    private produtoService: ProdutoService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    const id = this.activatedRoute.snapshot.params['id'];
    this.produtoService.detail(id).subscribe(
      data => {
        this.produto = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/']);
      }
    );
  }

  onUpdate(): void {
    const id = this.activatedRoute.snapshot.params['id'];
    this.produtoService.update(id, this.produto).subscribe(
      data => {
        this.toastr.success('Producto atualizado', 'OK', {
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
