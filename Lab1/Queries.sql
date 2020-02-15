select first_name, last_name, salary
from employees natural join salaries
where salary > 90000 and to_date = '9999-01-01';

select first_name, last_name, dept_name
from (employees natural join current_dept_emp) natural join departments
where dept_no = 'd009' or dept_no = 'd008';

select first_name, last_name, title
from employees natural join titles
where title = 'Technique Leader' and gender = 'F';

select first_name, last_name, salary
from (employees natural join titles) join salaries using(emp_no, to_date)
where to_date = '9999-01-01' and (not title = 'Senior Engineer')
order by salary;

select first_name, last_name, birth_date
from employees
order by birth_date desc;

select first_name, last_name
from employees natural join dept_manager
where to_date = '9999-01-01';

select first_name, last_name, dept_name
from (employees natural join current_dept_emp) natural join departments join salaries using(emp_no, to_date)
where to_date = '9999-01-01'
having max(salary);
