import { TestCaseResult } from '../test-case/test-case-result/test-case-result'
import { TestSuite } from '../test-suite/test-suite'


export class TestRun {
    id: number;
    name: string;
    fullname: string;
    testCaseCount: number;
    result: TestCaseResult;
    time: number;
    total: number;
    passed: number;
    failed: number;
    inconclusive: number;
    skipped: number;
    asserts: number;
    runDate: Date;
    startTime: Date;
    testSuite: TestSuite;
}
