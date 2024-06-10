import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentListComponent } from './agent-list.component';

describe('AgentListComponent', () => {
  let component: AgentListComponent;
  let fixture: ComponentFixture<AgentListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AgentListComponent]
    });
    fixture = TestBed.createComponent(AgentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
