DELIMITER $$

create trigger add_author after insert on Author
    for each row begin
    insert into Audit values (null,'Author', 'INSERT', NOW());
end;
$$
DELIMITER ;


DELIMITER $$

create trigger add_Book_to_Shelf after insert on StoredOn
    for each row begin
    insert into Audit values (null,'StoredOn', 'INSERT', NOW());
end;
$$
DELIMITER ;


DELIMITER $$

create trigger delete_Book_from_shelf after delete on StoredOn
    for each row begin
    insert into Audit values (null,'StoredOn', 'DELETE', NOW());
end;
$$
DELIMITER ;

DELIMITER $$

create trigger mod_num_copies after update on StoredOn
    for each row begin

    if NEW.TotalCopies <> OLD.TotalCopies
    THEN
        insert into Audit values (null,'StoredOn', 'UPDATE', NOW());
    END IF;
end;
$$
DELIMITER ;