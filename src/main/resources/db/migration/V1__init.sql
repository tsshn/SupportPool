create table if not exists users
(
    id       int primary key auto_increment,
    login    varchar(50) not null,
    password varchar(50) not null
);

create table if not exists tickets
(
    id            int auto_increment primary key,
    title         varchar(200) not null,
    body_text     varchar(1500) not null,
    state         varchar(15)  not null,
    priority      varchar(15)  not null,
    case_type     varchar(15)  not null,
    creation_date datetime     not null,
    firm          varchar(75)  not null
);

create table if not exists comments
(
    id            int primary key auto_increment,
    ticket        int          not null,
    foreign key (ticket) references tickets (id),
    author        int          not null,
    foreign key (author) references users (id),
    body_text     varchar(300) not null,
    creation_date datetime     not null
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

insert into users (login, password)
values ('illia', 'password'),
       ('mykhailo', 'password'),
       ('james', 'password'),
       ('maria', 'password'),
       ('irene', 'password'),
       ('anna', 'password');

insert into permissions (permission)
values ('VIEW_ADMIN'),
       ('VIEW_SUPPORT'),
       ('VIEW_SALES');

-- FILLING TABLES WITH SAMPLE DATA BELOW

insert into user_to_permissions (user_id, permission_id)
values (1, 1),
       (2, 1),
       (3, 2),
       (4, 2),
       (5, 3),
       (6, 3);

insert into tickets (title, body_text, state, priority, case_type, creation_date, firm)
values ('Importing issue',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Feugiat vivamus at augue eget arcu. Quisque egestas diam in arcu cursus. Posuere urna nec tincidunt praesent semper feugiat nibh sed pulvinar. Rhoncus est pellentesque elit ullamcorper. Vestibulum sed arcu non odio euismod. Vulputate dignissim suspendisse in est ante in nibh. Nisi porta lorem mollis aliquam ut. Tellus in metus vulputate eu scelerisque felis imperdiet. Ac felis donec et odio pellentesque diam volutpat commodo sed. In est ante in nibh mauris cursus.',
        'NEW',
        'HIGH',
        'QUESTION',
        '2020-05-03T12:45:30',
        'Lynx LLC'),
       ('Slow with big JSON',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Feugiat vivamus at augue eget arcu. Quisque egestas diam in arcu cursus. Posuere urna nec tincidunt praesent semper feugiat nibh sed pulvinar. Rhoncus est pellentesque elit ullamcorper. Vestibulum sed arcu non odio euismod. Vulputate dignissim suspendisse in est ante in nibh. Nisi porta lorem mollis aliquam ut. Tellus in metus vulputate eu scelerisque felis imperdiet. Ac felis donec et odio pellentesque diam volutpat commodo sed. In est ante in nibh mauris cursus.',
        'OPEN',
        'NORMAL',
        'QUESTION',
        '2020-05-01T19:01:34',
        'Stardew Limited'),
       ('Reporting problem',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Feugiat vivamus at augue eget arcu. Quisque egestas diam in arcu cursus. Posuere urna nec tincidunt praesent semper feugiat nibh sed pulvinar. Rhoncus est pellentesque elit ullamcorper. Vestibulum sed arcu non odio euismod. Vulputate dignissim suspendisse in est ante in nibh. Nisi porta lorem mollis aliquam ut. Tellus in metus vulputate eu scelerisque felis imperdiet. Ac felis donec et odio pellentesque diam volutpat commodo sed. In est ante in nibh mauris cursus.',
        'NEW',
        'LOW',
        'BUG',
        '2020-04-30T16:25:12',
        'C&B Prod.'),
       ('We want to discuss customization',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Feugiat vivamus at augue eget arcu. Quisque egestas diam in arcu cursus. Posuere urna nec tincidunt praesent semper feugiat nibh sed pulvinar. Rhoncus est pellentesque elit ullamcorper. Vestibulum sed arcu non odio euismod. Vulputate dignissim suspendisse in est ante in nibh. Nisi porta lorem mollis aliquam ut. Tellus in metus vulputate eu scelerisque felis imperdiet. Ac felis donec et odio pellentesque diam volutpat commodo sed. In est ante in nibh mauris cursus.',
        'NEW',
        'HIGH',
        'CALL',
        '2020-04-29T09:12:42',
        'DotNap'),
       ('How about Excel export?',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Feugiat vivamus at augue eget arcu. Quisque egestas diam in arcu cursus. Posuere urna nec tincidunt praesent semper feugiat nibh sed pulvinar. Rhoncus est pellentesque elit ullamcorper. Vestibulum sed arcu non odio euismod. Vulputate dignissim suspendisse in est ante in nibh. Nisi porta lorem mollis aliquam ut. Tellus in metus vulputate eu scelerisque felis imperdiet. Ac felis donec et odio pellentesque diam volutpat commodo sed. In est ante in nibh mauris cursus.',
        'OPEN',
        'NORMAL',
        'FEATURE',
        '2020-05-04T16:02:09',
        'Hexmonster'),
       ('Binary serialization',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Feugiat vivamus at augue eget arcu. Quisque egestas diam in arcu cursus. Posuere urna nec tincidunt praesent semper feugiat nibh sed pulvinar. Rhoncus est pellentesque elit ullamcorper. Vestibulum sed arcu non odio euismod. Vulputate dignissim suspendisse in est ante in nibh. Nisi porta lorem mollis aliquam ut. Tellus in metus vulputate eu scelerisque felis imperdiet. Ac felis donec et odio pellentesque diam volutpat commodo sed. In est ante in nibh mauris cursus.',
        'NEW',
        'HIGH',
        'QUESTION',
        '2020-05-03T12:45:30',
        'Lynx LLC'),
       ('Integrated integration',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Feugiat vivamus at augue eget arcu. Quisque egestas diam in arcu cursus. Posuere urna nec tincidunt praesent semper feugiat nibh sed pulvinar. Rhoncus est pellentesque elit ullamcorper. Vestibulum sed arcu non odio euismod. Vulputate dignissim suspendisse in est ante in nibh. Nisi porta lorem mollis aliquam ut. Tellus in metus vulputate eu scelerisque felis imperdiet. Ac felis donec et odio pellentesque diam volutpat commodo sed. In est ante in nibh mauris cursus.',
        'OPEN',
        'NORMAL',
        'QUESTION',
        '2020-05-01T19:01:34',
        'Stardew Limited'),
       ('Very large data',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Feugiat vivamus at augue eget arcu. Quisque egestas diam in arcu cursus. Posuere urna nec tincidunt praesent semper feugiat nibh sed pulvinar. Rhoncus est pellentesque elit ullamcorper. Vestibulum sed arcu non odio euismod. Vulputate dignissim suspendisse in est ante in nibh. Nisi porta lorem mollis aliquam ut. Tellus in metus vulputate eu scelerisque felis imperdiet. Ac felis donec et odio pellentesque diam volutpat commodo sed. In est ante in nibh mauris cursus.',
        'NEW',
        'LOW',
        'BUG',
        '2020-04-30T16:25:12',
        'C&B Prod.'),
       ('How to connect MSAS',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Feugiat vivamus at augue eget arcu. Quisque egestas diam in arcu cursus. Posuere urna nec tincidunt praesent semper feugiat nibh sed pulvinar. Rhoncus est pellentesque elit ullamcorper. Vestibulum sed arcu non odio euismod. Vulputate dignissim suspendisse in est ante in nibh. Nisi porta lorem mollis aliquam ut. Tellus in metus vulputate eu scelerisque felis imperdiet. Ac felis donec et odio pellentesque diam volutpat commodo sed. In est ante in nibh mauris cursus.',
        'NEW',
        'HIGH',
        'CALL',
        '2020-04-29T09:12:42',
        'DotNap'),
       ('How can we use your sevice?',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Feugiat vivamus at augue eget arcu. Quisque egestas diam in arcu cursus. Posuere urna nec tincidunt praesent semper feugiat nibh sed pulvinar. Rhoncus est pellentesque elit ullamcorper. Vestibulum sed arcu non odio euismod. Vulputate dignissim suspendisse in est ante in nibh. Nisi porta lorem mollis aliquam ut. Tellus in metus vulputate eu scelerisque felis imperdiet. Ac felis donec et odio pellentesque diam volutpat commodo sed. In est ante in nibh mauris cursus.',
        'OPEN',
        'NORMAL',
        'FEATURE',
        '2020-05-04T16:02:09',
        'Hexmonster');

insert into comments (ticket, author, body_text, creation_date)
values (1, 3, 'I think we should just send them the link to our NPM', '2020-05-03T15:14:23'),
       (1, 4, 'Yeah, I think that will do', '2020-05-04T08:01:56'),
       (2, 5, 'To support team: keep it nice with them, they might bring us quite some money soon', '2020-05-02T10:34:01');