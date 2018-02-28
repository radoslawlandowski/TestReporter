import { TestRun } from '../test-run/test-run'

export class TestGroup {
    id: number;
    name: string;
    description: string;
    testRuns: TestRun[];

    constructor(myName: string) {
        this.name = myName;
    }
}
