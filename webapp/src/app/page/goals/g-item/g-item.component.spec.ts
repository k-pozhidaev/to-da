import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GItemComponent } from './g-item.component';

describe('GItemComponent', () => {
  let component: GItemComponent;
  let fixture: ComponentFixture<GItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
