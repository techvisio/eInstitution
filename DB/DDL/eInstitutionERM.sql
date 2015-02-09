CREATE TABLE `castecategorymaster` (
  `Id` int(11) NOT NULL,
  `Category` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
);

CREATE TABLE `consultantmaster` (
  `Id` bigint(20) NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Primary_Contact_No` varchar(50) DEFAULT NULL,
  `Secondary_contact_No` varchar(50) DEFAULT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `Email_Id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
);

CREATE TABLE `counsellingmaster` (
  `Id` bigint(20) NOT NULL,
  `Counselling_Body` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
);

CREATE TABLE `coursemaster` (
  `Id` int(11) NOT NULL,
  `Course` varchar(50) DEFAULT NULL,
  `Course_Type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
);

CREATE TABLE `coursebranchmaster` (
  `Course_Id` int(11) NOT NULL,
  `Id` int(11) NOT NULL,
  `Branch` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Course_Id` (`Course_Id`),
  CONSTRAINT `coursebranchmaster_ibfk_1` FOREIGN KEY (`Course_Id`) REFERENCES `coursemaster` (`Id`)
);



CREATE TABLE `feeheadmaster` (
  `Id` int(11) NOT NULL,
  `Fee_Head` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
);

CREATE TABLE `qualificationmaster` (
  `Id` int(11) NOT NULL,
  `QualifyingExam` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
);

CREATE TABLE `quotacodemaster` (
  `Id` int(11) NOT NULL,
  `Code` varchar(50) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ;

CREATE TABLE `seq` (
  `name` varchar(20) DEFAULT NULL,
  `val` int(11) DEFAULT NULL
) ;

CREATE TABLE `statemaster` (
  `Id` int(11) NOT NULL,
  `State` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
);

CREATE TABLE `subjectmaster` (
  `Subject_Id` int(11) NOT NULL,
  `Subject` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Subject_Id`)
);

CREATE TABLE `admissioninquiry` (
  `Inquiry_Id` int(11) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Father_Name` varchar(100) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `Contact_No` varchar(20) DEFAULT NULL,
  `Application_Status` varchar(50) DEFAULT NULL,
  `Due_Date` date DEFAULT NULL,
  `Created_By` varchar(100) DEFAULT NULL,
  `Created_On` date DEFAULT NULL,
  `Updated_By` varchar(100) DEFAULT NULL,
  `Updated_On` date DEFAULT NULL,
  `Intrested_Branch_Id` int(11) NOT NULL,
  `FollowUp_Rquired` bit(1) DEFAULT NULL,
  PRIMARY KEY (`Inquiry_Id`),
  KEY `Intrested_Branch_Id` (`Intrested_Branch_Id`),
  CONSTRAINT `admissioninquiry_ibfk_1` FOREIGN KEY (`Intrested_Branch_Id`) REFERENCES `coursebranchmaster` (`Id`)
);

CREATE TABLE `studentdetail` (
  `File_No` varchar(100) NOT NULL,
  `Enroll_No` varchar(50) DEFAULT NULL,
  `Uni_Enroll_No` varchar(50) DEFAULT NULL,
  `Photo` blob,
  `First_Name` varchar(50) DEFAULT NULL,
  `Last_Name` varchar(50) DEFAULT NULL,
  `Father_Name` varchar(50) DEFAULT NULL,
  `Mother_Name` varchar(50) DEFAULT NULL,
  `Gender` varchar(20) DEFAULT NULL,
  `DOB` varchar(30) DEFAULT NULL,
  `Blood_Group` varchar(20) DEFAULT NULL,
  `Father_Occupation` varchar(50) DEFAULT NULL,
  `FixedLine_No` varchar(50) DEFAULT NULL,
  `Self_Mobile_No` varchar(20) DEFAULT NULL,
  `Parent_Mobile_No` varchar(20) DEFAULT NULL,
  `Gaurdian_Mobile_No` varchar(50) DEFAULT NULL,
  `Email_Id` varchar(50) DEFAULT NULL,
  `Gaurdian_Email_Id` varchar(50) DEFAULT NULL,
  `Hostel` bit(1) DEFAULT NULL,
  `Transportation` bit(1) DEFAULT NULL,
  `Academic_Year` int(11) DEFAULT NULL,
  `Semester` int(11) DEFAULT NULL,
  `Management_Approval` bit(1) DEFAULT NULL,
  `Fee_Paid` bit(1) DEFAULT NULL,
  `Category_Id` int(11) DEFAULT NULL,
  `Branch_Id` int(11) DEFAULT NULL,
  `Created_By` varchar(100) DEFAULT NULL,
  `Created_On` date DEFAULT NULL,
  `Updated_By` varchar(100) DEFAULT NULL,
  `Updated_On` date DEFAULT NULL,
  `Domicile_State_Id` int(11) DEFAULT NULL,
  `Scholarship` bit(1) DEFAULT NULL,
  `Remarks` varchar(500) DEFAULT NULL,
  `Course_Id` int(11) DEFAULT NULL,
  `Admission_Mode` varchar(100) DEFAULT NULL,
  `Referred_By` varchar(100) DEFAULT NULL,
  `Quota_Code` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`File_No`),
  UNIQUE KEY `Uni_Enroll_No` (`Uni_Enroll_No`),
  KEY `studentdetail_ibfk_4` (`Domicile_State_Id`),
  KEY `studentdetail_ibfk_1` (`Category_Id`),
  KEY `studentdetail_ibfk_2` (`Branch_Id`),
  KEY `studentdetail_ibfk_3` (`Course_Id`),
  CONSTRAINT `studentdetail_ibfk_1` FOREIGN KEY (`Category_Id`) REFERENCES `castecategorymaster` (`Id`),
  CONSTRAINT `studentdetail_ibfk_2` FOREIGN KEY (`Branch_Id`) REFERENCES `coursebranchmaster` (`Id`),
  CONSTRAINT `studentdetail_ibfk_3` FOREIGN KEY (`Course_Id`) REFERENCES `coursemaster` (`Id`),
  CONSTRAINT `studentdetail_ibfk_4` FOREIGN KEY (`Domicile_State_Id`) REFERENCES `statemaster` (`Id`)
);

