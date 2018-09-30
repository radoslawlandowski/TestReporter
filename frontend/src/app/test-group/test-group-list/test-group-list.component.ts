import { Component, Input, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'test-group-list',
  templateUrl: './test-group-list.component.html',
  styleUrls: ['./test-group-list-component.css']
})
export class TestGroupListComponent {

  private chosenGroupName: string = "";
  private _testGroupNames: string[] = [];

  @Input()
  set testGroupNames(testGroupNames: string[]) {
    this._testGroupNames = testGroupNames;
    this.chosenGroupName = testGroupNames[0];
  };

  @Output()
  public chooseGroup = new EventEmitter<string>()

  constructor() { }

  isGroupNameHighlighted(groupName): boolean {
    if(this.chosenGroupName !== undefined) {
      return groupName === this.chosenGroupName
    }
  }

  setGroupName(groupName) : void {
    this.chosenGroupName = groupName;

    this.chooseGroup.emit(groupName);
  }
 
  hasGroupNames() : boolean {
    return this._testGroupNames && this._testGroupNames.length > 0;
  }
}
