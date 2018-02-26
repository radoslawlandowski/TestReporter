import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';

import { TestCase } from './test-case';
import { TestCaseResult } from './test-case-result/test-case-result';

@Component({
  selector: 'test-case',
  templateUrl: './test-case.component.html',
  styleUrls: ['./test-case.component.css']
})

export class TestCaseComponent implements OnChanges {
    @Input() testCase: TestCase;

    testCaseResult = TestCaseResult

    ngOnChanges(changes: SimpleChanges) {
      this.testCase = changes['testCase'].currentValue;
    }
}