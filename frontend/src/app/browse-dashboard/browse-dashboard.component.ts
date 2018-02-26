import { Component, OnInit, OnChanges, SimpleChanges } from '@angular/core';

import { TestCase } from '../test-case/test-case'

import { TestGroupService } from '../test-group/test-group.service'
import { ChoiceTrackerService } from '../auxiliary/choice-tracker/choice-tracker.service';

@Component({
  selector: 'browse-dashboard',
  templateUrl: './browse-dashboard.component.html',
  styleUrls: ['./browse-dashboard.component.css']
})

export class BrowseDashboardComponent implements OnInit, OnChanges {

  groups; 
  chosenGroup;
  chosenRun;
  chosenTestCase;

  constructor(private testGroupService: TestGroupService, private choiceTrackerService: ChoiceTrackerService) { 

    choiceTrackerService.chosenTestCase$.subscribe(
      testCase => {
        this.chosenTestCase = testCase;
      });
  }

  ngOnInit() {
    var self = this;

    this.testGroupService.getTestGroups().subscribe(value => {
      self.groups = value;
      self.chosenGroup = self.groups[2];
      self.chosenRun = self.chosenGroup.testRuns[0];
    })    
  }

  ngOnChanges(changes: SimpleChanges) {
    
  }

  getRun() : any {
    return 
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

}

