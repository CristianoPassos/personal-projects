INSERT INTO car (id, uuid, date_created, deleted, license_plate, seat_count, convertible, rating, engine_type_id,
                 manufacturer_id, driver_id, version)
values (1, 'adc123b3', now(), false, 'BE BE 1234', 5, true, null, 1, 2, null, 1),
       (2, 'ab1585b3', now(), false, 'RA KL 8136', 5, false, null, 2, 1, null, 1)
;


INSERT INTO driver (id, date_created, deleted, online_status, password, username)
VALUES (9, now(), false, 'ONLINE', 'driver_with_car_pw', 'driver_with_car'),
       (10, now(), false, 'ONLINE', 'driver_without_car_pw', 'driver_without_car');
;

UPDATE car
SET driver_id = 9
WHERE id = 1;