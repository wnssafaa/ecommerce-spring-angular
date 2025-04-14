import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListCatrgoriesComponent } from './list-catrgories.component';

describe('ListCatrgoriesComponent', () => {
  let component: ListCatrgoriesComponent;
  let fixture: ComponentFixture<ListCatrgoriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListCatrgoriesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListCatrgoriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
