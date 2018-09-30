import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject, BehaviorSubject } from 'rxjs';
import { catchError, map, tap, combineLatest, mergeMap, merge, withLatestFrom, distinctUntilChanged, switchMap } from 'rxjs/operators';

import { environment } from '../../environments/environment';

import { TestCase } from '../test-case/test-case';
import { TestCaseResult } from '../test-case/test-case-result/test-case-result';
import { TestSuite } from '../test-suite/test-suite';
import { TestRun } from '../test-run/test-run';
import { TestGroup } from './test-group';
import { of } from 'rxjs/observable/of';

@Injectable()
export class TestGroupService {

    private static TEST_GROUPS_URL = `${environment.apiUrl}/test-groups`;  

    private testGroupsSource: BehaviorSubject<TestGroup[]> = new BehaviorSubject<TestGroup[]>([]);
    private testGroups$ : Observable<TestGroup[]> = this.testGroupsSource.asObservable();

    constructor(private http: HttpClient) {
        this.testGroupsSource.next([]);
    }

    uploadFile(testGroupName: string, file: File) {
        let formData: FormData = this.buildFormData(file);
        let headers: HttpHeaders = this.buildHeadersForFileUpload();

        return this.http.post(`${environment.apiUrl}/test-groups/${testGroupName}/test-runs`, formData, {headers: headers})
            .pipe(switchMap((testRunId: number) => {
                return this.fetchTestRunById(testRunId, testGroupName);
            }), catchError(val => of(`Uploading test run file failed!`)))
    }

    createTestGroup(testGroup: TestGroup) {
        let headers = this.buildBasicHeaders();

        this.http.post(TestGroupService.TEST_GROUPS_URL, testGroup, {headers: headers})
            .pipe(withLatestFrom(this.testGroupsSource)).subscribe(([newGroup, groups]) => {
                let updatedGroups = [...groups as TestGroup[], newGroup as TestGroup]
                this.testGroupsSource.next(updatedGroups);
        })
    }

    fetchTestRunById(id: number, testGroup: string) {
        return this.http.get<TestRun>(`${environment.apiUrl}/test-groups/${testGroup}/test-runs/${id}`).pipe(
            withLatestFrom(this.testGroupsSource),
            tap(this.addTestRunToTestGroups),
            map(([testGroups, testRun]) => { return testRun })
          );
    }

    fetchTestGroups() {
        return this.http.get<TestGroup[]>(TestGroupService.TEST_GROUPS_URL).pipe(
            tap(testGroups => { this.testGroupsSource.next(testGroups) }, 
            map(testGroups => { return testGroups })));
    }

    fetchTestGroup(name: string): Observable<TestGroup> {
        return this.http.get<TestGroup>(`${TestGroupService.TEST_GROUPS_URL}/${name}`);
    }

    getTestGroups() : Observable<TestGroup[]> {
        return this.testGroups$;
    }

    private addTestRunToTestGroups([testRun, testGroups]) {
        let currentTestGroups = [...testGroups];
        currentTestGroups.find(tg => tg.id === testRun.testGroupId).testRuns.push(testRun as TestRun);
        this.testGroupsSource.next(currentTestGroups);
    }

    private buildFormData(file: File): FormData {
        let formData: FormData = new FormData();
        formData.append('file', file, file.name);

        return formData;
    }

    private buildHeadersForFileUpload(): HttpHeaders {
        let headers = new HttpHeaders();
        headers.append('Content-Type', 'application/form-data');        
        headers.append('Accept', 'application/json');

        return headers;
    }

    private buildBasicHeaders(): HttpHeaders {
        let headers = new HttpHeaders();
        headers.append('Content-Type', 'application/json');     
        headers.append('Accept', 'application/json');

        return headers;
    }

    private handleError<T> (operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
          console.error(error); // log to console instead
          console.log(`${operation} failed: ${error.message}`);
          return Observable.of(result as T);
        };
      }
}