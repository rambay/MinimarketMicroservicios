import { TestBed } from '@angular/core/testing';

import { TiposproductosService } from './tiposproductos.service';

describe('TiposproductosService', () => {
  let service: TiposproductosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TiposproductosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
