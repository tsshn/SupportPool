create table if not exists users
(
    id       int primary key auto_increment,
    login    varchar(50) not null,
    password varchar(50) not null
);

create table if not exists tickets
(
    id                  int auto_increment primary key,
    title               varchar(100) not null,
    responsible_user int not null,
    foreign key (responsible_user) references users(id),
    requester_user int not null,
    foreign key (requester_user) references users(id),
    body_text varchar(500) not null,
    state varchar(15) not null,
    priority varchar(15) not null,
    case_type varchar(15) not null,
    creation_date date not null,
    eta_date date null,
    ns_date date null,
    ns_note varchar(50) null,
    firm varchar(50) not null
);

create table if not exists comments
(
    id int primary key auto_increment,
    ticket int not null,
    foreign key (ticket) references tickets(id),
    author int not null,
    foreign key (author) references users(id),
    body_text varchar(300) not null,
    creation_date date not null
);

create table if not exists permissions
(
    id         int primary key auto_increment,
    permission varchar(50) not null
);

create table if not exists user_to_permissions
(
    user_id       int not null,
    permission_id int not null,
    constraint fk_user_to_permission_user foreign key (user_id) references users (id),
    constraint fk_user_to_permission_permission foreign key (permission_id) references permissions (id)
);

/*create table responsible_user_to_tickets
(
    user_id int not null,
    ticket_id int not null,
    constraint fk_responsible_user_to_tickets_user foreign key (user_id) references users (id),
    constraint fk_responsible_user_to_tickets_ticket foreign key (ticket_id) references tickets (id)
);

create table requester_user_to_tickets
(
    user_id int not null,
    ticket_id int not null,
    constraint fk_requester_user_to_tickets_user foreign key (user_id) references users (id),
    constraint fk_requester_user_to_tickets_ticket foreign key (ticket_id) references tickets (id)
);*/

insert into users (login, password)
values ('admin', 'password'),
       ('support', 'password'),
       ('sales', 'password');

insert into permissions (permission)
values ('VIEW_ADMIN'),
       ('VIEW_SUPPORT'),
       ('VIEW_SALES');

-- FILLING TABLES WITH SAMPLE DATA BELOW

insert into user_to_permissions (user_id, permission_id)
values ((
            select id
            from users
            where login = 'admin'
        ),
        (
            select id
            from permissions
            where permission = 'VIEW_ADMIN'
        )),
       ((
            select id
            from users
            where login = 'support'
        ),
        (
            select id
            from permissions
            where permission = 'VIEW_SUPPORT'
        )),
       ((
            select id
            from users
            where login = 'sales'
        ),
        (
            select id
            from permissions
            where permission = 'VIEW_SALES'
        ));