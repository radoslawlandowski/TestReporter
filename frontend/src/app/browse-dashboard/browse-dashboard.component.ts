import { Component, OnInit, SimpleChanges } from '@angular/core';

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
  chosenGroup: TestGroup = {id: 1, name: ''};
  chosenRun: TestRun;
  chosenTestCase: TestCase;

  constructor(private testGroupService: TestGroupService, private choiceTrackerService: ChoiceTrackerService) { 
    choiceTrackerService.chosenTestCase$.subscribe(testCase => {
        this.chosenTestCase = testCase;
      });
    
    this.testGroupService.getTestGroups().subscribe(testGroups => {
      console.log(testGroups);
      this.groups = testGroups;
      this.groupNames = this.getTestGroupNames(this.groups);
    })

    this.testGroupService.fetchTestGroups().subscribe();
  }

  getTestGroupNames(groups: TestGroup[]) {
    return groups !== undefined ? groups.map(g => g.name) : [];
  }

  chooseTestGroupEvent(testGroupName: string) {
    this.chosenGroup = this.groups.find(g => g.name === testGroupName);
  }

  hasTestRuns() : boolean {
    return this.chosenGroup && this.chosenGroup.testRuns && this.chosenGroup.testRuns.length > 0;
  }

  setRun(run) : void {
    this.chosenRun = run;
  }

  isRunHighlighted(run): boolean {
    if(this.chosenRun !== undefined) {
      return run.id == this.chosenRun.id    
    }
  }
}

