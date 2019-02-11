import { Component } from '@angular/core';

import { TestCase } from '../test-case/test-case'

import { TestGroupService } from '../test-group/test-group.service'
import { ChoiceTrackerService } from '../auxiliary/choice-tracker/choice-tracker.service';
import { TestGroup } from '../test-group/test-group';
import { TestRun } from '../test-run/test-run';

@Component({
  selector: 'browse-dashboard',
  templateUrl: './browse-dashboard.component.html',
  styleUrls: ['./browse-dashboard.component.css']
})

export class BrowseDashboardComponent {

  groups: TestGroup[] = [];
  groupNames: string[] = [];
  testRunNames: string[] = [];
  chosenGroup: TestGroup;
  chosenRun: TestRun;
  chosenTestCase: TestCase;

  constructor(private testGroupService: TestGroupService,
      private choiceTrackerService: ChoiceTrackerService) { 
    choiceTrackerService.chosenTestCase$.subscribe(testCase => { this.chosenTestCase = testCase});
    
    this.testGroupService.testGroups$.subscribe(testGroups => {
      this.groups = testGroups;
      this.groupNames = this.getTestGroupNames(this.groups);
      this.chooseTestGroupEvent(this.chosenGroup !== undefined ? this.chosenGroup.name : "");
    })

    this.testGroupService.fetchTestGroups().subscribe();
  }

  getTestGroupNames(groups: TestGroup[]) {
    return groups !== undefined ? groups.map(g => g.name) : [];
  }

  getTestRunNames(group: TestGroup) {
    return group !== undefined ? group.testRuns.map(tr => tr.name) : [];
  }

  chooseTestGroupEvent(testGroupName: string) {
    this.chosenGroup = this.groups.find(g => g.name === testGroupName);
    this.testRunNames = this.getTestRunNames(this.chosenGroup)
  }
 
  chooseTestRunEvent(testRunName: string) {
    this.chosenRun = this.chosenGroup.testRuns.find(tr => tr.name === testRunName);
  }
}

