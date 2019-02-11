import { Component, Input, OnChanges, SimpleChanges, ChangeDetectionStrategy } from '@angular/core';
import { Failure } from './failure'

@Component({
  selector: 'failure',
  templateUrl: './failure.component.html',
  styleUrls: ['./failure.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})

export class FailureComponent {
    @Input() failure: Failure;
}