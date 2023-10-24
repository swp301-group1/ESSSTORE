USE [master]
GO
/****** Object:  Database [ESS_Store11]    Script Date: 10/24/2023 2:22:37 PM ******/
CREATE DATABASE [ESS_Store11]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ESS_Store11', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\ESS_Store11.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'ESS_Store11_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\ESS_Store11_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [ESS_Store11] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ESS_Store11].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ESS_Store11] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ESS_Store11] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ESS_Store11] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ESS_Store11] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ESS_Store11] SET ARITHABORT OFF 
GO
ALTER DATABASE [ESS_Store11] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ESS_Store11] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ESS_Store11] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ESS_Store11] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ESS_Store11] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ESS_Store11] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ESS_Store11] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ESS_Store11] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ESS_Store11] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ESS_Store11] SET  DISABLE_BROKER 
GO
ALTER DATABASE [ESS_Store11] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ESS_Store11] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ESS_Store11] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ESS_Store11] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ESS_Store11] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ESS_Store11] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ESS_Store11] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ESS_Store11] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [ESS_Store11] SET  MULTI_USER 
GO
ALTER DATABASE [ESS_Store11] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ESS_Store11] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ESS_Store11] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ESS_Store11] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [ESS_Store11] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [ESS_Store11] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [ESS_Store11] SET QUERY_STORE = OFF
GO
USE [ESS_Store11]
GO
/****** Object:  Table [dbo].[accounts]    Script Date: 10/24/2023 2:22:37 PM ******/
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
/****** Object:  Table [dbo].[brands]    Script Date: 10/24/2023 2:22:37 PM ******/
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
/****** Object:  Table [dbo].[carst]    Script Date: 10/24/2023 2:22:37 PM ******/
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
	[Color] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[CartID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[categories]    Script Date: 10/24/2023 2:22:37 PM ******/
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
/****** Object:  Table [dbo].[color]    Script Date: 10/24/2023 2:22:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[color](
	[ProductID] [varchar](255) NOT NULL,
	[ColorID] [int] NOT NULL,
	[Image] [nvarchar](50) NOT NULL,
	[ColorName] [nchar](50) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customers]    Script Date: 10/24/2023 2:22:37 PM ******/
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
/****** Object:  Table [dbo].[feedbacks]    Script Date: 10/24/2023 2:22:37 PM ******/
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
/****** Object:  Table [dbo].[order_details]    Script Date: 10/24/2023 2:22:37 PM ******/
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
/****** Object:  Table [dbo].[orders]    Script Date: 10/24/2023 2:22:37 PM ******/
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
/****** Object:  Table [dbo].[products]    Script Date: 10/24/2023 2:22:37 PM ******/
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
/****** Object:  Table [dbo].[staffs]    Script Date: 10/24/2023 2:22:37 PM ******/
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
/****** Object:  Table [dbo].[suppliers]    Script Date: 10/24/2023 2:22:37 PM ******/
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
INSERT [dbo].[accounts] ([Email], [Password], [Role], [Status]) VALUES (N'linhndhe163822@fpt.edu.vn', N'123456', 1, 1)
INSERT [dbo].[accounts] ([Email], [Password], [Role], [Status]) VALUES (N'ngolinh09032002@gmail.com', N'123456', 2, 1)
INSERT [dbo].[accounts] ([Email], [Password], [Role], [Status]) VALUES (N'ngolinh209032002@gmail.com', N'123456', 1, 1)
INSERT [dbo].[accounts] ([Email], [Password], [Role], [Status]) VALUES (N'ngolinh309032002@gmail.com', N'ngolinh09032002', 1, 1)
GO
INSERT [dbo].[brands] ([BrandID], [BrandName]) VALUES (1, N'Apple                                             ')
INSERT [dbo].[brands] ([BrandID], [BrandName]) VALUES (2, N'Dell                                              ')
INSERT [dbo].[brands] ([BrandID], [BrandName]) VALUES (3, N'Lenovo                                            ')
INSERT [dbo].[brands] ([BrandID], [BrandName]) VALUES (4, N'Asus                                              ')
INSERT [dbo].[brands] ([BrandID], [BrandName]) VALUES (5, N'Acer                                              ')
INSERT [dbo].[brands] ([BrandID], [BrandName]) VALUES (6, N'Samsung                                           ')
GO
INSERT [dbo].[carst] ([CartID], [CustomerID], [ProductID], [Quantity], [Time], [Color]) VALUES (N'1-p20231011T144239825028-1', 1, N'p20231011T144239825028', 1, CAST(N'2023-10-24T13:54:53.610' AS DateTime), 1)
INSERT [dbo].[carst] ([CartID], [CustomerID], [ProductID], [Quantity], [Time], [Color]) VALUES (N'1-p20231011T144815390859800-1', 1, N'p20231011T144815390859800', 1, CAST(N'2023-10-24T13:55:16.783' AS DateTime), 1)
INSERT [dbo].[carst] ([CartID], [CustomerID], [ProductID], [Quantity], [Time], [Color]) VALUES (N'1-p20231020T162928071142600-1', 1, N'p20231020T162928071142600', 1, CAST(N'2023-10-24T14:06:03.510' AS DateTime), 1)
GO
SET IDENTITY_INSERT [dbo].[categories] ON 

INSERT [dbo].[categories] ([CategoryID], [CategoryName], [Description]) VALUES (1, N'Telephone', NULL)
INSERT [dbo].[categories] ([CategoryID], [CategoryName], [Description]) VALUES (2, N'Laptop', NULL)
INSERT [dbo].[categories] ([CategoryID], [CategoryName], [Description]) VALUES (3, N'Computer', NULL)
INSERT [dbo].[categories] ([CategoryID], [CategoryName], [Description]) VALUES (4, N'Tablet', NULL)
SET IDENTITY_INSERT [dbo].[categories] OFF
GO
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231011T090757061506900', 1, N'p20231011T090757061506900_1.webp', N'Black                                             ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231011T093735793582', 1, N'p20231011T093735793582_1.webp', N'Black                                             ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231011T144239825028', 1, N'p20231011T144239825028_1.webp', N'Black                                             ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231011T144239825028', 2, N'p20231011T144239825028_2.webp', N'While                                             ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231011T144815390859800', 1, N'p20231011T144815390859800_1.webp', N'Black                                             ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231011T173501162505500', 1, N'p20231011T173501162505500_1.webp', N'Black                                             ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231012T224641112571800', 1, N'p20231012T224641112571800_1.webp', N'Black                                             ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231014T233545920637600', 1, N'p20231014T233545920637600_1.webp', N'Black                                             ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231020T004200918679700', 1, N'p20231020T004200918679700_1.webp', N'Black                                             ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231011T150019315992900', 2, N'p20231011T150019315992900_2.webp', N'While                                             ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231020T163622446401300', 1, N'p20231020T163622446401300_1.webp', N'Black                                             ')
INSERT [dbo].[color] ([ProductID], [ColorID], [Image], [ColorName]) VALUES (N'p20231020T162928071142600', 1, N'p20231020T162928071142600_1.webp', N'Black                                             ')
GO
SET IDENTITY_INSERT [dbo].[customers] ON 

INSERT [dbo].[customers] ([CustomerID], [CustomerName], [Address], [PhoneNumber], [Email]) VALUES (1, N'Ngo Dinh Linh', N'279 phạm hong thái', N'0974841508', N'linhndhe163822@fpt.edu.vn')
INSERT [dbo].[customers] ([CustomerID], [CustomerName], [Address], [PhoneNumber], [Email]) VALUES (2, N'Ngo Dinh Linh', N'undefined', N'0974841508', N'ngolinh209032002@gmail.com')
INSERT [dbo].[customers] ([CustomerID], [CustomerName], [Address], [PhoneNumber], [Email]) VALUES (3, N'ngo dinh linh', N'279 phạm hong thái', N'0974841508', N'ngolinh309032002@gmail.com')
SET IDENTITY_INSERT [dbo].[customers] OFF
GO
SET IDENTITY_INSERT [dbo].[feedbacks] ON 

INSERT [dbo].[feedbacks] ([FeedbackID], [ProductID], [CustomerID], [Commen], [Star], [Time], [Status]) VALUES (1, N'p20231011T093735793582', 1, N'43t 45y45y 5y regrt rthrhr ', 4, CAST(N'2023-10-11T14:01:05.580' AS DateTime), 1)
INSERT [dbo].[feedbacks] ([FeedbackID], [ProductID], [CustomerID], [Commen], [Star], [Time], [Status]) VALUES (3, N'p20231011T144815390859800', 1, N'7tr8o72tyr72y9 8y 8ry8y 82y', 3, CAST(N'2023-10-11T22:22:32.847' AS DateTime), 1)
INSERT [dbo].[feedbacks] ([FeedbackID], [ProductID], [CustomerID], [Commen], [Star], [Time], [Status]) VALUES (5, N'p20231011T093735793582', 1, N'giang ngao da
', 4, CAST(N'2023-10-12T22:38:32.990' AS DateTime), 1)
INSERT [dbo].[feedbacks] ([FeedbackID], [ProductID], [CustomerID], [Commen], [Star], [Time], [Status]) VALUES (6, N'p20231011T144239825028', 1, N'werwerwe', 4, CAST(N'2023-10-14T22:17:59.720' AS DateTime), 1)
INSERT [dbo].[feedbacks] ([FeedbackID], [ProductID], [CustomerID], [Commen], [Star], [Time], [Status]) VALUES (7, N'p20231011T144239825028', 1, N'e34tg34', 0, CAST(N'2023-10-15T17:39:01.193' AS DateTime), 0)
INSERT [dbo].[feedbacks] ([FeedbackID], [ProductID], [CustomerID], [Commen], [Star], [Time], [Status]) VALUES (8, N'p20231011T144239825028', 1, N'e34tg34', 4, CAST(N'2023-10-15T17:39:03.680' AS DateTime), 0)
INSERT [dbo].[feedbacks] ([FeedbackID], [ProductID], [CustomerID], [Commen], [Star], [Time], [Status]) VALUES (9, N'p20231011T144239825028', 1, N'24323r32r3r', 0, CAST(N'2023-10-15T17:39:25.230' AS DateTime), 0)
INSERT [dbo].[feedbacks] ([FeedbackID], [ProductID], [CustomerID], [Commen], [Star], [Time], [Status]) VALUES (10, N'p20231011T093735793582', 1, N'qr23r 32r 3r 23', 3, CAST(N'2023-10-15T17:42:44.787' AS DateTime), 1)
INSERT [dbo].[feedbacks] ([FeedbackID], [ProductID], [CustomerID], [Commen], [Star], [Time], [Status]) VALUES (11, N'p20231011T173501162505500', 1, N'san pham kha la oke
', 0, CAST(N'2023-10-16T21:00:38.733' AS DateTime), 0)
INSERT [dbo].[feedbacks] ([FeedbackID], [ProductID], [CustomerID], [Commen], [Star], [Time], [Status]) VALUES (12, N'p20231011T093735793582', 1, N'sffewfwe', 4, CAST(N'2023-10-22T15:36:16.883' AS DateTime), 0)
INSERT [dbo].[feedbacks] ([FeedbackID], [ProductID], [CustomerID], [Commen], [Star], [Time], [Status]) VALUES (13, N'p20231011T093735793582', 1, N'fgwufgiwegfiuwif uweof we', 3, CAST(N'2023-10-23T00:01:37.673' AS DateTime), 0)
SET IDENTITY_INSERT [dbo].[feedbacks] OFF
GO
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl120231012T001527926570500', N'od120231012T001527909738500', N'p20231011T093735793582', 4, 1, CAST(4000 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl120231012T124502115003100', N'od120231012T124502108485700', N'p20231011T090757061506900', 1, 1, CAST(3000 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl120231012T141516449903600', N'od120231012T141516441849900', N'p20231011T093735793582', 4, 1, CAST(4000 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl120231012T141516498098900', N'od120231012T141516441849900', N'p20231011T173501162505500', 3, 1, CAST(3500 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl120231012T141516538425300', N'od120231012T141516441849900', N'p20231011T090757061506900', 1, 1, CAST(3000 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl120231013T142818286841700', N'od120231013T142818272141800', N'p20231011T173501162505500', 1, 1, CAST(3500 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl120231013T142818347128', N'od120231013T142818272141800', N'p20231011T093735793582', 1, 1, CAST(4000 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl120231013T143940893368900', N'od120231013T143940893368900', N'p20231011T093735793582', 1, 1, CAST(4000 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl120231014T153626593366600', N'od120231014T153626546451200', N'p20231011T144239825028', 3, 2, CAST(3500 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl120231014T153816272503800', N'od120231014T153816246482400', N'p20231011T144815390859800', 3, 1, CAST(3000 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl120231014T154028262255100', N'od120231014T154028237669500', N'p20231011T090757061506900', 3, 1, CAST(3000 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl120231014T154347300992', N'od120231014T154347290889', N'p20231011T090757061506900', 5, 1, CAST(3000 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl120231014T221737826882400', N'od120231014T221737818362100', N'p20231011T144239825028', 3, 1, CAST(3500 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl120231015T173540667509300', N'od120231015T173540653497700', N'p20231011T144239825028', 1, 1, CAST(3500 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl120231015T173540749440800', N'od120231015T173540653497700', N'p20231011T093735793582', 1, 1, CAST(4000 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl120231015T174224275532500', N'od120231015T174224270213600', N'p20231011T093735793582', 1, 1, CAST(4000 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl120231017T164639293455800', N'od120231017T164639273700600', N'p20231011T144815390859800', 1, 1, CAST(3000 AS Decimal(18, 0)))
INSERT [dbo].[order_details] ([OrderDetailID], [OrderID], [ProductID], [Quantity], [Color], [Price]) VALUES (N'odl220231020T161201543882800', N'od220231020T161201535974800', N'p20231011T144239825028', 1, 1, CAST(3500 AS Decimal(18, 0)))
GO
INSERT [dbo].[orders] ([OrderID], [CustomerID], [OrderDate], [StaffID], [Status], [Address]) VALUES (N'od120231012T001527909738500', 1, CAST(N'2023-10-12T00:15:27.920' AS DateTime), 0, 2, N'279 ph?m hong thái')
INSERT [dbo].[orders] ([OrderID], [CustomerID], [OrderDate], [StaffID], [Status], [Address]) VALUES (N'od120231012T124502108485700', 1, CAST(N'2023-10-12T12:45:02.113' AS DateTime), 0, 2, N'279 ph?m hong thái')
INSERT [dbo].[orders] ([OrderID], [CustomerID], [OrderDate], [StaffID], [Status], [Address]) VALUES (N'od120231012T141516441849900', 1, CAST(N'2023-10-12T14:15:16.450' AS DateTime), 1, 2, N'279 ph?m hong thái')
INSERT [dbo].[orders] ([OrderID], [CustomerID], [OrderDate], [StaffID], [Status], [Address]) VALUES (N'od120231013T142818272141800', 1, CAST(N'2023-10-13T14:28:18.287' AS DateTime), 0, 2, N'279 ph?m hong thái')
INSERT [dbo].[orders] ([OrderID], [CustomerID], [OrderDate], [StaffID], [Status], [Address]) VALUES (N'od120231013T143940893368900', 1, CAST(N'2023-10-13T14:39:40.897' AS DateTime), 0, 2, N'279 ph?m hong thái')
INSERT [dbo].[orders] ([OrderID], [CustomerID], [OrderDate], [StaffID], [Status], [Address]) VALUES (N'od120231014T153626546451200', 1, CAST(N'2023-10-14T15:36:26.553' AS DateTime), 0, 1, N'279 ph?m hong thái')
INSERT [dbo].[orders] ([OrderID], [CustomerID], [OrderDate], [StaffID], [Status], [Address]) VALUES (N'od120231014T153816246482400', 1, CAST(N'2023-10-14T15:38:16.253' AS DateTime), 0, 1, N'279 ph?m hong thái')
INSERT [dbo].[orders] ([OrderID], [CustomerID], [OrderDate], [StaffID], [Status], [Address]) VALUES (N'od120231014T154028237669500', 1, CAST(N'2023-10-14T15:40:28.247' AS DateTime), 1, 2, N'279 ph?m hong thái')
INSERT [dbo].[orders] ([OrderID], [CustomerID], [OrderDate], [StaffID], [Status], [Address]) VALUES (N'od120231014T154347290889', 1, CAST(N'2023-10-14T15:43:47.300' AS DateTime), 0, 1, N'279 ph?m hong thái')
INSERT [dbo].[orders] ([OrderID], [CustomerID], [OrderDate], [StaffID], [Status], [Address]) VALUES (N'od120231014T221737818362100', 1, CAST(N'2023-10-14T22:17:37.827' AS DateTime), 1, 2, N'279 ph?m hong thái')
INSERT [dbo].[orders] ([OrderID], [CustomerID], [OrderDate], [StaffID], [Status], [Address]) VALUES (N'od120231015T173540653497700', 1, CAST(N'2023-10-15T17:35:40.660' AS DateTime), 0, 1, N'279 ph?m hong thái')
INSERT [dbo].[orders] ([OrderID], [CustomerID], [OrderDate], [StaffID], [Status], [Address]) VALUES (N'od120231015T174224270213600', 1, CAST(N'2023-10-15T17:42:24.273' AS DateTime), 1, 2, N'279 ph?m hong thái')
INSERT [dbo].[orders] ([OrderID], [CustomerID], [OrderDate], [StaffID], [Status], [Address]) VALUES (N'od120231017T164639273700600', 1, CAST(N'2023-10-17T16:46:39.290' AS DateTime), 0, 0, N'279 ph?m hong thái')
INSERT [dbo].[orders] ([OrderID], [CustomerID], [OrderDate], [StaffID], [Status], [Address]) VALUES (N'od220231020T161201535974800', 2, CAST(N'2023-10-20T16:12:01.540' AS DateTime), 1, 2, N'undefined')
GO
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231011T090757061506900', N'iphone11', 11, 3017, CAST(3000 AS Decimal(18, 0)), N'$', N'iPhone 11 là một chiếc điện thoại thông minh được thiết kế, phát triển và tiếp thị bởi Apple Inc. 
Đây là thế hệ iPhone thứ 13, kế nhiệm iPhone XR, và được ra mắt vào ngày 10 tháng 9 năm 2019, cùng với iPhone 11 Pro cao cấp hơn tại Nhà hát Steve Jobs trong Công viên Apple, Cupertino, bởi CEO của Apple Tim Cook1. 
iPhone 11 có màn hình LCD IPS Liquid Retina kích thước 6.1 inch, độ phân giải 828 x 1792 pixel, bảo vệ bằng kính chống trầy xước và lớp phủ chống dính vân tay. Máy có hai camera sau, mỗi camera có độ phân giải 12 MP, một camera góc rộng với khẩu độ f/1.8 và một camera siêu rộng với khẩu độ f/2.4. 
Camera sau có thể quay video 4K với tốc độ 24, 30 hoặc 60 khung hình/giây hoặc 1080p với tốc độ 30 hoặc 60 khung hình/giây, cũng như chụp ảnh chân dung, HDR và chế độ ban đêm. 
Camera trước cũng có độ phân giải 12 MP, khẩu độ f/2.2 và có thể quay video 4K hoặc chụp ảnh Animoji2. iPhone 11 được trang bị vi xử lý Apple A13 Bionic (7 nm+), RAM 4 GB và bộ nhớ trong 64, 128 hoặc 256 GB. 
Pin của máy có dung lượng 3110 mAh, hỗ trợ sạc nhanh và sạc không dây chuẩn Qi2. 
iPhone 11 có sẵn nhiều màu sắc, bao gồm đen, trắng, xanh lá cây, tím, đỏ và vàng1. Giá bán khởi điểm của iPhone 11 tại Việt Nam là khoảng 16 triệu đồng3', 1, 1, 1, CAST(N'2023-10-11T09:07:57.257' AS DateTime), 1)
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231011T093735793582', N'iphone12', 12, 2973, CAST(4000 AS Decimal(18, 0)), N'$', N'iPhone 12 là một chiếc điện thoại thông minh của Apple, được ra mắt vào tháng 10 năm 2020. iPhone 12 có thiết kế nhỏ gọn, nhẹ và mỏng, với khung nhôm và mặt kính chống trầy xước. iPhone 12 có màn hình OLED 6.1 inch, hỗ trợ HDR10, Dolby Vision và Ceramic Shield glass1. iPhone 12 cũng là chiếc iPhone đầu tiên hỗ trợ kết nối 5G, cho tốc độ truyền dữ liệu nhanh hơn1. iPhone 12 có hai camera sau, một camera góc rộng 12 MP với khẩu độ f/1.6 và một camera siêu rộng 12 MP với góc nhìn 120 độ1. iPhone 12 cũng có khả năng quay video 4K với HDR và Dolby Vision1. iPhone 12 còn có tính năng MagSafe, cho phép sạc không dây và gắn các phụ kiện từ tính2.', 1, 1, 1, CAST(N'2023-10-11T09:37:35.877' AS DateTime), 1)
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231011T144239825028', N'Lenovo ThinkBook 15 G4 (Gen 4)', 15, 2994, CAST(3500 AS Decimal(18, 0)), N'$', N'Bộ vi xử lí: Intel® Core™ i5-1235U, 10 nhân 12 luồng (1.30GHz up to 4.40GHz, 12MB Cache)
RAM: 8GB DDR4 3200MHz Up to 40GB (8GB soldered + 32GB SO-DIMM) DDR4-3200 
Bộ nhớ: 256GB PCIe SSD (Up to two drives, 2x M.2 SSD)
Card đồ hoạ: Intel®Iris®Xe Graphics
Màn hình: 15.6" FHD (1920 x 1080) IPS, anti-glare, 300 nits, TÜV Rheinland Low Blue Light certification
Hệ điều hành: No OS
Tình trạng sản phẩm: Mới 100% Chính Hãng
Chi tiết thông số từ kỹ thuật Lenovo:  Click Link
Bảo hành: Chính hãng Lenovo 2 năm Lenovo Việt', 2, 2, 3, CAST(N'2023-10-11T14:42:39.980' AS DateTime), 1)
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231011T144815390859800', N'MacBook  Pro 16', 16, 1241411, CAST(3000 AS Decimal(18, 0)), N'$', N'Tình trạng: New 100%
Màu: Xám (Space Gray)
10-Core CPU, Apple M1 Max
32-Core GPU
64GB Unified Memory
4TB SSD Storage¹
16-core Neural Engine
16-inch Liquid Retina XDR display
Three Thunderbolt 4 ports, HDMI port, SDXC card slot, MagSafe 3 port
Magic Keyboard with Touch ID
Force Touch trackpad
140W USB-C Power Adapter', 1, 2, 1, CAST(N'2023-10-11T14:48:15.510' AS DateTime), 1)
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231011T150019315992900', N'Máy tính d? bàn AIO HP Eliteone 840 G9 76N54PA', 840, 141414, CAST(6000 AS Decimal(18, 0)), N'$', N'Chíp xử lý: Intel Core i7-12700 (upto 4.9 GHz, 25MB)
Bộ nhớ Ram: 8GB DDR5-4800MHz (1 x 8GB) (2 khe)
Ổ đĩa cứng: 512GB M.2 NVMe SSD
Kết nối mạng: Lan Gigabit, Wifi+ Bluetooth', 1, 3, 5, CAST(N'2023-10-11T15:00:19.460' AS DateTime), 1)
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231011T173501162505500', N'Samsung Galaxy A33 5G', 33, 12412393, CAST(3500 AS Decimal(18, 0)), N'$', N'Samsung Galaxy A33 5G chính hãng, máy mới fullbox nguyên seal. Mua bán trả góp điện thoại Samsung Galaxy A33 5G 128GB chính hãng giá rẻ, uy tín', 4, 1, 6, CAST(N'2023-10-11T17:35:01.357' AS DateTime), 1)
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231012T224641112571800', N'iphone20', 20, 3004, CAST(3000 AS Decimal(18, 0)), N'$', N'-weuruworow3i4roiurp34r
-eruhuieyriugheh hguherghueh-
-eurguehriugherhguegheug
-erwueruuergherughiuerg
', 3, 1, 1, CAST(N'2023-10-12T22:46:41.343' AS DateTime), 1)
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231014T233545920637600', N'iphone111', 20, 124124, CAST(30 AS Decimal(18, 0)), N'$', N'wetwegtyertger', 2, 1, 2, CAST(N'2023-10-14T23:35:46.033' AS DateTime), 1)
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231020T004200918679700', N'iphone15', 4, 123124, CAST(3000 AS Decimal(18, 0)), N'$', N'âfafaefefascas', 2, 1, 1, CAST(N'2023-10-20T00:42:01.047' AS DateTime), 1)
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231020T162928071142600', N'iphone20', 4, 1212, CAST(3000 AS Decimal(18, 0)), N'$', N'qefewfwe', 2, 1, 1, CAST(N'2023-10-20T16:29:28.170' AS DateTime), 1)
INSERT [dbo].[products] ([ProductID], [ProductName], [Size], [Quantity], [Price], [Unit], [Contents], [SupplierID], [CategoryID], [BrandID], [DateCreate], [Status]) VALUES (N'p20231020T163622446401300', N'iphone15', 20, 141212, CAST(3000 AS Decimal(18, 0)), N'$', N'qyteuwfyw e w ', 2, 1, 1, CAST(N'2023-10-20T16:36:22.557' AS DateTime), 1)
GO
SET IDENTITY_INSERT [dbo].[staffs] ON 

INSERT [dbo].[staffs] ([StaffID], [Name], [Email]) VALUES (1, N'ngo dinh linh', N'ngolinh09032002@gmail.com')
SET IDENTITY_INSERT [dbo].[staffs] OFF
GO
SET IDENTITY_INSERT [dbo].[suppliers] ON 

INSERT [dbo].[suppliers] ([SupplierID], [SupplierName], [Address], [Phone]) VALUES (1, N'China', NULL, NULL)
INSERT [dbo].[suppliers] ([SupplierID], [SupplierName], [Address], [Phone]) VALUES (2, N'
Japan', NULL, NULL)
INSERT [dbo].[suppliers] ([SupplierID], [SupplierName], [Address], [Phone]) VALUES (3, N'
Australia', NULL, NULL)
INSERT [dbo].[suppliers] ([SupplierID], [SupplierName], [Address], [Phone]) VALUES (4, N'Taiwan', NULL, NULL)
SET IDENTITY_INSERT [dbo].[suppliers] OFF
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
GO
USE [master]
GO
ALTER DATABASE [ESS_Store11] SET  READ_WRITE 
GO
