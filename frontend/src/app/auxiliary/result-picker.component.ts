import { Component, ChangeDetectionStrategy } from '@angular/core';

import { TestCaseResult } from '../test-case/test-case-result/test-case-result';

@Component({
  selector: 'result-picker',
  templateUrl: './result-picker.component.html',
  styleUrls: ['./result-picker.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})

export class ResultPickerComponent {
    chosenResult = TestCaseResult.Any;

    testResult = TestCaseResult;

    chooseResults(result : TestCaseResult) {
        this.chosenResult = result;
    }
}