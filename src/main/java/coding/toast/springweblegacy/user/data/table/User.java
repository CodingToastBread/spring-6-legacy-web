package coding.toast.springweblegacy.user.data.table;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "USERS")
public record User(@Id Long id, String name, String phoneNumber) {
}

/*
-- need a table like below

create table users
(
    id           serial
        constraint user_pk
            primary key,
    name         varchar(50) not null,
    phone_number varchar(50)
);

-- sample data
insert into users (name, phone_number)
values ('Daily Code', '010-0000-0000'),
       ('Coding Toast', '010-1111-1111'),
       ('Working Toast', '010-2222-2222'),
       ('Sleeping Toast', '010-4444-4444');


 */
