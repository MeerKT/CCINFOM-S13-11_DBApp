SELECT 
    pr.employee_ID, 
    AVG(pr.review_score) AS avg_performance_score,
    SUM(
        CASE 
            WHEN pr.review_score >= 80 THEN 100 
            WHEN  pr.review_score BETWEEN 60 AND 79 THEN 50  
            ELSE 0                             
        END
    ) / COUNT(*) AS promotion_rate, 
    YEAR(pr.review_date) AS review_year 
FROM performance_review pr
WHERE YEAR(pr.review_date) = 2023 
GROUP BY pr.employee_ID, review_year 
ORDER BY pr.employee_ID ASC;

