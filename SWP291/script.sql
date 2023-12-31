USE [ESS_Store15]
GO
/****** Object:  Table [dbo].[accounts]    Script Date: 11/15/2023 9:02:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[accounts](
	[AID] [int] IDENTITY(1,1) NOT NULL,
	[Email] [nvarchar](50) NULL,
	[PhoneNumber] [nvarchar](50) NULL,
	[Password] [nvarchar](50) NULL,
	[Role] [int] NOT NULL,
	[Status] [int] NOT NULL,
	[UserName] [nvarchar](255) NULL,
	[Address] [nvarchar](255) NULL,
	[Picture] [nvarchar](255) NULL,
 CONSTRAINT [PK_accounts] PRIMARY KEY CLUSTERED 
(
	[AID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[brands]    Script Date: 11/15/2023 9:02:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[brands](
	[BrandID] [int] IDENTITY(1,1) NOT NULL,
	[BrandName] [nchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[BrandID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[carts]    Script Date: 11/15/2023 9:02:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[carts](
	[CartID] [nvarchar](50) NOT NULL,
	[AID] [int] NOT NULL,
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
/****** Object:  Table [dbo].[categories]    Script Date: 11/15/2023 9:02:38 PM ******/
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
/****** Object:  Table [dbo].[color]    Script Date: 11/15/2023 9:02:38 PM ******/
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
/****** Object:  Table [dbo].[feedbacks]    Script Date: 11/15/2023 9:02:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[feedbacks](
	[FeedbackID] [int] IDENTITY(1,1) NOT NULL,
	[ProductID] [varchar](255) NOT NULL,
	[AID] [int] NOT NULL,
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
/****** Object:  Table [dbo].[order_details]    Script Date: 11/15/2023 9:02:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_details](
	[OrderDetailID] [varchar](255) NOT NULL,
	[OrderID] [varchar](255) NULL,
	[ProductID] [varchar](255) NULL,
	[Quantity] [int] NULL,
	[Color] [varchar](50) NULL,
	[Price] [decimal](18, 0) NULL,
 CONSTRAINT [PK_order_details] PRIMARY KEY CLUSTERED 
(
	[OrderDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orders]    Script Date: 11/15/2023 9:02:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orders](
	[OrderID] [varchar](255) NOT NULL,
	[AID] [int] NULL,
	[OrderDate] [datetime] NULL,
	[StaffID] [int] NULL,
	[Status] [int] NULL,
	[Address] [varchar](255) NULL,
	[DatePay] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[products]    Script Date: 11/15/2023 9:02:38 PM ******/
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
/****** Object:  Table [dbo].[suppliers]    Script Date: 11/15/2023 9:02:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[suppliers](
	[SupplierID] [int] IDENTITY(1,1) NOT NULL,
	[SupplierName] [varchar](255) NULL,
	[Address] [varchar](255) NULL,
	[Phone] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[SupplierID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[accounts] ON 

INSERT [dbo].[accounts] ([AID], [Email], [PhoneNumber], [Password], [Role], [Status], [UserName], [Address], [Picture]) VALUES (1, N'ngolinh09032002@gmail.com', N'0974841508', N'123456', 3, 1, N'admin', N'sdffwef', NULL)
INSERT [dbo].[accounts] ([AID], [Email], [PhoneNumber], [Password], [Role], [Status], [UserName], [Address], [Picture]) VALUES (2, N'linhndhe163822@fpt.edu.vn', N'12335413', N'123456789', 2, 1, N'ngolinh 30', N'279 pham hong thai', N'null')
INSERT [dbo].[accounts] ([AID], [Email], [PhoneNumber], [Password], [Role], [Status], [UserName], [Address], [Picture]) VALUES (7, N'ngolinh209032002@gmail.com', NULL, N'N2avJBA0Gr07y', 1, 1, N'Ngo Linh', N'23e23e23e', N'https://lh3.googleusercontent.com/a/ACg8ocLpJPsZgHhP8cpTBmzQquIN0Bwxeuf1d5luBOI5UMzeeA=s96-c')
SET IDENTITY_INSERT [dbo].[accounts] OFF
GO
SET IDENTITY_INSERT [dbo].[brands] ON 

INSERT [dbo].[brands] ([BrandID], [BrandName]) VALUES (1, N'apple')
INSERT [dbo].[brands] ([BrandID], [BrandName]) VALUES (2, N'Sony')
INSERT [dbo].[brands] ([BrandID], [BrandName]) VALUES (3, N'Lenovo')
INSERT [dbo].[brands] ([BrandID], [BrandName]) VALUES (4, N'Dell')
INSERT [dbo].[brands] ([BrandID], [BrandName]) VALUES (5, N'Samsung')
SET IDENTITY_INSERT [dbo].[brands] OFF
GO
SET IDENTITY_INSERT [dbo].[categories] ON 

INSERT [dbo].[categories] ([CategoryID], [CategoryName], [Description]) VALUES (1, N'Lap top', NULL)
INSERT [dbo].[categories] ([CategoryID], [CategoryName], [Description]) VALUES (2, N'telephone', N'qwdqwd')
INSERT [dbo].[categories] ([CategoryID], [CategoryName], [Description]) VALUES (3, N'Earphone', N'abc')
SET IDENTITY_INSERT [dbo].[categories] OFF
GO
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231110T105901853530900', N'#000000', N'p20231110T105901853530900_000000.webp', N'black                                             ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231110T123128588879400', N'#000000', N'p20231110T123128588879400_000000.webp', N'Black                                             ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231110T123345964133200', N'#000000', N'p20231110T123345964133200_000000.webp', N'Black                                             ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231110T123345964133200', N'#f50000', N'p20231110T123345964133200_Red.webp', N'Red                                               ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231110T123820844681400', N'#1f1d1c', N'p20231110T123820844681400_1f1d1c.webp', N'Black                                             ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231112T133200124445', N'#aba0a0', N'p20231112T133200124445_aba0a0.webp', N'Greey                                             ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231112T134101088713200', N'#000000', N'p20231112T134101088713200_000000.webp', N'Black                                             ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231112T134740717197600', N'#f5ebeb', N'p20231112T134740717197600_f5ebeb.webp', N'While                                             ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231112T135328468516300', N'#282828', N'p20231112T135328468516300_282828.webp', N'Black                                             ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231112T133440016244900', N'#0a0a0a', N'p20231112T133440016244900_0a0a0a.webp', N'Black                                             ')
GO
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl720231110T122558406903900', N'od720231110T122558385954300', N'p20231110T105901853530900', 1, N'#000000', CAST(3000 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl720231110T124741774090', N'od720231110T124741754714600', N'p20231110T123345964133200', 1, N'#000000', CAST(3000 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl720231112T180943833355300', N'od720231112T180943819408200', N'p20231110T123128588879400', 1, N'#000000', CAST(3000 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl720231112T181937758935800', N'od720231112T181937750383200', N'p20231110T123128588879400', 1, N'#000000', CAST(3000 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl720231115T205429655777900', N'od720231115T205429620833200', N'p20231110T123128588879400', 1, N'#000000', CAST(3000 AS Decimal(18, 0)))
GO
INSERT [dbo].[orders] ([OrderID], [AID], [OrderDate], [StaffID], [Status], [Address], [DatePay]) VALUES (N'od720231110T122558385954300', 7, CAST(N'2023-11-10T12:25:58.407' AS DateTime), 0, -2, N'23e23e23e', NULL)
INSERT [dbo].[orders] ([OrderID], [AID], [OrderDate], [StaffID], [Status], [Address], [DatePay]) VALUES (N'od720231110T122708674890', 7, CAST(N'2023-11-10T12:27:08.673' AS DateTime), 0, -2, N'23e23e23e', NULL)
INSERT [dbo].[orders] ([OrderID], [AID], [OrderDate], [StaffID], [Status], [Address], [DatePay]) VALUES (N'od720231110T124741754714600', 7, CAST(N'2023-11-10T12:47:41.770' AS DateTime), 0, 1, N'23e23e23e', NULL)
INSERT [dbo].[orders] ([OrderID], [AID], [OrderDate], [StaffID], [Status], [Address], [DatePay]) VALUES (N'od720231112T180943819408200', 7, CAST(N'2023-11-12T18:09:43.830' AS DateTime), 2, -1, N'23e23e23e', NULL)
INSERT [dbo].[orders] ([OrderID], [AID], [OrderDate], [StaffID], [Status], [Address], [DatePay]) VALUES (N'od720231112T181937750383200', 7, CAST(N'2023-11-12T18:19:37.757' AS DateTime), 0, -1, N'23e23e23e', NULL)
INSERT [dbo].[orders] ([OrderID], [AID], [OrderDate], [StaffID], [Status], [Address], [DatePay]) VALUES (N'od720231115T205429620833200', 7, CAST(N'2023-11-15T20:54:29.650' AS DateTime), 0, 1, N'23e23e23e', NULL)
GO
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231110T105901853530900', N'lap top len no vo', 20, 1231229, CAST(3000 AS Decimal(18, 0)), N'$', N'qasadwq qw qw qwd', 1, 1, 1, CAST(N'2023-11-10T10:59:01.927' AS DateTime), 1)
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231110T123128588879400', N'iphone11', 11, 300, CAST(3000 AS Decimal(18, 0)), N'$', N'Bộ sản phẩm gồm: Hộp, Sách hướng dẫn, Cây lấy sim, Cáp Lightning – Type C.
Bảo hành 1 năm chính hãng không phụ thu.
Giao hàng tận nơi ship toàn quốc.
Tổng đài : 0947.666.696 (từ 9h30 – 21h30 mỗi ngày)', 2, 2, 1, CAST(N'2023-11-10T12:31:28.727' AS DateTime), 1)
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231110T123345964133200', N'Iphone 12', 12, 299, CAST(3000 AS Decimal(18, 0)), N'$', N'Mới, đầy đủ phụ kiện từ nhà sản xuất
Thân máy, cáp USB-C to Lightning, sách HDSD
Bảo hành 12 tháng, 1 đổi 1 trong 15 ngày do lỗi nhà sản xuất', 2, 2, 1, CAST(N'2023-11-10T12:33:46.080' AS DateTime), 1)
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231110T123820844681400', N'Maccbook Air M2 256GB 2022', 2022, 300, CAST(5000 AS Decimal(18, 0)), N'$', N'8-Core CPU 8-Core GPU
8GB Unified Memory
256GB SSD Storage¹

16-core Neural Engine
13.6-inch Liquid Retina display with True Tone
1080p FaceTime HD camera
MagSafe 3 charging port
Two Thunderbolt / USB 4 ports
Magic Keyboard with Touch ID
Force Touch trackpad
30W USB-C Power Adapter', 2, 1, 1, CAST(N'2023-11-10T12:38:20.987' AS DateTime), 1)
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231112T133200124445', N'Laptop Lenovo IdeaPad 3 14ITL6 82H701QYVN', 3, 1222, CAST(2300 AS Decimal(18, 0)), N'$', N'CPU: Intel Core i5-1155G7 (upto 4.50GHz, 8MB)
RAM: 16GB (8GB Soldered DDR4-3200 + 8GB SO-DIMM DDR4-3200)
Ổ cứng: 512GB SSD M.2 2242 PCIe® 4.0x4 NVMe®
VGA: Integrated Intel® Iris® Xe Graphics', 3, 1, 3, CAST(N'2023-11-12T13:32:00.260' AS DateTime), 1)
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231112T133440016244900', N'Laptop Lenovo IdeaPad Gaming 3 15ARH7 82SB00BBVN', 4, 333, CAST(775 AS Decimal(18, 0)), N'$', N'CPU: AMD Ryzen 5 6600H (6 nhân - 12 luồng, upto 4.5GHz, 3MB L2 / 16MB L3)
RAM: 2x 8GB SO-DIMM DDR5-4800 (2 khe, tối đa 16GB)
Ổ cứng: 512GB SSD M.2 2242 PCIe 4.0x4 NVMe
VGA: NVIDIA GeForce RTX 3050 4GB GDDR6', 3, 1, 3, CAST(N'2023-11-12T13:34:40.163' AS DateTime), 1)
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231112T134101088713200', N'Earphone Roland RH5', 5, 200, CAST(120 AS Decimal(18, 0)), N'$', N'Super speaker technology, optimized performance for high portability.
The headphone frame is firmly designed with synthetic plastic.
The wide speaker frequency range is suitable for testing and listening to music.
Easy connection with 2 types 3.5mm and 6mm', 1, 3, 5, CAST(N'2023-11-12T13:41:01.237' AS DateTime), 1)
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231112T134740717197600', N' Bluetooth Airpods Pro', 4, 100, CAST(200 AS Decimal(18, 0)), N'$', N'Compatible: iPhone, iPad, Mac, Apple Watch, iPod, Apple TV.
Connectivity: 1 device
Charging time: 5 minutes of charging provides 1 hour of music or 1 hour of talk time
Usage time: 4.5 hours of music playback, 3.5 hours of voice calls
Sensor: Dual beam microphone, Inward facing microphone, Dual optical sensor, Motion detection accelerometer, Speech detection acceleration, Force sensor
Size: AirPods
Container: 45.6g
Weight: Airpods
Communication port: Lightning', 2, 3, 1, CAST(N'2023-11-12T13:47:40.870' AS DateTime), 1)
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231112T135328468516300', N'Samsung Galaxy S23 5G 128GB', 5, 3223, CAST(1200 AS Decimal(18, 0)), N'', N'Screen: Dynamic AMOLED 2X6.1"Full HD+
Operating system: Android 13
Rear camera: Main 50 MP & Secondary 12 MP, 10 MP
Front camera: 12 MP
Chip: Snapdragon 8 Gen 2 for Galaxy
RAM: 8 GB
Storage capacity: 128 GB
SIM: 2 Nano SIM or 1 Nano SIM + 1 eSIM Support 5G
Battery, Charger: 3900 mAh25 W', 4, 2, 5, CAST(N'2023-11-12T13:53:28.600' AS DateTime), 1)
GO
SET IDENTITY_INSERT [dbo].[suppliers] ON 

INSERT [dbo].[suppliers] ([SupplierID], [SupplierName], [Address], [Phone]) VALUES (1, N'ngo dinh linh', N'279 ph?m hong thái', N'0974841508')
INSERT [dbo].[suppliers] ([SupplierID], [SupplierName], [Address], [Phone]) VALUES (2, N'Apple', N'279 ph?m hong thái', N'0974841508')
INSERT [dbo].[suppliers] ([SupplierID], [SupplierName], [Address], [Phone]) VALUES (3, N'Lenovo Store', N'279 ph?m hong thái', N'0974841508')
INSERT [dbo].[suppliers] ([SupplierID], [SupplierName], [Address], [Phone]) VALUES (4, N'SamSung', N'279 ph?m hong thái', N'0974841508')
SET IDENTITY_INSERT [dbo].[suppliers] OFF
GO
ALTER TABLE [dbo].[categories] ADD  DEFAULT (NULL) FOR [CategoryName]
GO
ALTER TABLE [dbo].[categories] ADD  DEFAULT (NULL) FOR [Description]
GO
ALTER TABLE [dbo].[order_details] ADD  DEFAULT (NULL) FOR [OrderID]
GO
ALTER TABLE [dbo].[order_details] ADD  DEFAULT (NULL) FOR [ProductID]
GO
ALTER TABLE [dbo].[order_details] ADD  DEFAULT (NULL) FOR [Quantity]
GO
ALTER TABLE [dbo].[orders] ADD  DEFAULT (NULL) FOR [AID]
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
ALTER TABLE [dbo].[carts]  WITH CHECK ADD  CONSTRAINT [FK_carst_products] FOREIGN KEY([ProductID])
REFERENCES [dbo].[products] ([ProductID])
GO
ALTER TABLE [dbo].[carts] CHECK CONSTRAINT [FK_carst_products]
GO
ALTER TABLE [dbo].[carts]  WITH CHECK ADD  CONSTRAINT [FK_carts_accounts] FOREIGN KEY([AID])
REFERENCES [dbo].[accounts] ([AID])
GO
ALTER TABLE [dbo].[carts] CHECK CONSTRAINT [FK_carts_accounts]
GO
ALTER TABLE [dbo].[color]  WITH CHECK ADD  CONSTRAINT [FK_color_products] FOREIGN KEY([ProductID])
REFERENCES [dbo].[products] ([ProductID])
GO
ALTER TABLE [dbo].[color] CHECK CONSTRAINT [FK_color_products]
GO
ALTER TABLE [dbo].[feedbacks]  WITH CHECK ADD  CONSTRAINT [FK_feedbacks_accounts] FOREIGN KEY([AID])
REFERENCES [dbo].[accounts] ([AID])
GO
ALTER TABLE [dbo].[feedbacks] CHECK CONSTRAINT [FK_feedbacks_accounts]
GO
ALTER TABLE [dbo].[feedbacks]  WITH CHECK ADD  CONSTRAINT [FK_feedbacks_products] FOREIGN KEY([ProductID])
REFERENCES [dbo].[products] ([ProductID])
GO
ALTER TABLE [dbo].[feedbacks] CHECK CONSTRAINT [FK_feedbacks_products]
GO
ALTER TABLE [dbo].[order_details]  WITH CHECK ADD FOREIGN KEY([ProductID])
REFERENCES [dbo].[products] ([ProductID])
GO
ALTER TABLE [dbo].[order_details]  WITH CHECK ADD  CONSTRAINT [FK_order_details_orders] FOREIGN KEY([OrderID])
REFERENCES [dbo].[orders] ([OrderID])
GO
ALTER TABLE [dbo].[order_details] CHECK CONSTRAINT [FK_order_details_orders]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK_orders_accounts] FOREIGN KEY([AID])
REFERENCES [dbo].[accounts] ([AID])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK_orders_accounts]
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


