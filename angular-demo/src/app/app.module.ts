import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
// import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { CustomFormComponent } from './custom-form/custom-form.component';
import { CoursesComponent } from './courses/courses.component';
import { CustomdirDirective } from './directives/customdir.directive';
import { TemplateDrivenFormComponent } from './template-driven-form/template-driven-form.component';
import { BackendcommComponent } from './backendcomm/backendcomm.component';
// import { CoursesvcService } from './coursesvc.service';

@NgModule({
  declarations: [
    AppComponent,
    CustomFormComponent,
    CoursesComponent,
    CustomdirDirective,
    TemplateDrivenFormComponent,
    BackendcommComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    // NgModule
  ],
  providers: [/*CoursesvcService*/],
  bootstrap: [AppComponent]
})
export class AppModule { }
