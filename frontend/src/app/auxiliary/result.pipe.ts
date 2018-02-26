import { Pipe, PipeTransform } from '@angular/core';

import { TestCaseResult } from '../test-case/test-case-result/test-case-result'
import { TestCase } from '../test-case/test-case'

@Pipe({name: 'result'})
export class ResultPipe implements PipeTransform {
  transform(allTestCases: TestCase[], result: TestCaseResult) {
    return result === TestCaseResult.Any ? allTestCases : allTestCases.filter(testCase => testCase.result === result)
  }
}