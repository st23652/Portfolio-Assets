/* Find the total quantity of each product sold */
SELECT TotalAmount.ProductID, Products.ProductName, SUM(OrderedQuantity) FROM TotalAmount, Products WHERE TotalAmount.ProductID = Products.ProductID GROUP BY ProductID;

/* Retrieve all customer orders along with the total price */
SELECT Orders.OrderID, Customers.CustomerID, Customers.CustomerName, SUM(TotalAmount.TotalAmount) AS Total FROM Orders JOIN TotalAmount ON Orders.OrderID = TotalAmount.OrderID JOIN Customers ON Orders.CustomerID = Customers.CustomerID GROUP BY Orders.OrderID, Customers.CustomerID, Customers.CustomerName;

/* Find the top 3 customers who spent the most */
SELECT Customers.CustomerID, Customers.CustomerName, SUM(TotalAmount.TotalAmount) AS Total FROM Customers, TotalAmount, Orders WHERE Orders.OrderID = TotalAmount.OrderID AND Orders.CustomerID = Customers.CustomerID GROUP BY Orders.CustomerID ORDER BY Total DESC LIMIT 3;