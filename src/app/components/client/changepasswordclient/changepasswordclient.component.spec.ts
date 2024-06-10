import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangepasswordclientComponent } from './changepasswordclient.component';

describe('ChangepasswordclientComponent', () => {
  let component: ChangepasswordclientComponent;
  let fixture: ComponentFixture<ChangepasswordclientComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChangepasswordclientComponent]
    });
    fixture = TestBed.createComponent(ChangepasswordclientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