CREATE TABLE `addressdetail` (
  `House_No` int(11) DEFAULT NULL,
  `Locality` varchar(50) DEFAULT NULL,
  `Landmark` varchar(50) DEFAULT NULL,
  `District` varchar(50) DEFAULT NULL,
  `City` varchar(50) DEFAULT NULL,
  `Pincode` int(11) DEFAULT NULL,
  `Address_Type` char(1) NOT NULL DEFAULT '',
  `State_Id` int(11) NOT NULL,
  `File_No` varchar(100) NOT NULL,
  PRIMARY KEY (`File_No`,`Address_Type`),
  KEY `addressdetail_ibfk_1` (`State_Id`),
  CONSTRAINT `addressdetail_ibfk_1` FOREIGN KEY (`State_Id`) REFERENCES `statemaster` (`Id`),
  CONSTRAINT `addressdetail_ibfk_2` FOREIGN KEY (`File_No`) REFERENCES `studentdetail` (`File_No`)
);


CREATE TABLE `academicdetail` (
  `University` varchar(100) DEFAULT NULL,
  `College_Name` varchar(100) DEFAULT NULL,
  `Passing_Year` varchar(20) DEFAULT NULL,
  `Percentage` decimal(10,0) DEFAULT NULL,
  `Roll_No` varchar(50) DEFAULT NULL,
  `Qualification_Id` int(11) NOT NULL,
  `File_No` varchar(100) NOT NULL,
  PRIMARY KEY (`Qualification_Id`,`File_No`),
  KEY `File_No` (`File_No`),
  CONSTRAINT `academicdetail_ibfk_1` FOREIGN KEY (`File_No`) REFERENCES `studentdetail` (`File_No`)
);

CREATE TABLE `qualificationsubjectdtl` (
  `Subject_Id` int(11) NOT NULL,
  `Qualification_Id` int(11),
  `Marks_Obtained` decimal(10,0) DEFAULT NULL,
  `Max_Marks` decimal(10,0) DEFAULT NULL,
  `File_No` varchar(100) NOT NULL,
  PRIMARY KEY (`Subject_Id`,`File_No`),
  KEY `Qualification_Id` (`Qualification_Id`),
  KEY `File_No` (`File_No`),
  CONSTRAINT `qualificationsubjectdtl_ibfk_1` FOREIGN KEY (`Qualification_Id`) REFERENCES `qualificationmaster` (`Id`),
  CONSTRAINT `qualificationsubjectdtl_ibfk_2` FOREIGN KEY (`File_No`) REFERENCES `studentdetail` (`File_No`),
  CONSTRAINT `qualificationsubjectdtl_ibfk_3` FOREIGN KEY (`Subject_Id`) REFERENCES `subjectmaster` (`Subject_Id`)
) ;

CREATE TABLE `admissiondiscountdtl` (
  `FeeHead_Id` int(11) NOT NULL,
  `Amount` decimal(10,0) DEFAULT NULL,
  `Percent` decimal(10,0) DEFAULT NULL,
  `File_No` varchar(100) NOT NULL,
  PRIMARY KEY (`FeeHead_Id`,`File_No`),
  KEY `File_No` (`File_No`),
  CONSTRAINT `admissiondiscountdtl_ibfk_1` FOREIGN KEY (`File_No`) REFERENCES `studentdetail` (`File_No`)
);


CREATE TABLE `branchpreference` (
  `Branch_Preference_Id` int(11) DEFAULT NULL,
  `File_No` varchar(100) NOT NULL,
  `Branch_Id` int(11) NOT NULL,
  PRIMARY KEY (`File_No`),
  KEY `Branch_Id` (`Branch_Id`),
  CONSTRAINT `branchpreference_ibfk_1` FOREIGN KEY (`Branch_Id`) REFERENCES `coursebranchmaster` (`Id`),
  CONSTRAINT `branchpreference_ibfk_2` FOREIGN KEY (`File_No`) REFERENCES `studentdetail` (`File_No`)
);



