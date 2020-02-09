select first_name, last_name, salary
from employees natural join salaries
where salary > 90000 and to_date = '9999-01-01';
