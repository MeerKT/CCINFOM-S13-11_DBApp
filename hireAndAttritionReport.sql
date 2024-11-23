SELECT 
    ht.employee_ID, 
    AVG(ht.event_type = 'Hired') AS avg_hires, 
    SUM(ht.event_type = 'Terminated') AS number_of_terminated, 
    YEAR(er.hire_date) AS hire_year, 
    YEAR(er.termination_date) AS terminate_year, 
    MONTH(er.hire_date) AS hire_month, 
    MONTH(er.termination_date) AS terminate_month 
FROM hiring_termination ht
JOIN employee_records er ON ht.employee_ID = er.employee_ID
WHERE YEAR(er.hire_date) = 2021 AND MONTH(er.hire_date) = 11
    AND YEAR(er.termination_date) = 2024 AND MONTH(er.termination_date) = 10
GROUP BY ht.employee_ID, hire_year, hire_month, terminate_year, terminate_month 
ORDER BY ht.employee_ID ASC; 
