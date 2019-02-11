import { Component, Input } from '@angular/core';
import { TestCaseResult } from './test-case-result'; 

@Component({
  selector: 'test-case-result-badge',
  templateUrl: './test-case-result-badge.component.html',
  styleUrls: []
})
export class TestCaseResultBadgeComponent {
    _resultName: string = "";
    baseClass: string = "badge option ";
    resultToCssMap: Map<string, string>;
    resultClass: string;
    fontSize : string;

    @Input()
    set resultName(value: string) {
      this._resultName = value;
      this.resultClass = this.resultToCssMap.get(value.toLowerCase())
    };

    @Input() 
    set fontSizePercentage(value: string) {
      this.fontSize = value + "%";
    }

    constructor() {
        console.log("TestCaseResultBadgeComponent")

        this.resultToCssMap = new Map<string, string>();

        this.resultToCssMap.set(TestCaseResult.Any, this.baseClass + "badge-primary")
        this.resultToCssMap.set(TestCaseResult.Passed, this.baseClass + "badge option badge-success")
        this.resultToCssMap.set(TestCaseResult.Failed, this.baseClass + "badge option badge-danger")
        this.resultToCssMap.set(TestCaseResult.Inconclusive, this.baseClass + "badge option badge-warning")
        this.resultToCssMap.set(TestCaseResult.Skipped, this.baseClass + "badge option badge-info")
    
        this.resultClass = this.resultToCssMap.get(this.resultName);
    }
}