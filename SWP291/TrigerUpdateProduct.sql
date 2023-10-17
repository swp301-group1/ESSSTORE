
go
CREATE TRIGGER UpdateProductQuantityOrder
ON order_details 
AFTER insert
AS
BEGIN
    DECLARE @orderDetail  varchar(255);
    SELECT @orderDetail = order_details.OrderDetailID FROM order_details 
	inner join orders on orders.OrderID = order_details.OrderID where orders.Status !=-1;
    UPDATE products
    set products.Quantity = (products.Quantity - odl.Quantity)
	from products
    INNER JOIN order_details as odl on odl.ProductID = products.ProductID
	where odl.OrderDetailID = @orderDetail 
END;

go


CREATE TRIGGER UpdateProductQuantityOrderCancel
ON orders 
AFTER update
AS
BEGIN
    DECLARE @orderid  varchar(255);
    SELECT @orderid = orders.OrderID FROM orders 
	where orders.Status =-1;
    UPDATE products
    set products.Quantity = (products.Quantity + odl.Quantity)
	from products
    INNER JOIN order_details as odl on odl.ProductID = products.ProductID
	inner join orders as od on od.OrderID = odl.OrderID
	where od.OrderID = @orderid
END;
drop TRIGGER UpdateProductQuantityOrder
