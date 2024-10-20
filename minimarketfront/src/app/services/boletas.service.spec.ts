import { TestBed } from '@angular/core/testing';

import { BoletasService } from './boletas.service';

describe('BoletasService', () => {
  let service: BoletasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BoletasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
