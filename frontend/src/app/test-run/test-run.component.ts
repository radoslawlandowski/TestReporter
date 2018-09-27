import 'rxjs/add/operator/switchMap';

import { Component, Input } from '@angular/core';

import { TestRun } from './test-run'
import { TestGroupService } from '../test-group/test-group.service'

@Component({
  selector: 'test-run',
  templateUrl: './test-run.component.html',
  styleUrls: ['./test-run.component.css']
})

export class TestRunComponent {
    @Input() testRun: TestRun;
}