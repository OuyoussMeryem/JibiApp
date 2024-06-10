import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacturesnonpayeComponent } from './facturesnonpaye.component';

describe('FacturesnonpayeComponent', () => {
  let component: FacturesnonpayeComponent;
  let fixture: ComponentFixture<FacturesnonpayeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FacturesnonpayeComponent]
    });
    fixture = TestBed.createComponent(FacturesnonpayeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
