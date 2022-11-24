import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailProdutoComponent } from './detail-produto.component';

describe('DetailProdutoComponent', () => {
  let component: DetailProdutoComponent;
  let fixture: ComponentFixture<DetailProdutoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailProdutoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailProdutoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
