create table if not exists "transactions"
(
    id      uuid  default random_uuid() primary key,
    amount          DECIMAL(15, 4)  NOT NULL,
    `timestamp`     TIMESTAMP       NOT NULL,
    reference       VARCHAR(255)    NOT NULL,
    slogan          VARCHAR(255),
    receiving_user  VARCHAR(255)    NOT NULL
    );