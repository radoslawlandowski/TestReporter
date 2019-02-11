
import { Component, ChangeDetectionStrategy } from '@angular/core';

import { TestGroupService } from '../../test-group/test-group.service'
import { TestGroup } from '../../test-group/test-group'

@Component({
  selector: 'add-testgroup',
  templateUrl: './add-testgroup.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
  
export class AddTestgroupComponent {

  public groupName: string; 
  
  constructor(private testGroupService: TestGroupService) {}

  create() {
    if (this.groupName && this.groupName.length > 2) {
      this.testGroupService.createTestGroup(new TestGroup(this.groupName))
    }
  }
}