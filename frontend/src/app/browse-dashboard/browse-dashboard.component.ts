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
  chosenGroup: TestGroup = {id: 1, name: ''};
  chosenRun: TestRun;
  chosenTestCase: TestCase;

  constructor(private testGroupService: TestGroupService, private choiceTrackerService: ChoiceTrackerService) { 
    choiceTrackerService.chosenTestCase$.subscribe(testCase => {
        this.chosenTestCase = testCase;
      });
    
    this.testGroupService.getTestGroups().subscribe(testGroups => {
      this.groups = testGroups;
    })

    this.testGroupService.fetchTestGroups().subscribe();
  }

  hasGroups() : boolean {
    return this.groups && this.groups.length > 0;
  }

  hasTestRuns() : boolean {
    return this.chosenGroup && this.chosenGroup.testRuns && this.chosenGroup.testRuns.length > 0;
  }

  getGroups() : any {
    return this.groups;
  }

  getChosenGroup() : any {
    return this.chosenGroup;
  }

  setChosenRun(chosenRun) : void {
    this.chosenRun = chosenRun;
  }

  setChosenGroup(chosenGroup) : void {
    this.chosenGroup = chosenGroup;
  }

  setGroups(groups) : void {
    this.groups = groups;
  }

  setGroup(group) : void {
    this.chosenGroup = group;
  }

  setRun(run) : void {
    this.chosenRun = run;
  }

  isGroupHighlighted(group): boolean {
    if(this.chosenGroup !== undefined) {
      return group.id == this.chosenGroup.id
    }
  }

  isRunHighlighted(run): boolean {
    if(this.chosenRun !== undefined) {
      return run.id == this.chosenRun.id    
    }
  }
}

