
import { Component, OnInit, OnChanges, SimpleChanges, Input } from '@angular/core';

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { TestGroupService } from '../../test-group/test-group.service'
import { TestGroup } from '../../test-group/test-group'
import { ApiResponse } from '../../auxiliary/model/api-response';

import { environment } from '../../../environments/environment';

@Component({
    selector: 'add-testrun',
    templateUrl: './add-testrun.component.html',
  })
  
export class AddTestrunComponent {

  @Input() testGroup: TestGroup;  

  public uploadResponse: ApiResponse;
  public uploadStatus: String;
  
  constructor(private http: HttpClient, private testGroupService: TestGroupService) {
    this.uploadStatus = "Choose file to upload!";
  }

  fileChange(event) {
    let fileList: FileList = event.target.files;
    if(fileList.length > 0) {
        let file: File = fileList[0];

        this.testGroupService.uploadFile(this.testGroup.name, file).subscribe(status => {
          this.uploadStatus = (status as ApiResponse).message;
        })
        this.uploadStatus = "The file is being uploaded..."        
    }
  }
}