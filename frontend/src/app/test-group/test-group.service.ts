import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject, BehaviorSubject } from 'rxjs';
import { catchError, map, tap, combineLatest, mergeMap, merge, withLatestFrom, distinctUntilChanged } from 'rxjs/operators';

import { environment } from '../../environments/environment';

import { TestCase } from '../test-case/test-case';
import { TestCaseResult } from '../test-case/test-case-result/test-case-result';
import { TestSuite } from '../test-suite/test-suite';
import { TestRun } from '../test-run/test-run';
import { TestGroup } from './test-group';

@Injectable()
export class TestGroupService {

    private static TEST_GROUPS_URL = `${environment.apiUrl}/test-groups`;  
    private static TEST_RUN_URL = `${environment.apiUrl}/test-runs`;

    private testGroupsSource: BehaviorSubject<TestGroup[]> = new BehaviorSubject<TestGroup[]>([]);
    private testGroups$ : Observable<TestGroup[]> = this.testGroupsSource.asObservable();

    constructor(private http: HttpClient) {
        this.testGroupsSource.next([]);
    }

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

    public getTestGroups() : Observable<TestGroup[]> {
        return this.testGroups$;
    }

    fetchTestGroups() {
        this.http.get<TestGroup[]>(TestGroupService.TEST_GROUPS_URL).subscribe(testGroups => {
            this.testGroupsSource.next(testGroups);
        });
    }

    getTestGroup(name: string): Observable<TestGroup> {
        return this.http.get<TestGroup>(TestGroupService.TEST_GROUPS_URL + "/" + name);
    }

    getTestRun(name:string, testRunName: string): Promise<TestRun>  {
        return Promise.resolve([].find(group => group.name === name).testRuns.find(testRun => testRun.name === testRunName));
    }

    createTestGroup(testGroup: TestGroup) {
        let headers = new HttpHeaders();
        headers.append('Content-Type', 'application/json');     
        headers.append('Accept', 'application/json');

        this.http.post(TestGroupService.TEST_GROUPS_URL, testGroup, {headers: headers})
            .pipe(withLatestFrom(this.testGroupsSource)).subscribe(([newGroup, groups]) => {
                let updatedGroups = [...groups as TestGroup[], newGroup as TestGroup]
                this.testGroupsSource.next(updatedGroups);
        })
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