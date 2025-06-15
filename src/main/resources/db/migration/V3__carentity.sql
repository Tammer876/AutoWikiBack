CREATE TABLE cars
(
    id                         BIGINT AUTO_INCREMENT NOT NULL,
    manufacturer               VARCHAR(255)          NULL,
    model                      VARCHAR(255)          NULL,
    gear_type                  VARCHAR(255)          NULL,
    fuel_type                  VARCHAR(255)          NULL,
    engine_displacement        INT                   NULL,
    engine_power               INT                   NULL,
    engine_torque              INT                   NULL,
    production_start_year      INT                   NULL,
    production_end_year        INT                   NULL,
    price                      INT                   NULL,
    number_of_seats            INT                   NULL,
    body_type                  VARCHAR(255)          NULL,
    number_of_doors            INT                   NULL,
    length                     INT                   NULL,
    width                      INT                   NULL,
    height                     INT                   NULL,
    wheelbase                  INT                   NULL,
    weight                     INT                   NULL,
    acceleration_to_hundred    DECIMAL               NULL,
    top_speed                  INT                   NULL,
    drive_wheels_configuration VARCHAR(255)          NULL,
    weight_perhp               DECIMAL               NULL,
    image_url                  VARCHAR(255)          NULL,
    CONSTRAINT pk_cars PRIMARY KEY (id)
);

CREATE INDEX idx_cars_manufacturer ON cars (manufacturer);
CREATE INDEX idx_cars_model ON cars (model);