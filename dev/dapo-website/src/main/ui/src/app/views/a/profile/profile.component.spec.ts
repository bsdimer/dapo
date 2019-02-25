import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TokenSetComponent } from './token-set.component';

describe('ProfileComponent', () => {
  let component: TokenSetComponent;
  let fixture: ComponentFixture<TokenSetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TokenSetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TokenSetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