CREATE TABLE `consultantdetail` (
  `File_No` varchar(100) NOT NULL,
  `Consultant_Id` bigint(20) NOT NULL,
  `Consultancy_Agreed` varchar(10) DEFAULT NULL,
  `Payment_Mode` varchar(50) DEFAULT NULL,
  `Amount_To_Pay` decimal(10,0) DEFAULT NULL,
  `Due_Date` date DEFAULT NULL,
  PRIMARY KEY (`File_No`,`Consultant_Id`),
  KEY `Consultant_Id` (`Consultant_Id`),
  CONSTRAINT `consultantdetail_ibfk_1` FOREIGN KEY (`Consultant_Id`) REFERENCES `consultantmaster` (`Id`)
);


CREATE TABLE `consultantpaymentdetail` (
  `Amount` decimal(10,0) DEFAULT NULL,
  `Pay_Date` date DEFAULT NULL,
  `File_No` varchar(100) NOT NULL,
  PRIMARY KEY (`File_No`)
);

CREATE TABLE `counsellingdetail` (
  `File_No` varchar(200) DEFAULT NULL,
  `Counselling_Id` bigint(20) DEFAULT NULL,
  `Roll_No` varchar(100) DEFAULT NULL,
  `Rank` bigint(20) DEFAULT NULL,
  `Category_Rank` bigint(20) DEFAULT NULL,
  `Percentile` decimal(10,0) DEFAULT NULL,
  KEY `File_No` (`File_No`),
  KEY `Counselling_Id` (`Counselling_Id`),
  CONSTRAINT `counsellingdetail_ibfk_1` FOREIGN KEY (`File_No`) REFERENCES `studentdetail` (`File_No`),
  CONSTRAINT `counsellingdetail_ibfk_2` FOREIGN KEY (`Counselling_Id`) REFERENCES `counsellingmaster` (`Id`)
) ;

 CREATE TABLE `transportmaster` (
  `Route_Code` varchar(100) NOT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Threshold` varchar(200) DEFAULT NULL,
  `Price` double DEFAULT NULL,
  PRIMARY KEY (`Route_Code`)
);
 

CREATE TABLE `transportreservation` (
  `File_No` varchar(100) NOT NULL,
  `Fee_Paid` bit(1) DEFAULT NULL,
  `Route_Code` varchar(100),
  PRIMARY KEY (`File_No`),
  KEY `Route_Code` (`Route_Code`),
  CONSTRAINT `transportreservation_ibfk_1` FOREIGN KEY (`Route_Code`) REFERENCES `transportmaster` (`Route_Code`),
  CONSTRAINT `transportreservation_ibfk_2` FOREIGN KEY (`File_No`) REFERENCES `studentdetail` (`File_No`)
) ;

CREATE TABLE `transportallocation` (
  `File_No` varchar(200) NOT NULL,
  `Vehicle_Id` varchar(100),
  PRIMARY KEY (`File_No`),
  CONSTRAINT `transportallocation_ibfk_1` FOREIGN KEY (`File_No`) REFERENCES `studentdetail` (`File_No`)
);

CREATE TABLE `vehicledetail` (
  `Type` varchar(100) DEFAULT NULL,
  `Capacity` varchar(200) DEFAULT NULL,
  `Vehicle_No` varchar(200) DEFAULT NULL,
  `Route_Code` varchar(100) NOT NULL,
  `Vehicle_Id` varchar(100),
  PRIMARY KEY (`Vehicle_Id`),
  KEY `Route_Code` (`Route_Code`),
  CONSTRAINT `vehicledetail_ibfk_1` FOREIGN KEY (`Route_Code`) REFERENCES `transportmaster` (`Route_Code`)
); 

/*Alter in admissiondiscountdtl(add column discounttype) */


alter table admissiondiscountdtl add COLUMN Discount_Type varchar(20);

create table counsellinDetail (File_No varchar(200), Counselling_Id bigint, Roll_No varchar(100), Rank bigint, Category_Rank bigint, Percentile decimal);

/* adding Counselling table*/

alter table counsellindetail add FOREIGN KEY (File_No) REFERENCES studentdetail(File_No);
alter table counsellindetail add FOREIGN KEY (Counselling_Id) REFERENCES counsellingMaster(Id);


/*Altring StudentDetail*/
alter TABLE studentdetail add COLUMN Scholarship bit;

alter TABLE studentdetail add COLUMN Referred_By varchar(100);
alter TABLE studentdetail add COLUMN Remarks varchar(500);
alter table studentdetail add column Quota_Code varchar(200);
alter table studentdetail add column Admission_Mode char(1);

/*#######################################################################################  */
/* WORK OF TRANSPORT TABLE*/


ALTER TABLE TransportReservation
	ADD FOREIGN KEY (File_No)
	REFERENCES StudentDetail (File_No);

ALTER TABLE TransportAllocation
	ADD FOREIGN KEY (File_No)
	REFERENCES StudentDetail (File_No);

ALTER TABLE TransportReservation
ADD Primary KEY (File_No);

ALTER TABLE TransportAllocation	
  ADD Primary KEY (File_No);