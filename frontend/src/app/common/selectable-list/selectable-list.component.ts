import { Component, Input, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'selectable-list',
  templateUrl: './selectable-list.component.html',
  styleUrls: ['./selectable-list.component.css']
})
export class SelectableListComponent {

  private chosenName: string = "";
  private _names: string[] = [];

  @Input()
  set names(names: string[]) {
    this._names = names;
    this.chosenName = names[0];
  };

  @Output()
  public chooseName = new EventEmitter<string>()

  constructor() { }

  isNameHighlighted(name: string): boolean {
    if(this.chosenName !== undefined) {
      return name === this.chosenName
    }
  }

  setName(name: string) : void {
    this.chosenName = name;

    this.chooseName.emit(name);
  }
 
  isListEmpty() : boolean {
    return this._names && this._names.length > 0;
  }
}
