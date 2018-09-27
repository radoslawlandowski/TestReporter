import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { Property } from './property';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { OnInit } from '@angular/core/src/metadata/lifecycle_hooks';

import { environment } from '../../environments/environment';

@Component({
  selector: 'property',
  templateUrl: './property.component.html'
})

export class PropertyComponent implements OnInit {
  @Input() property: Property;

  imageToShow: any;  
  downloadStatus: any;

  constructor(private http: HttpClient) {

  }

  ngOnInit() {
    if(this.isImage()) {
        this.getImageFromService(this.property.resultFile_fk)    
    }
  }

  isImage() {
    return this.property.name === "Attachment";
  }

    getImageFromService(imageId: Number) {
      this.downloadStatus = "Downloading the image..."
      return this.getImage(imageId).subscribe(data => {
        this.downloadStatus = "Image donwloaded!"      
        return this.createImageFromBlob(data);
      }, error => {
        console.log(error); 
        this.downloadStatus = "Image download failed!"            
      });
  }
  
  getImage(imageId: Number): Observable<Blob> {
      return this.http.get(`${environment.apiUrl}/attachments?fileId=${imageId}`, { responseType: 'blob' });
  }

  createImageFromBlob(image: Blob) {
      let reader = new FileReader();
      reader.addEventListener("load", () => {
          this.imageToShow = "data:image/png;base64," + reader.result.split(",")[1];
      }, false);

      if (image) {
          reader.readAsDataURL(image);
      }
  }
}