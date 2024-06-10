import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PayeFormComponent } from './paye-form.component';

describe('PayeFormComponent', () => {
  let component: PayeFormComponent;
  let fixture: ComponentFixture<PayeFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PayeFormComponent]
    });
    fixture = TestBed.createComponent(PayeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
