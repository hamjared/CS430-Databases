select first_name, last_name, salary
from employees natural join salaries
where salary > 90000 and to_date = '9999-01-01';

select first_name, last_name, dept_name
from (employees natural join current_dept_emp) natural join departments
where dept_no = 'd009' or dept_no = 'd008';

select first_name, last_name, title
from employees natural join titles
where title = 'Technique Leader';
