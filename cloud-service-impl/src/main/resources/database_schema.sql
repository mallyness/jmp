create table PUBLIC.USERS
(
    ID      INTEGER auto_increment,
    NAME    CHARACTER VARYING not null,
    SURNAME CHARACTER VARYING not null,
    constraint ID
        primary key (ID)
);

create table PUBLIC.CARDS
(
    ID      INTEGER auto_increment,
    NUMBER  CHARACTER VARYING not null,
    USER_ID INTEGER           not null,
    constraint "CARDS_pk"
        primary key (ID),
    constraint USER_ID
        foreign key (USER_ID) references PUBLIC.USERS
);

create table PUBLIC.SUBSCRIPTIONS
(
    ID          INTEGER auto_increment,
    CARD_NUMBER CHARACTER VARYING not null,
    START_DATE  DATETIME          not null,
    constraint "SUBSCRIPTIONS_pk"
        primary key (ID),
    constraint CARD_NUMBER
        foreign key (CARD_NUMBER) references PUBLIC.CARDS
);

