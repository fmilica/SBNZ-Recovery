package com.sbnz.recovery.tests.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.sbnz.recovery.tests.BmrAndRegularDailyCaloriIntakeTest;
import com.sbnz.recovery.tests.ClassifyTherapyTest;
import com.sbnz.recovery.tests.DailyCalorieIntakeAfterInjury;
import com.sbnz.recovery.tests.FailedLoginTest;
import com.sbnz.recovery.tests.MealCEPTest;
import com.sbnz.recovery.tests.MealTest;
import com.sbnz.recovery.tests.NewActivityLevelTest;
import com.sbnz.recovery.tests.NewUpdateInjuryTest;
import com.sbnz.recovery.tests.RankMealTest;
import com.sbnz.recovery.tests.RankTherapyTest;
import com.sbnz.recovery.tests.ReportsTest;
import com.sbnz.recovery.tests.TemplateGenderIntervalTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	BmrAndRegularDailyCaloriIntakeTest.class, 
	ClassifyTherapyTest.class,
	DailyCalorieIntakeAfterInjury.class,
	FailedLoginTest.class,
	MealCEPTest.class,
	MealTest.class,
	NewActivityLevelTest.class,
	NewUpdateInjuryTest.class,
	RankMealTest.class,
	RankTherapyTest.class,
	ReportsTest.class,
	TemplateGenderIntervalTest.class
})
public class AllTestsSuite {

}
