import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmepayeComponent } from './confirmepaye.component';

describe('ConfirmepayeComponent', () => {
  let component: ConfirmepayeComponent;
  let fixture: ComponentFixture<ConfirmepayeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConfirmepayeComponent]
    });
    fixture = TestBed.createComponent(ConfirmepayeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
