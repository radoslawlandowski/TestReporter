import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestGroupListComponent } from './test-group-list.component';

describe('TestGroupListComponent', () => {
  let component: TestGroupListComponent;
  let fixture: ComponentFixture<TestGroupListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestGroupListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestGroupListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
