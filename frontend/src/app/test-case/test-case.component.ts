import { Component, Input } from '@angular/core';

import { TestCase } from './test-case';

@Component({
  selector: 'test-case',
  templateUrl: './test-case.component.html',
  styleUrls: ['./test-case.component.css']
})

export class TestCaseComponent {
    @Input() testCase: TestCase;
} 