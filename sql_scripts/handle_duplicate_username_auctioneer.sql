USE auctions;
SET SQL_SAFE_UPDATES = 0;

DELETE a1
FROM bidder a1
JOIN (
    SELECT email
    FROM bidder
    GROUP BY email
    HAVING COUNT(*) > 1
) a2 ON a1.email = a2.email;

SELECT username, count(*)
FROM bidder
GROUP BY username
HAVING count(*) >= 2;
