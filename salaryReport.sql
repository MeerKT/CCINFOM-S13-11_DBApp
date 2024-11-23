SELECT es.employee_ID, 
    SUM(es.basic_salary + es.raises - es.taxes) AS total_salary_paid, 
    AVG(es.basic_salary + es.raises - es.taxes) AS average_salary,
    YEAR(sd.disbursment_date) AS salary_year
FROM employee_salary es
JOIN salary_disbursement sd ON es.salary_ID = sd.salary_ID 
WHERE YEAR(sd.disbursment_date) = 2024 -- change
GROUP BY es.employee_ID, salary_year 
ORDER BY es.employee_ID , salary_year ASC;
