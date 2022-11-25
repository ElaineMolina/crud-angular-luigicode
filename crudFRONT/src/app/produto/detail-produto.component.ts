import { ToastrService } from 'ngx-toastr';
import { ProdutoService } from './../service/produto.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, TitleStrategy } from '@angular/router';
import { Produto } from '../model/produto';

@Component({
  selector: 'app-detail-produto',
  templateUrl: './detail-produto.component.html',
  styleUrls: ['./detail-produto.component.css']
})
export class DetailProdutoComponent implements OnInit {

  produto: Produto | any = null;

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
        this.back();
      }
    );
  }
  
  back(): void{
    this.router.navigate(['/']);
  }
}
