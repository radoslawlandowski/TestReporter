import { Component, Input, ChangeDetectionStrategy } from '@angular/core';

import { TestGroup } from './test-group'
import { TestRun } from '../test-run/test-run'

@Component({
  selector: 'test-group',
  templateUrl: './test-group.component.html',
  styleUrls: ['./test-group.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})

export class TestGroupComponent {
    @Input() testGroup: TestGroup;

    chosenTestRun: TestRun;

    setRun(testRun): void {
      this.chosenTestRun = testRun;
    }
}