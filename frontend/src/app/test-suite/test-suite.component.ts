import { Component, Input, OnChanges, SimpleChanges, ChangeDetectionStrategy } from '@angular/core';

import { TestSuite } from './test-suite';
import { TestCaseResult } from '../test-case/test-case-result/test-case-result'

import { BrowseDashboardStateService } from '../auxiliary/browse-dashboard-state/browse-dashobard-state.service';

@Component({
  selector: 'test-suite',
  templateUrl: './test-suite.component.html',
  styleUrls: ['./test-suite.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})

export class TestSuiteComponent {
    @Input() testSuite: TestSuite;
    @Input() chosenResult: TestCaseResult;

    constructor(private bds: BrowseDashboardStateService) {}

    setCurrentTestCase(testCase): void {
      this.bds.chooseTestCase(testCase);
    }

    testResult = TestCaseResult;
}