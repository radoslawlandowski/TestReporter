import { Component, Input, OnChanges, SimpleChanges, ChangeDetectionStrategy } from '@angular/core';

import { TestSuite } from './test-suite';
import { TestCaseResult } from '../test-case/test-case-result/test-case-result'

import { ChoiceTrackerService } from '../auxiliary/choice-tracker/choice-tracker.service';

@Component({
  selector: 'test-suite',
  templateUrl: './test-suite.component.html',
  styleUrls: ['./test-suite.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})

export class TestSuiteComponent {
    @Input() testSuite: TestSuite;
    @Input() chosenResult: TestCaseResult;

    constructor(private choiceTrackerService: ChoiceTrackerService) {}

    setCurrentTestCase(testCase): void {
      this.choiceTrackerService.chooseTestCase(testCase);
    }

    testResult = TestCaseResult;
}