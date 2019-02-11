import { Component, Input, EventEmitter, Output, ChangeDetectionStrategy } from '@angular/core';

@Component({
  selector: 'selectable-list',
  templateUrl: './selectable-list.component.html',
  styleUrls: ['./selectable-list.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SelectableListComponent {

  private _names: string[] = [];

  @Input()
  set names(names: string[]) {
    this._names = names;
  };

  @Input()
  chosenName: string = "";

  @Output()
  chosenNameChange = new EventEmitter<string>()

  constructor() { }

  isNameHighlighted(name: string): boolean {
    if(this.chosenName !== undefined) {
      return name === this.chosenName
    }
  }

  setName(name: string) : void {
    this.chosenName = name;

    this.chosenNameChange.emit(name);
  }
 
  isListEmpty() : boolean {
    return this._names && this._names.length > 0;
  }

  trackByFn(index, item) {
    return index;
  }
}
