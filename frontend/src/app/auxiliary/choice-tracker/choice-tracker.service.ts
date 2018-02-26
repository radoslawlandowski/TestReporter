import { Injectable } from '@angular/core';
import { Subject }    from 'rxjs/Subject';

import { TestCase } from '../../test-case/test-case';

@Injectable()
export class ChoiceTrackerService {
    private chosenTestCaseSource = new Subject<TestCase>();
    
    chosenTestCase$ = this.chosenTestCaseSource.asObservable();
    
    chooseTestCase(testCase: TestCase) {
        this.chosenTestCaseSource.next(testCase);
    }
}