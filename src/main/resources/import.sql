create table if not exists novels
(
    id         bigserial primary key,
    title      varchar(255),
    author_id  integer references authors,
    rating     numeric(10, 2)   default 0.00,
    price      double precision default 0.00,
    created_at timestamp        default CURRENT_TIMESTAMP,
    updated_at timestamp        default CURRENT_TIMESTAMP
);

create table if not exists authors
(
    id      bigserial primary key,
    name    varchar(255),
    surname varchar(255)
);

create table if not exists students
(
    id       bigserial primary key,
    name     varchar(255),
    age      bigint default 18 not null,
    group_id bigint default 1  not null references groups
);

create table if not exists groups
(
    id    bigserial primary key,
    title varchar(255)
);

create table if not exists novel_readers
(
    novel_id   bigint references novels,
    student_id bigint references students
);

insert into authors values          ('Chuck', 'Palahniuk'),
                                    ('Stephen', 'King'),
                                    ('Erich-Maria', 'Remark'),
                                    ('Bernard', 'Werber'),
                                    ('Alexandre', 'Dumas');

insert into novels values           ('Invisible Monsters', 1, 5.78, 12.45),
                                    ('Under the Dome', 2, 4.77, 9.12),
                                    ('Three comrades', 3, 4.77, 10.67),
                                    ('The Thanatonauts', 4, 4.52, 8.44),
                                    ('The Three Musketeerss', 4, 5.59, 12.01);

insert into groups values           ('spirits'),
                                    ('young');

insert into students values         ('Bob', 21, 1),
                                    ('Bill', 20, 2),
                                    ('John', 19, 2),
                                    ('Stasy', 20, 1);

insert into novel_readers values    (1, 1),
                                    (1, 2),
                                    (2, 3),
                                    (3, 4),
                                    (4, 2);