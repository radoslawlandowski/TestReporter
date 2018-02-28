import { Component, Input, OnInit, SimpleChanges  } from '@angular/core';

import { TestCase } from '../test-case';
import { TestCaseResult } from './test-case-result';

@Component({
  selector: 'test-case-result-badge',
  templateUrl: './test-case-result-badge.component.html',
  styleUrls: ['./test-case-result-badge.component.css']
})

export class TestCaseResultBadgeComponent {
    @Input() resultName : string;
    @Input() fontSizePercentage: string;

    resultToCssMap;
    resultClass;
    fontSize;

    constructor() {
        this.resultToCssMap = new Map<string, string>();
        
        this.resultToCssMap.set("all", "badge option badge-primary")
        this.resultToCssMap.set("passed", "badge option badge-success")
        this.resultToCssMap.set("failed", "badge option badge-danger")
        this.resultToCssMap.set("inconclusive", "badge option badge-warning")
        this.resultToCssMap.set("skipped", "badge option badge-info")
    
        this.resultClass = this.resultToCssMap.get(this.resultName);
    }

    ngOnInit() {
        this.fontSize = this.fontSizePercentage + "%";
    }

    ngOnChanges(changes: SimpleChanges ) {
        this.resultClass = this.resultToCssMap.get(changes["resultName"].currentValue.toLowerCase())
      }
}