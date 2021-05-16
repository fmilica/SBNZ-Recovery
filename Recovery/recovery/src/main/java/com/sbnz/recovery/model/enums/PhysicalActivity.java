package com.sbnz.recovery.model.enums;

/*
 * Sedentary (little or no exercise) : Calorie-Calculation = BMR x 1.2
 * Lightly active (light exercise/sports 1-3 days/week) : Calorie-Calculation = BMR x 1.375
 * Moderately active (moderate exercise/sports 3-5 days/week) : Calorie-Calculation = BMR x 1.55
 * Very active (hard exercise/sports 6-7 days a week) : Calorie-Calculation = BMR x 1.725
 * Extra active (very hard exercise/sports & a physical job) : Calorie-Calculation = BMR x 1.9
*/

public enum PhysicalActivity {
	SEDENTARY, LIGHT_ACTIVE, MODERATE, VERY_ACTIVE, EXTRA_ACTIVE
}
