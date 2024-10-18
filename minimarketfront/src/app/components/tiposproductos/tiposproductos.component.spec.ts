import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TiposproductosComponent } from './tiposproductos.component';

describe('TiposproductosComponent', () => {
  let component: TiposproductosComponent;
  let fixture: ComponentFixture<TiposproductosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TiposproductosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TiposproductosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
