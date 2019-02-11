import { Component, Input, ChangeDetectionStrategy } from '@angular/core';
import { TestCaseResult } from './test-case-result'; 

@Component({
  selector: 'test-case-result-badge',
  templateUrl: './test-case-result-badge.component.html',
  styleUrls: [],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class TestCaseResultBadgeComponent {
    public static baseClass: string = "badge option ";
    public static resultToCssMap: any = {
      [TestCaseResult.Any]: TestCaseResultBadgeComponent.baseClass + "badge-primary",
      [TestCaseResult.Passed]: TestCaseResultBadgeComponent.baseClass + "badge-success",
      [TestCaseResult.Failed]: TestCaseResultBadgeComponent.baseClass + "badge-danger",
      [TestCaseResult.Inconclusive]: TestCaseResultBadgeComponent.baseClass + "badge-warning",
      [TestCaseResult.Skipped]: TestCaseResultBadgeComponent.baseClass + "badge-info",
    };

    _resultName: string = "";
    resultClass: string;
    fontSize : string;

    @Input()
    set resultName(value: string) {
      this._resultName = value;
      this.resultClass = TestCaseResultBadgeComponent.resultToCssMap[value.toLowerCase()];
    };

    @Input() 
    set fontSizePercentage(value: string) {
      this.fontSize = value + "%";
    }

    constructor() {
        this.resultClass = TestCaseResultBadgeComponent.resultToCssMap[this.resultName];
    }
}