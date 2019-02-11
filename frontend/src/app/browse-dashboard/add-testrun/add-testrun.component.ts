
import { Component, Input } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { TestGroupService } from '../../test-group/test-group.service'
import { TestGroup } from '../../test-group/test-group'
import { ApiResponse } from '../../auxiliary/model/api-response';

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

        this.testGroupService.uploadFile(this.testGroup.name, file).subscribe((data) => {this.uploadStatus = "File uploaded!"}, (data) => {
          this.uploadStatus = "File upload error!";        
        });

        this.uploadStatus = "The file is being uploaded..."        
    }
  }
}