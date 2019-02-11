import { Component, Input, ChangeDetectionStrategy } from '@angular/core';

import { TestRun } from './test-run'

@Component({
  selector: 'test-run',
  templateUrl: './test-run.component.html',
  styleUrls: ['./test-run.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})

export class TestRunComponent {
    @Input() testRun: TestRun; 
}