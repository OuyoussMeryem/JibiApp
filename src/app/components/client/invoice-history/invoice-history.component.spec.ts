import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoiceHistoryComponent } from './invoice-history.component';

describe('InvoiceHistoryComponent', () => {
  let component: InvoiceHistoryComponent;
  let fixture: ComponentFixture<InvoiceHistoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InvoiceHistoryComponent]
    });
    fixture = TestBed.createComponent(InvoiceHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
