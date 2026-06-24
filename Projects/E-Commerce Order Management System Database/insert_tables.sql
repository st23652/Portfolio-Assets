INSERT INTO Customers (CustomerID, CustomerName) VALUES ('C001', 'Alice'), 
('C002', 'David'), 
('C003', 'Pearl'), 
('C004', 'Samantha');

INSERT INTO Orders (OrderID, OrderDate, CustomerID) VALUES ('R101', '12-01-24', 'C001'), 
('R102', '12-01-24', 'C002'),
('R103', '13-01-24', 'C003'),
('R104', '14-01-24', 'C004'),
('R105', '14-01-24', 'C004');

INSERT INTO Products (ProductID, ProductName, Price) VALUES ('P123', 'Laptop', 500),
('P124', 'Laptop Charger', 25),
('P126', 'Printer', 250),
('P127', 'Desktop', 150),
('P128', 'CPU', 75);

INSERT INTO TotalAmount (OrderID, ProductID, OrderedQuantity, TotalAmount) VALUES ('R101', 'P123', 2, 1000),
('R102', 'P124', 1, 25),
('R103', 'P126', 3, 750),
('R104', 'P127', 15, 2250),
('R105', 'P128', 15, 1125);

INSERT INTO Person (PersonID, PersonName) VALUES ('E301', 'John'),
('E305', 'Sam'),
('E306', 'Jessica'),
('E310', 'Robin');

