import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessInfComponent } from './business-inf.component';

describe('BusinessInfComponent', () => {
  let component: BusinessInfComponent;
  let fixture: ComponentFixture<BusinessInfComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BusinessInfComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BusinessInfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
