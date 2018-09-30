import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';

import { BrowseDashboardComponent } from './browse-dashboard/browse-dashboard.component';
import { TestRunComponent } from './test-run/test-run.component';
import { TestSuiteComponent } from './test-suite/test-suite.component';
import { TestCaseComponent } from './test-case/test-case.component';
import { FailureComponent } from './test-case/failure/failure.component';
import { ResultPickerComponent } from './auxiliary/result-picker.component';
import { TestGroupComponent } from './test-group/test-group.component';
import { TestCaseResultBadgeComponent } from './test-case/test-case-result/test-case-result-badge.component';
import { PropertyComponent } from './property/property.component';
import { AddTestrunComponent } from './browse-dashboard/add-testrun/add-testrun.component';
import { AddTestgroupComponent } from './browse-dashboard/add-testgroup/add-testgroup.component';

import { TestGroupService } from './test-group/test-group.service';
import { ChoiceTrackerService } from './auxiliary/choice-tracker/choice-tracker.service';

import { ResultPipe } from './auxiliary/result.pipe';
import { TestGroupListComponent } from './test-group/test-group-list/test-group-list.component'

const appRoutes: Routes = [
  { path: '', redirectTo: '/browse', pathMatch: 'full' },  
  { 
    path: 'browse',
    component: BrowseDashboardComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    BrowseDashboardComponent,
    TestRunComponent,
    TestSuiteComponent,
    TestCaseComponent,
    FailureComponent,
    TestCaseResultBadgeComponent,
    ResultPickerComponent,
    TestGroupComponent,
    PropertyComponent,
    AddTestrunComponent,
    AddTestgroupComponent,
    ResultPipe,
    TestGroupListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    NgbModule.forRoot(),
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: false } // <-- debugging purposes only
    ),
    HttpClientModule
  ],
  providers: [TestGroupService, ChoiceTrackerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
