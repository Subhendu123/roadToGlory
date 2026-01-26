import { Component, EventEmitter, Output } from '@angular/core';
// import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CoursesvcService } from './coursesvc.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {


  appPropBind : number = 0;
  isEnabled = false;
  title = 'angular-demo';
  courseList: string[] = [];
  selection: { showBasics: boolean; showTemplateForm: boolean; showHttpServices : boolean} = {
    showBasics : false,
    showTemplateForm: false,
    showHttpServices : false
  };

  constructor(public courseSvc: CoursesvcService){

  }

  open(cardName : string) {

    console.log("in open: "+cardName);
    if(cardName == 'basic'){
      this.selection.showBasics = true;
      this.selection.showTemplateForm = false;
      this.selection.showHttpServices = false;
    }
    if(cardName == 'template'){
      this.selection.showBasics = false;
      this.selection.showTemplateForm = true;
      this.selection.showHttpServices = false;
    }
    if(cardName == 'http'){
      this.selection.showBasics = false;
      this.selection.showTemplateForm = false;
      this.selection.showHttpServices = true;
    }
  }
    

  generateCourses() {
  let id =   this.courseSvc.getRandom(1,10);
  console.log('It is generating the course with id '+id);
  this.courseList.push('Course '+id);
  }

  deleteCourse(course: string) {
    console.log('In the parent delete course!');
    let index = this.courseList.indexOf(course);
    if(index === -1)
    throw new Error('The Course is incorrect!');

    this.courseList.splice(index,1);
    }

     
  appOnClickEnt() {
    console.log('The on click method is triggered and the event is ', this.appPropBind);
  // throw new Error('Method not implemented.');
  }

}
