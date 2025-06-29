CREATE TABLE articles
(
    id                                  BIGINT AUTO_INCREMENT NOT NULL,
    car_id                              BIGINT                NOT NULL,
    created_by                          VARCHAR(255)          NULL,
    created_at                          datetime              NULL,
    is_approved                         BIT(1)                NOT NULL,
    proposed_manufacturer               VARCHAR(255)          NULL,
    proposed_model                      VARCHAR(255)          NULL,
    proposed_gear_type                  VARCHAR(255)          NULL,
    proposed_fuel_type                  VARCHAR(255)          NULL,
    proposed_engine_displacement        INT                   NULL,
    proposed_engine_power               INT                   NULL,
    proposed_engine_torque              INT                   NULL,
    proposed_production_start_year      INT                   NULL,
    proposed_production_end_year        INT                   NULL,
    proposed_price                      INT                   NULL,
    proposed_number_of_seats            INT                   NULL,
    proposed_body_type                  VARCHAR(255)          NULL,
    proposed_number_of_doors            INT                   NULL,
    proposed_length                     INT                   NULL,
    proposed_width                      INT                   NULL,
    proposed_height                     INT                   NULL,
    proposed_wheelbase                  INT                   NULL,
    proposed_weight                     INT                   NULL,
    proposed_acceleration_to_hundred    DECIMAL               NULL,
    proposed_top_speed                  INT                   NULL,
    proposed_drive_wheels_configuration VARCHAR(255)          NULL,
    proposed_weight_perhp               DECIMAL               NULL,
    proposed_image_url                  VARCHAR(255)          NULL,
    CONSTRAINT pk_articles PRIMARY KEY (id)
);

CREATE INDEX idx_articles_created_by ON articles (created_by);
CREATE INDEX idx_articles_car_id ON articles (car_id);
CREATE INDEX idx_articles_proposed_model ON articles (proposed_model);