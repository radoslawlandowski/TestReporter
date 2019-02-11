import { Injectable } from '@angular/core';
import { Subject }    from 'rxjs/Subject';

import { TestCase } from '../../test-case/test-case';
import { TestGroup } from '../../test-group/test-group';
import { TestRun } from '../../test-run/test-run';

@Injectable()
export class BrowseDashboardStateService {
    private chosenTestCaseSource = new Subject<TestCase>();
    private chosenTestGroupSource = new Subject<TestGroup>();
    private chosenTestRunSource = new Subject<TestRun>();
    
    chosenTestCase$ = this.chosenTestCaseSource.asObservable();
    chosenTestGroup$ = this.chosenTestGroupSource.asObservable();
    chosenTestRun$ = this.chosenTestRunSource.asObservable();
    
    chooseTestCase(testCase: TestCase) {
        this.chosenTestCaseSource.next(testCase);
    }    

    chooseTestGroup(testGroup: TestGroup) {
        this.chosenTestGroupSource.next(testGroup);
    }

    chooseTestRun(testRun: TestRun) {
        this.chosenTestRunSource.next(testRun);
    }
}