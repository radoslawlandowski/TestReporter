import 'rxjs/add/operator/switchMap';

import { Component, Input, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location }                 from '@angular/common';

import { TestRun } from './test-run'
import { TestGroupService } from '../test-group/test-group.service'

@Component({
  selector: 'test-run',
  templateUrl: './test-run.component.html',
  styleUrls: ['./test-run.component.css']
})

export class TestRunComponent implements OnChanges {
    @Input() testRun: TestRun;

    ngOnChanges(changes: SimpleChanges) {
      this.testRun = changes['testRun'].currentValue;
    }
}