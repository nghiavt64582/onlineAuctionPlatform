DELIMITER $$
CREATE PROCEDURE GetBidderById(IN p_bidder_id INT)
BEGIN
    SELECT * FROM Bidder
    WHERE id = p_bidder_id;
END $$

DELIMITER ;