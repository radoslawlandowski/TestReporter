import { Component, Input } from '@angular/core';

import { TestCaseResult } from '../test-case/test-case-result/test-case-result';

@Component({
  selector: 'result-picker',
  templateUrl: './result-picker.component.html',
  styleUrls: ['./result-picker.component.css']  
})

export class ResultPickerComponent {
    chosenResult = TestCaseResult.Any;

    testResult = TestCaseResult;

    chooseResults(result : TestCaseResult) {
        console.log(result);
        this.chosenResult = result;
    }
}