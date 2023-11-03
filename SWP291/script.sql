
 
USE [ESS_Store12]
GO
/****** Object:  Table [dbo].[accounts]    Script Date: 11/2/2023 11:43:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[accounts](
	[Email] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[Role] [int] NOT NULL,
	[Status] [int] NULL,
 CONSTRAINT [PK_Accounts] PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[brands]    Script Date: 11/2/2023 11:43:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[brands](
	[BrandID] [int] NOT NULL,
	[BrandName] [nchar](50) NULL,
 CONSTRAINT [PK_brands] PRIMARY KEY CLUSTERED 
(
	[BrandID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[carst]    Script Date: 11/2/2023 11:43:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[carst](
	[CartID] [nvarchar](50) NOT NULL,
	[CustomerID] [int] NOT NULL,
	[ProductID] [varchar](255) NOT NULL,
	[Quantity] [int] NOT NULL,
	[Time] [datetime] NULL,
	[Color] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[CartID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[categories]    Script Date: 11/2/2023 11:43:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[categories](
	[CategoryID] [int] IDENTITY(1,1) NOT NULL,
	[CategoryName] [varchar](255) NULL,
	[Description] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[color]    Script Date: 11/2/2023 11:43:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[color](
	[ProductID] [varchar](255) NOT NULL,
	[ColorID] [varchar](50) NOT NULL,
	[Image] [nvarchar](50) NOT NULL,
	[ColorName] [nchar](50) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customers]    Script Date: 11/2/2023 11:43:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customers](
	[CustomerID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerName] [nvarchar](100) NULL,
	[Address] [nvarchar](255) NULL,
	[PhoneNumber] [nvarchar](20) NULL,
	[Email] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[CustomerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[feedbacks]    Script Date: 11/2/2023 11:43:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[feedbacks](
	[FeedbackID] [int] IDENTITY(1,1) NOT NULL,
	[ProductID] [varchar](255) NOT NULL,
	[CustomerID] [int] NOT NULL,
	[Commen] [nvarchar](100) NULL,
	[Star] [int] NULL,
	[Time] [datetime] NULL,
	[Status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[FeedbackID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_details]    Script Date: 11/2/2023 11:43:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_details](
	[OrderDetailID] [varchar](255) NOT NULL,
	[OrderID] [varchar](255) NULL,
	[ProductID] [varchar](255) NULL,
	[Quantity] [int] NULL,
	[Color] [int] NULL,
	[Price] [decimal](18, 0) NULL,
 CONSTRAINT [PK_order_details] PRIMARY KEY CLUSTERED 
(
	[OrderDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orders]    Script Date: 11/2/2023 11:43:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orders](
	[OrderID] [varchar](255) NOT NULL,
	[CustomerID] [int] NULL,
	[OrderDate] [datetime] NULL,
	[StaffID] [int] NULL,
	[Status] [int] NULL,
	[Address] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[products]    Script Date: 11/2/2023 11:43:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[products](
	[ProductID] [varchar](255) NOT NULL,
	[ProductName] [varchar](255) NULL,
	[Size] [int] NULL,
	[Quantity] [int] NOT NULL,
	[Price] [decimal](18, 0) NOT NULL,
	[Unit] [nvarchar](50) NULL,
	[Contents] [nvarchar](max) NULL,
	[SupplierID] [int] NULL,
	[CategoryID] [int] NULL,
	[BrandID] [int] NULL,
	[DateCreate] [datetime] NULL,
	[Status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[staffs]    Script Date: 11/2/2023 11:43:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[staffs](
	[StaffID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[Email] [nvarchar](50) NULL,
 CONSTRAINT [PK_staffs] PRIMARY KEY CLUSTERED 
(
	[StaffID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[suppliers]    Script Date: 11/2/2023 11:43:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[suppliers](
	[SupplierID] [int] IDENTITY(1,1) NOT NULL,
	[SupplierName] [varchar](255) NULL,
	[Address] [varchar](255) NULL,
	[Phone] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[SupplierID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[categories] ADD  DEFAULT (NULL) FOR [CategoryName]
GO
ALTER TABLE [dbo].[categories] ADD  DEFAULT (NULL) FOR [Description]
GO
ALTER TABLE [dbo].[customers] ADD  DEFAULT (NULL) FOR [CustomerName]
GO
ALTER TABLE [dbo].[customers] ADD  DEFAULT (NULL) FOR [Address]
GO
ALTER TABLE [dbo].[order_details] ADD  DEFAULT (NULL) FOR [OrderID]
GO
ALTER TABLE [dbo].[order_details] ADD  DEFAULT (NULL) FOR [ProductID]
GO
ALTER TABLE [dbo].[order_details] ADD  DEFAULT (NULL) FOR [Quantity]
GO
ALTER TABLE [dbo].[orders] ADD  DEFAULT (NULL) FOR [CustomerID]
GO
ALTER TABLE [dbo].[orders] ADD  DEFAULT (NULL) FOR [OrderDate]
GO
ALTER TABLE [dbo].[products] ADD  DEFAULT (NULL) FOR [ProductName]
GO
ALTER TABLE [dbo].[products] ADD  DEFAULT (NULL) FOR [Unit]
GO
ALTER TABLE [dbo].[products] ADD  DEFAULT (NULL) FOR [SupplierID]
GO
ALTER TABLE [dbo].[products] ADD  DEFAULT (NULL) FOR [CategoryID]
GO
ALTER TABLE [dbo].[suppliers] ADD  DEFAULT (NULL) FOR [SupplierName]
GO
ALTER TABLE [dbo].[suppliers] ADD  DEFAULT (NULL) FOR [Address]
GO
ALTER TABLE [dbo].[suppliers] ADD  DEFAULT (NULL) FOR [Phone]
GO
ALTER TABLE [dbo].[carst]  WITH CHECK ADD  CONSTRAINT [FK_carst_customers] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[customers] ([CustomerID])
GO
ALTER TABLE [dbo].[carst] CHECK CONSTRAINT [FK_carst_customers]
GO
ALTER TABLE [dbo].[carst]  WITH CHECK ADD  CONSTRAINT [FK_carst_products] FOREIGN KEY([ProductID])
REFERENCES [dbo].[products] ([ProductID])
GO
ALTER TABLE [dbo].[carst] CHECK CONSTRAINT [FK_carst_products]
GO
ALTER TABLE [dbo].[color]  WITH CHECK ADD  CONSTRAINT [FK_color_products] FOREIGN KEY([ProductID])
REFERENCES [dbo].[products] ([ProductID])
GO
ALTER TABLE [dbo].[color] CHECK CONSTRAINT [FK_color_products]
GO
ALTER TABLE [dbo].[customers]  WITH CHECK ADD  CONSTRAINT [FK_customers_accounts] FOREIGN KEY([Email])
REFERENCES [dbo].[accounts] ([Email])
GO
ALTER TABLE [dbo].[customers] CHECK CONSTRAINT [FK_customers_accounts]
GO
ALTER TABLE [dbo].[feedbacks]  WITH CHECK ADD  CONSTRAINT [FK_feedbacks_customers] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[customers] ([CustomerID])
GO
ALTER TABLE [dbo].[feedbacks] CHECK CONSTRAINT [FK_feedbacks_customers]
GO
ALTER TABLE [dbo].[feedbacks]  WITH CHECK ADD  CONSTRAINT [FK_feedbacks_products] FOREIGN KEY([ProductID])
REFERENCES [dbo].[products] ([ProductID])
GO
ALTER TABLE [dbo].[feedbacks] CHECK CONSTRAINT [FK_feedbacks_products]
GO
ALTER TABLE [dbo].[order_details]  WITH CHECK ADD FOREIGN KEY([OrderID])
REFERENCES [dbo].[orders] ([OrderID])
GO
ALTER TABLE [dbo].[order_details]  WITH CHECK ADD FOREIGN KEY([ProductID])
REFERENCES [dbo].[products] ([ProductID])
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK_orders_customers] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[customers] ([CustomerID])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK_orders_customers]
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD FOREIGN KEY([CategoryID])
REFERENCES [dbo].[categories] ([CategoryID])
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD FOREIGN KEY([SupplierID])
REFERENCES [dbo].[suppliers] ([SupplierID])
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD  CONSTRAINT [FK_products_brands] FOREIGN KEY([BrandID])
REFERENCES [dbo].[brands] ([BrandID])
GO
ALTER TABLE [dbo].[products] CHECK CONSTRAINT [FK_products_brands]
GO
ALTER TABLE [dbo].[staffs]  WITH CHECK ADD  CONSTRAINT [FK_staffs_accounts] FOREIGN KEY([Email])
REFERENCES [dbo].[accounts] ([Email])
GO
ALTER TABLE [dbo].[staffs] CHECK CONSTRAINT [FK_staffs_accounts]



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