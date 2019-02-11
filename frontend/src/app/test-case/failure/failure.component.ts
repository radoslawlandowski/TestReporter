import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { Failure } from './failure'

@Component({
  selector: 'failure',
  templateUrl: './failure.component.html',
  styleUrls: ['./failure.component.css']
})

export class FailureComponent {
    @Input() failure: Failure;
}