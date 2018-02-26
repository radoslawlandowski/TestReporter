import { TestCaseResult } from './test-case-result/test-case-result';
import { Failure } from './failure/failure';
import { Property } from '../property/property';

export class TestCase {
    id: number;
    name: string;
    fullname: string;
    result: TestCaseResult;
    time: string;
    asserts: number;
    failure: Failure;
    properties: Property[];
}