import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { environment } from '../../environments/environment';

import { TestCase } from '../test-case/test-case';
import { TestCaseResult } from '../test-case/test-case-result/test-case-result';
import { TestSuite } from '../test-suite/test-suite';
import { TestRun } from '../test-run/test-run';
import { TestGroup } from './test-group';

@Injectable()
export class TestGroupService {

    private testGroupsUrl = `${environment.apiUrl}/test-groups`;  // URL to web api
    private testRunUrl = `${environment.apiUrl}/test-runs`;  // URL to web api
    
    constructor(private http: HttpClient) {}

    uploadFile(testGroup: String, file: File) {
        let formData:FormData = new FormData();
        formData.append('file', file, file.name);
        let headers = new HttpHeaders();
        headers.append('Content-Type', 'application/form-data');        
        headers.append('Accept', 'application/json');

        return this.http.post(`${environment.apiUrl}/test-groups/${testGroup}/test-runs`, formData, {headers: headers}).pipe(
            catchError(this.handleError('getTestGroups', []))
          )
    }

    getTestGroups(): Observable<TestGroup[]> {
        return this.http.get<TestGroup[]>(this.testGroupsUrl).pipe(
            catchError(this.handleError('getTestGroups', []))
          );
    }

    getTestGroup(name: string): Observable<TestGroup> {
        return this.http.get<TestGroup>(this.testGroupsUrl + "/" + name);
    }

    getTestRun(name:string, testRunName: string): Promise<TestRun>  {
        return Promise.resolve([].find(group => group.name === name).testRuns.find(testRun => testRun.name === testRunName));
    }

    createTestGroup(testGroup: TestGroup) {
        let headers = new HttpHeaders();
        headers.append('Content-Type', 'application/json');     
        headers.append('Accept', 'application/json');
        
        return this.http.post(this.testGroupsUrl, testGroup, {headers: headers}).pipe(
            catchError(this.handleError('createTestGroup', []))
        )
    }

    private handleError<T> (operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
       
          // TODO: send the error to remote logging infrastructure
          console.error(error); // log to console instead
       
          // TODO: better job of transforming error for user consumption
          console.log(`${operation} failed: ${error.message}`);
       
          // Let the app keep running by returning an empty result.
          return Observable.of(result as T);
        };
      }
}