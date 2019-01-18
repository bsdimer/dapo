import { CapitalizePipe } from './capitalize.pipe';
import { expect, describe } from "@angular/core/testing/src/testing_internal";

describe('CapitalizePipe', () => {
  it('create an instance', () => {
    const pipe = new CapitalizePipe();
    expect(pipe).toBeTruthy();
  });
});
