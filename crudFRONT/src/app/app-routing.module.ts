import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DetailProdutoComponent } from './produto/detail-produto.component';
import { EditProdutoComponent } from './produto/edit-produto.component';
import { ListaProdutoComponent } from './produto/lista-produto.component';
import { NewProdutoComponent } from './produto/new-produto.component';


const routes: Routes = [
    {path: '', component: ListaProdutoComponent},
    {path: 'detail/:id', component: DetailProdutoComponent},
    {path: 'new', component: NewProdutoComponent},
    {path: 'edit/:id', component: EditProdutoComponent},
    {path: '**', redirectTo: '', pathMatch: 'full'}
];

@NgModule({
    declarations: [
    ],
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}