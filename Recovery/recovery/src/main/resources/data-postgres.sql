insert into authority (name) values ('ROLE_DOCTOR');
insert into authority (name) values ('ROLE_PATIENT');

--password=doctor
insert into doctor (id, username, password, name, surname)
values (1, 'doctor@doctor.com', '$2y$12$jxelH0P3kGx5LyhaaelDbe4621AHciGk2uGOqrbjYo3eAxs625QCy', 'Neko', 'Nekic');
insert into user_authority (user_id, authority_id) values (1, 1);

--password=patient
insert into patient (id, username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values (1, 'patient1@patient.com', '$2y$12$Mz9aZQZcPPdrctescbnAD.ceatxTOuZdXFzxwId9kKRsYBjQQfqAK', 'Jovo', 'Jovic', 1, '1998-10-10', 172, 68, 1, 0.0, 0.0, 0, 0.0);
insert into patient (id, username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values (2, 'patient2@patient.com', '$2y$12$Mz9aZQZcPPdrctescbnAD.ceatxTOuZdXFzxwId9kKRsYBjQQfqAK', 'Ivan', 'Ivanic', 1, '1998-10-10', 172, 68, 1, 1484.0, 2040.5, 0, 0.0);
insert into patient (id, username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values (3, 'patient3@patient.com', '$2y$12$Mz9aZQZcPPdrctescbnAD.ceatxTOuZdXFzxwId9kKRsYBjQQfqAK', 'Igor', 'Igic', 1, '1998-10-10', 172, 68, 4, 1484.0, 2040.5, 0, 0.0);
insert into patient (id, username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values (4, 'patient4@patient.com', '$2y$12$Mz9aZQZcPPdrctescbnAD.ceatxTOuZdXFzxwId9kKRsYBjQQfqAK', 'Marko', 'Markovic', 1, '1998-10-10', 172, 68, 3, 1484.0, 2040.5, 0, 0.0);
insert into patient (id, username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values (5, 'patient5@patient.com', '$2y$12$Mz9aZQZcPPdrctescbnAD.ceatxTOuZdXFzxwId9kKRsYBjQQfqAK', 'Ema', 'Emic', 0, '1998-10-10', 172, 68, 1, 0.0, 0.0, 0, 0.0);
insert into patient (id, username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values (6, 'patient6@patient.com', '$2y$12$Mz9aZQZcPPdrctescbnAD.ceatxTOuZdXFzxwId9kKRsYBjQQfqAK', 'Lena', 'Lenic', 0, '2003-10-10', 172, 68, 1, 0.0, 0.0, 0, 0.0);
insert into patient (id, username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values (7, 'patient7@patient.com', '$2y$12$Mz9aZQZcPPdrctescbnAD.ceatxTOuZdXFzxwId9kKRsYBjQQfqAK', 'Marina', 'Marinic', 0, '1968-10-10', 172, 68, 1, 0.0, 0.0, 0, 0.0);
insert into patient (id, username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values (8, 'patient8@patient.com', '$2y$12$Mz9aZQZcPPdrctescbnAD.ceatxTOuZdXFzxwId9kKRsYBjQQfqAK', 'Zeva', 'Zevic', 1, '1998-10-10', 172, 68, 0, 0.0, 0.0, 0, 0.0);

insert into user_authority (user_id, authority_id) values (1, 2);
insert into user_authority (user_id, authority_id) values (2, 2);
insert into user_authority (user_id, authority_id) values (3, 2);
insert into user_authority (user_id, authority_id) values (4, 2);
insert into user_authority (user_id, authority_id) values (5, 2);
insert into user_authority (user_id, authority_id) values (6, 2);
insert into user_authority (user_id, authority_id) values (7, 2);
insert into user_authority (user_id, authority_id) values (8, 2);

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