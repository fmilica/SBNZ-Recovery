insert into authority (name) values ('ROLE_DOCTOR');
insert into authority (name) values ('ROLE_PATIENT');

--password=doctor
insert into doctor (id, username, password, name, surname)
values (1, 'doctor@doctor.com', '$2y$12$jxelH0P3kGx5LyhaaelDbe4621AHciGk2uGOqrbjYo3eAxs625QCy', 'Neko', 'Nekic');
insert into user_authority (user_id, authority_id) values (1, 1);

--password=patient
insert into patient (id, username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values (2, 'patient2@patient.com', '$2y$12$Mz9aZQZcPPdrctescbnAD.ceatxTOuZdXFzxwId9kKRsYBjQQfqAK', 'Jovo', 'Jovic', 0, '1998-10-10', 172, 68, 1, 0.0, 0.0, 0, 1000.0);
insert into patient (id, username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values (3, 'patient3@patient.com', '$2y$12$Mz9aZQZcPPdrctescbnAD.ceatxTOuZdXFzxwId9kKRsYBjQQfqAK', 'Ivan', 'Ivanic', 0, '1998-10-10', 172, 68, 1, 1484.0, 2040.5, 0, 4000.0);
insert into patient (id, username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values (4, 'patient4@patient.com', '$2y$12$Mz9aZQZcPPdrctescbnAD.ceatxTOuZdXFzxwId9kKRsYBjQQfqAK', 'Igor', 'Igic', 0, '1998-10-10', 172, 68, 4, 1484.0, 2040.5, 0, 100.0);
insert into patient (id, username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values (5, 'patient5@patient.com', '$2y$12$Mz9aZQZcPPdrctescbnAD.ceatxTOuZdXFzxwId9kKRsYBjQQfqAK', 'Marko', 'Markovic', 0, '1998-10-10', 172, 68, 3, 1484.0, 2040.5, 0, 2000.0);
insert into patient (id, username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values (6, 'patient6@patient.com', '$2y$12$Mz9aZQZcPPdrctescbnAD.ceatxTOuZdXFzxwId9kKRsYBjQQfqAK', 'Ema', 'Emic', 1, '1998-10-10', 172, 68, 1, 0.0, 0.0, 0, 1500.0);
insert into patient (id, username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values (7, 'patient7@patient.com', '$2y$12$Mz9aZQZcPPdrctescbnAD.ceatxTOuZdXFzxwId9kKRsYBjQQfqAK', 'Lena', 'Lenic', 1, '2003-10-10', 172, 68, 1, 0.0, 0.0, 0, 700.0);
insert into patient (id, username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values (8, 'patient8@patient.com', '$2y$12$Mz9aZQZcPPdrctescbnAD.ceatxTOuZdXFzxwId9kKRsYBjQQfqAK', 'Marina', 'Marinic', 1, '1968-10-10', 172, 68, 1, 0.0, 0.0, 0, 3300.0);
insert into patient (id, username, password, name, surname, gender, date_of_birth, height, weight, physical_activity_before_injury, bmr, regular_daily_calory_intake, physical_activity_after_injury, daily_calory_intake_after_injury)
values (9, 'patient9@patient.com', '$2y$12$Mz9aZQZcPPdrctescbnAD.ceatxTOuZdXFzxwId9kKRsYBjQQfqAK', 'Zeva', 'Zevic', 0, '1998-10-10', 172, 68, 0, 0.0, 0.0, 0, 5000.0);

alter sequence hibernate_sequence restart with 10;

insert into user_authority (user_id, authority_id) values (2, 2);
insert into user_authority (user_id, authority_id) values (3, 2);
insert into user_authority (user_id, authority_id) values (4, 2);
insert into user_authority (user_id, authority_id) values (5, 2);
insert into user_authority (user_id, authority_id) values (6, 2);
insert into user_authority (user_id, authority_id) values (7, 2);
insert into user_authority (user_id, authority_id) values (8, 2);
insert into user_authority (user_id, authority_id) values (9, 2);

insert into meal(name, meal_description, total_calories)
values ('Apple soup', 'Low cooking', 470.00);

insert into ingredient (name, calories, carbohydrates, sugars, fat, fibers, proteins, water_percentage)
values ('ingredient1', 100.00, 50.00, 30.00, 5.00, 20.00, 20.00, 20.00);
insert into ingredient (name, calories, carbohydrates, sugars, fat, fibers, proteins, water_percentage)
values ('ingredient2', 90.00, 20.00, 30.00, 5.00, 20.00, 20.00, 20.00);
insert into ingredient (name, calories, carbohydrates, sugars, fat, fibers, proteins, water_percentage)
values ('ingredient3', 20.00, 20.00, 45.00, 5.00, 20.00, 20.00, 20.00);

insert into illness(name)
values ('Diabetes');
insert into illness(name)
values ('High blood pressure');
insert into illness(name)
values ('Low blood pressure');

insert into injury_type(name)
values ('Muscle strain');
insert into injury_type(name)
values ('Fracture');
insert into injury_type(name)
values ('Internal');

insert into therapy (name, therapy_type, maximum_monthly_application, temperature, intensity)
values ('Terapija', 1, 20, 25.0, 3);
insert into therapy_illness(therapy_id, illness_id)
values (1, 1);
insert into therapy_illness(therapy_id, illness_id)
values (1, 2);
insert into therapy_injury_type(therapy_id, injury_type_id)
values (1, 1);

-- INJURY REQUIREMENTS
-- muscle strain
insert into injury_requirement(lower_age_boundry, upper_age_boundry, injury_type_id, activity_after_injury) 
values (50, 130, 1, 0);
insert into injury_requirement(lower_age_boundry, upper_age_boundry, injury_type_id, activity_after_injury) 
values (20, 50, 1, 1);
insert into injury_requirement(lower_age_boundry, upper_age_boundry, injury_type_id, activity_after_injury) 
values (0, 20, 1, 2);
-- upper body fracture
insert into injury_requirement(lower_age_boundry, upper_age_boundry, injury_type_id, activity_after_injury) 
values (30, 130, 2, 1);
insert into injury_requirement(lower_age_boundry, upper_age_boundry, injury_type_id, activity_after_injury) 
values (0, 30, 2, 2);
-- lower body fracture
insert into injury_requirement(lower_age_boundry, upper_age_boundry, injury_type_id, activity_after_injury) 
values (0, 130, 2, 0);
-- internal
insert into injury_requirement(lower_age_boundry, upper_age_boundry, injury_type_id, activity_after_injury) 
values (0, 130, 3, 0);
-- INJURY REQUIREMENT BODY PART
-- Muscle strain <-> body part
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (1, 0);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (1, 1);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (1, 2);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (1, 3);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (1, 4);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (1, 5);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (2, 0);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (2, 1);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (2, 2);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (2, 3);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (2, 4);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (2, 5);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (3, 0);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (3, 1);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (3, 2);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (3, 3);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (3, 4);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (3, 5);
-- Upper body fracture <-> body part
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (4, 0);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (4, 1);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (5, 0);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (5, 1);
-- Lower body fracture <-> body part
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (6, 2);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (6, 3);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (6, 4);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (6, 5);
-- Internal <-> body part
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (7, 6);
insert into injury_req_body_part(injury_req_id, injury_body_part) 
values (7, 7);


-- PACIJENT 5 -> povrede i terapije
insert into injury(name, start_date, end_date, description, injury_type_id, patient_id, injury_body_parts)
values ('Povreda bubrega', '2021-05-25', null, 'opis', 2, 5, 7);
insert into injury(name, start_date, end_date, description, injury_type_id, patient_id, injury_body_parts)
values ('Povreda jetre', '2021-05-25', null, 'opis', 2, 5, 6);
insert into injury(name, start_date, end_date, description, injury_type_id, patient_id, injury_body_parts)
values ('Istegnuce ruke', '2021-05-25', null, 'opis', 1, 5, 0);
insert into injury(name, start_date, end_date, description, injury_type_id, patient_id, injury_body_parts)
values ('Prelom noge', '2021-05-25', null, 'opis', 3, 5, 2);
insert into injury(name, start_date, end_date, description, injury_type_id, patient_id, injury_body_parts)
values ('Prelom ruke', '2021-05-25', null, 'opis', 3, 5, 0);
-- primenjena terapija
insert into applied_therapy(application_date, therapy_id, injury_id) 
values ('2021-05-26', 1, 4);
insert into applied_therapy(application_date, therapy_id, injury_id) 
values ('2021-05-26', 1, 5);

insert into injury(name, start_date, end_date, description, injury_type_id, patient_id, injury_body_parts)
values ('Istegnuce ruke', '2021-05-25', null, 'opis', 1, 6, 0);

insert into injury(name, start_date, end_date, description, injury_type_id, patient_id, injury_body_parts)
values ('Istegnuce ruke', '2021-05-25', null, 'opis', 1, 7, 0);
insert into injury(name, start_date, end_date, description, injury_type_id, patient_id, injury_body_parts)
values ('Prelom ruke', '2021-05-25', null, 'opis', 3, 7, 0);

insert into ingredient_amount(ingredient_id, amount)
values (1, 200.00);
insert into ingredient_amount(ingredient_id, amount)
values (2, 300.00);

insert into meal_ingredient(meal_id, ingredient_amount_id)
values (1, 1);
insert into meal_ingredient(meal_id, ingredient_amount_id)
values (1, 2);

insert into illness_ingredient(ingredient_id, illness_id)
values (1, 1);
insert into illness_ingredient(ingredient_id, illness_id)
values (1, 2);
insert into illness_ingredient(ingredient_id, illness_id)
values (2, 1);
insert into illness_ingredient(ingredient_id, illness_id)
values (2, 2);
insert into illness_ingredient(ingredient_id, illness_id)
values (3, 1);
insert into illness_ingredient(ingredient_id, illness_id)
values (3, 2);
insert into illness_ingredient(ingredient_id, illness_id)
values (3, 3);