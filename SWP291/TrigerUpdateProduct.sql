
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

CREATE TRIGGER before_product_delete
ON products
after delete as
BEGIN 
    DECLARE @orderid varchar(255);
    SELECT @orderid = order_details.OrderID FROM order_details inner join products on products.ProductID = order_details.ProductID;
	Delete order_details where order_details.OrderID = @orderid;
	Delete orders where orders.OrderID = @orderid;
END;

declare @aid int
declare @oid varchar(255)
declare @role int
set @aid = 11
IF  EXISTS (SELECT AID FROM accounts WHERE AID=@aid and [Role] <=2)
BEGIN
select  @oid = OrderID from orders where AID = @aid
delete from order_details where OrderID = @oid
delete from orders where OrderID = @oid
delete from carts where AID = @aid
delete from accounts where AID = @aid
END
