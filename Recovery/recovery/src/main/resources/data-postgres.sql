insert into doctor (username, password, name, surname)
values ('doctor', 'doctor', 'Neko', 'Nekic');

insert into patient (username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values ('patient1', 'patient1', 'Jovo', 'Jovic', 1, '1998-10-10', 172, 68, 1, 0.0, 0.0, 0, 0.0);
insert into patient (username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values ('patient2', 'patient2', 'Ivan', 'Ivanic', 1, '1998-10-10', 172, 68, 1, 1484.0, 2040.5, 0, 0.0);
insert into patient (username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values ('patient3', 'patient3', 'Igor', 'Igic', 1, '1998-10-10', 172, 68, 4, 1484.0, 2040.5, 0, 0.0);
insert into patient (username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values ('patient4', 'patient4', 'Marko', 'Markovic', 1, '1998-10-10', 172, 68, 3, 1484.0, 2040.5, 0, 0.0);
insert into patient (username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values ('patient5', 'patient5', 'Ema', 'Emic', 0, '1998-10-10', 172, 68, 1, 0.0, 0.0, 0, 0.0);
insert into patient (username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values ('patient6', 'patient6', 'Lena', 'Lenic', 0, '2003-10-10', 172, 68, 1, 0.0, 0.0, 0, 0.0);
insert into patient (username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values ('patient7', 'patient7', 'Marina', 'Marinic', 0, '1968-10-10', 172, 68, 1, 0.0, 0.0, 0, 0.0);
insert into patient (username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values ('patient8', 'patient8', 'Zeva', 'Zevic', 1, '1998-10-10', 172, 68, 0, 0.0, 0.0, 0, 0.0);

insert into therapy (name, therapy_type, maximum_monthly_application, temperature, intensity)
values ('Terapija', 1, 20, 25.0, 3);

insert into meal(name, meal_description, total_calories)
values ('Apple soup', 'Low cooking', 470.00);

insert into ingredient (name, calories, carbohydrates, sugars, fat)
values ('ingredient1', 100.00, 50.00, 30.00, 5.00);
insert into ingredient (name, calories, carbohydrates, sugars, fat)
values ('ingredient2', 90.00, 200.00, 30.00, 5.00);
insert into ingredient (name, calories, carbohydrates, sugars, fat)
values ('ingredient2', 20.00, 200.00, 70.00, 5.00);

insert into injury_type(name)
values ('MUSCLE_STRAIN');
insert into injury_type(name)
values ('INTERNAL');
insert into injury_type(name)
values ('FRACTURE');

insert into injury(name, start_date, end_date, description, injury_type_id, patient_id, injury_body_parts)
values ('Povreda bubrega', '2021-05-25', null, 'opis', 2, 5, 7);
insert into injury(name, start_date, end_date, description, injury_type_id, patient_id, injury_body_parts)
values ('Povreda jetre', '2021-05-25', null, 'opis', 2, 5, 6);
insert into injury(name, start_date, end_date, description, injury_type_id, patient_id, injury_body_parts)
values ('Istegnuce ruke', '2021-05-25', null, 'opis', 1, 6, 0);
insert into injury(name, start_date, end_date, description, injury_type_id, patient_id, injury_body_parts)
values ('Istegnuce ruke', '2021-05-25', null, 'opis', 1, 5, 0);
insert into injury(name, start_date, end_date, description, injury_type_id, patient_id, injury_body_parts)
values ('Istegnuce ruke', '2021-05-25', null, 'opis', 1, 7, 0);
insert into injury(name, start_date, end_date, description, injury_type_id, patient_id, injury_body_parts)
values ('Prelom noge', '2021-05-25', null, 'opis', 3, 5, 2);
insert into injury(name, start_date, end_date, description, injury_type_id, patient_id, injury_body_parts)
values ('Prelom ruke', '2021-05-25', null, 'opis', 3, 5, 0);
insert into injury(name, start_date, end_date, description, injury_type_id, patient_id, injury_body_parts)
values ('Prelom ruke', '2021-05-25', null, 'opis', 3, 7, 0);

insert into illness(name)
values ('DIABETES');
insert into illness(name)
values ('HIGH_BLOOD_PRESSURE');
insert into illness(name)
values ('LOW_BLOOD_PRESSURE');

insert into ingredient_amount(ingredient_id, amount)
values (1, 200.00);
insert into ingredient_amount(ingredient_id, amount)
values (2, 300.00);