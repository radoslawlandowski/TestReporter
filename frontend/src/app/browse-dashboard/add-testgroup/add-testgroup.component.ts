
import { Component, OnInit, OnChanges, SimpleChanges, Input } from '@angular/core';

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { TestGroupService } from '../../test-group/test-group.service'
import { TestGroup } from '../../test-group/test-group'
import { ApiResponse } from '../../auxiliary/model/api-response';

import { environment } from '../../../environments/environment';

@Component({
    selector: 'add-testgroup',
    templateUrl: './add-testgroup.component.html',
  })
  
export class AddTestgroupComponent {

  public groupName: string;
  
  constructor(private http: HttpClient, private testGroupService: TestGroupService) {
  }

  create() {
    if(this.groupName && this.groupName.length > 2) {
      this.testGroupService.createTestGroup(new TestGroup(this.groupName))
    }
  }
}