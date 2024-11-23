/*SELECT     e.courseid, COUNT(e.studentid) AS numOfStudents                
FROM       enrollment e                                                      
WHERE      e.term              = ?                                      
AND        e.schoolyear        = ?                                         
GROUP BY   e.term, e.courseid                                             
ORDER BY   e.courseid, e.term;*/


/*SELECT DATE(orderDate) AS date, status, SUM(priceEach * quantityOrdered) AS totalSales " +
                         "FROM orders o " +
                         "JOIN orderdetails od ON o.orderNumber = od.orderNumber " +
                         "WHERE MONTH(orderDate) = ? AND YEAR(orderDate) = ? " +
                         "GROUP BY DATE(orderDate), status";
                         
                         
SELECT p.productName, DAY(o.orderDate) AS day, SUM(od.quantityOrdered * od.priceEach) AS dailySales " +
                    "FROM orderdetails od " +
                    "JOIN orders o ON od.orderNumber = o.orderNumber " +
                    "JOIN products p ON od.productCode = p.productCode " +
                    "WHERE MONTH(o.orderDate) = ? AND YEAR(o.orderDate) = ? " +
                    "GROUP BY p.productName, DAY(o.orderDate) " +
                    "ORDER BY p.productName, DAY(o.orderDate)");*/
                    
SELECT er.employee_ID,  CONCAT(er.first_name, ' ', er.last_name) AS complete_name, 
						SUM(TIMESTAMPDIFF(HOUR, er.check_in_time, er.check_out_time)) AS total_hours_worked,
						SUM(es.overtime_record) AS overtime_hours,
						COUNT(CASE WHEN esc.status_type = 'On Leave' THEN 1 END) AS total_leaves,

FROM employee_records er
JOIN employee_status_change esc ON er.employee_ID = esc.employee_ID
JOIN employee_salary es ON er.employee_ID = es.employee_ID
GROUP BY er.employee_ID, complete_name
ORDER BY er.employee_ID, complete_name ASC;


                    
