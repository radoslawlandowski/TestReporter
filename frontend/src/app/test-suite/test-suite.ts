import { TestCaseResult } from '../test-case/test-case-result/test-case-result'
import { TestCase } from '../test-case/test-case'


export class TestSuite {
    type: string;
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
    testSuites: TestSuite[];
    testCases: TestCase[];
}
