import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Illness } from 'src/app/model/illness.model';
import { Ingredient } from 'src/app/model/ingredient.model';
import { DoctorService } from 'src/app/services/doctor.service';
import { IllnessService } from 'src/app/services/illness.service';

@Component({
  selector: 'app-view-ingredients',
  templateUrl: './view-ingredients.component.html',
  styleUrls: ['./view-ingredients.component.sass']
})
export class ViewIngredientsComponent implements OnInit {

  displayedColumns: string[] = ['name', 'calories', 'waterPercentage', 'proteins', 'carbohydrates', 'sugars', 'fibers', 'fat', 'illnesses'];
  dataSource: Ingredient[] = [];

  allIllnesses: Illness[] = [];
  filterForm: FormGroup;

  constructor(
    private doctorService: DoctorService,
    private illnessService: IllnessService
  ) {
    this.filterForm = new FormGroup({
      illness: new FormControl(''),
    })
  }

  ngOnInit(): void {
    this.doctorService
      .getIngredients().subscribe(
        response => {
          this.dataSource = response;
        },
        error => {
        });
    this.illnessService.findAll()
      .subscribe(
        (response) => {
          this.allIllnesses = response;
          this.allIllnesses.unshift({ "id": -1, "name": "" })
        }
      );
  }

  onFilter(): void {
    const illnessId = this.filterForm.value.illness;
    if (illnessId == -1) {
      this.doctorService
        .getIngredients().subscribe(
          response => {
            this.dataSource = response;
          },
        )
    } else {
      this.doctorService.filterIngredients(illnessId)
        .subscribe(
          (response) => {
            this.dataSource = response;
          }
        );
    }
  }

  formatIllnesses(illnesses: Illness[]) {
    let illnessesString = ""
    illnesses.forEach((element, index) => {
      illnessesString += element.name;
      if (index != illnesses.length - 1) {
        illnessesString += ", ";
      }
    })
    return illnessesString;
  }

}
