import { Component, OnInit } from '@angular/core';
import { Ingredient } from 'src/app/model/ingredient.model';
import { DoctorService } from 'src/app/services/doctor.service';

@Component({
  selector: 'app-view-ingredients',
  templateUrl: './view-ingredients.component.html',
  styleUrls: ['./view-ingredients.component.sass']
})
export class ViewIngredientsComponent implements OnInit {

  displayedColumns: string[] = ['name', 'calories', 'waterPercentage', 'proteins', 'carbohydrates', 'sugars', 'fibers', 'fat'];
  dataSource: Ingredient[] = [];

  constructor(
    private doctorService: DoctorService
  ) { }

  ngOnInit(): void {
    this.doctorService
      .getIngredients().subscribe(
        response => {
          this.dataSource = response;
        },
        error => {
        });
  }

}
