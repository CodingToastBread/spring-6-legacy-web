package coding.toast.springweblegacy.user.data.table;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "users")
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

 */
