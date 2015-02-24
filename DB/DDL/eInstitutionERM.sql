CREATE TABLE `subjectmaster` (
  `Subject_Id` int(11) NOT NULL,
  `Subject` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Subject_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `quotacodemaster` (
  `Id` int(11) NOT NULL,
  `Code` varchar(50) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `qualificationmaster` (
  `Id` int(11) NOT NULL,
  `QualifyingExam` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `statemaster` (
  `Id` int(11) NOT NULL,
  `State` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `transportmaster` (
  `Route_Code` varchar(100) NOT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Threshold` varchar(200) DEFAULT NULL,
  `Price` double DEFAULT NULL,
  PRIMARY KEY (`Route_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `castecategorymaster` (
  `Id` int(11) NOT NULL,
  `Category` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `counsellingmaster` (
  `Id` bigint(20) NOT NULL,
  `Counselling_Body` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `coursemaster` (
  `Id` int(11) NOT NULL,
  `Course` varchar(50) DEFAULT NULL,
  `Course_Type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `coursebranchmaster` (
  `Course_Id` int(11) NOT NULL,
  `Id` int(11) NOT NULL,
  `Branch` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Course_Id` (`Course_Id`),
  CONSTRAINT `coursebranchmaster_ibfk_1` FOREIGN KEY (`Course_Id`) REFERENCES `coursemaster` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `feediscountheadmaster` (
  `Head_Id` int(11) NOT NULL DEFAULT '0',
  `Head` varchar(100) DEFAULT NULL,
  `Type` varchar(100) DEFAULT NULL,
  `Parent_type_id` int(11) DEFAULT NULL,
  `Discount_Type` varchar(100) DEFAULT NULL,
  `Refund_Type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Head_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `feedetailmaster` (
  `COURSE` int(11) NOT NULL DEFAULT '0',
  `BRANCH` int(11) NOT NULL DEFAULT '0',
  `SEMESTER` int(11) DEFAULT NULL,
  `FEE_HEAD_ID` int(11) NOT NULL DEFAULT '0',
  `FEE_AMOUNT` double DEFAULT NULL,
  PRIMARY KEY (`COURSE`,`BRANCH`,`FEE_HEAD_ID`),
  KEY `FEE_HEAD_ID` (`FEE_HEAD_ID`),
  KEY `BRANCH` (`BRANCH`),
  CONSTRAINT `feedetailmaster_ibfk_1` FOREIGN KEY (`FEE_HEAD_ID`) REFERENCES `feediscountheadmaster` (`Head_Id`),
  CONSTRAINT `feedetailmaster_ibfk_2` FOREIGN KEY (`COURSE`) REFERENCES `coursemaster` (`Id`),
  CONSTRAINT `feedetailmaster_ibfk_3` FOREIGN KEY (`BRANCH`) REFERENCES `coursebranchmaster` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `consultantmaster` (
  `Id` bigint(20) NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Primary_Contact_No` varchar(50) DEFAULT NULL,
  `Secondary_contact_No` varchar(50) DEFAULT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `Email_Id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `hostelinventory` (
  `Type_Code` varchar(200) NOT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Threshold` int(11) DEFAULT NULL,
  `Price` double DEFAULT NULL,
  `Room_Capacity` int(11) DEFAULT NULL,
  PRIMARY KEY (`Type_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `roomtypedetail` (
  `Room_No` varchar(200) NOT NULL,
  `Type_Code` varchar(200) NOT NULL,
  PRIMARY KEY (`Room_No`),
  KEY `Type_Code` (`Type_Code`),
  CONSTRAINT `roomtypedetail_ibfk_1` FOREIGN KEY (`Type_Code`) REFERENCES `hostelinventory` (`Type_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `DOB` date DEFAULT NULL,
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
  `Academic_Year` varchar(50) DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `taskandfollowup` (
  `USER_ID` varchar(220) DEFAULT NULL,
  `TASK_ID` int(11) NOT NULL DEFAULT '0',
  `TASK_ENTRY` varchar(220) DEFAULT NULL,
  `STATUS` varchar(220) DEFAULT NULL,
  `ROLE` varchar(220) DEFAULT NULL,
  `REMARK` varchar(500) DEFAULT NULL,
  `PARENTTASK_ID` int(11) DEFAULT NULL,
  `DUE_DATE` date DEFAULT NULL,
  PRIMARY KEY (`TASK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `addressdetail` (
  `House_No` varchar(50) DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `admissiondiscountdtl` (
  `FeeHead_Id` int(11) NOT NULL,
  `Amount` decimal(10,0) DEFAULT NULL,
  `Percent` decimal(10,0) DEFAULT NULL,
  `File_No` varchar(100) NOT NULL,
  `Discount_Type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`FeeHead_Id`,`File_No`),
  KEY `File_No` (`File_No`),
  CONSTRAINT `admissiondiscountdtl_ibfk_1` FOREIGN KEY (`File_No`) REFERENCES `studentdetail` (`File_No`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `branchpreference` (
  `Branch_Preference_Id` int(11) DEFAULT NULL,
  `File_No` varchar(100) NOT NULL,
  `Branch_Id` int(11) NOT NULL,
  PRIMARY KEY (`File_No`),
  KEY `Branch_Id` (`Branch_Id`),
  CONSTRAINT `branchpreference_ibfk_1` FOREIGN KEY (`Branch_Id`) REFERENCES `coursebranchmaster` (`Id`),
  CONSTRAINT `branchpreference_ibfk_2` FOREIGN KEY (`File_No`) REFERENCES `studentdetail` (`File_No`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `consultantdetail` (
  `File_No` varchar(100) NOT NULL,
  `Consultant_Id` bigint(20) NOT NULL,
  `Consultancy_Agreed` varchar(10) DEFAULT NULL,
  `Payment_Mode` varchar(50) DEFAULT NULL,
  `Amount_To_Pay` decimal(10,0) DEFAULT NULL,
  `Due_Date` date DEFAULT NULL,
  KEY `Consultant_Id` (`Consultant_Id`),
  CONSTRAINT `consultantdetail_ibfk_1` FOREIGN KEY (`Consultant_Id`) REFERENCES `consultantmaster` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `consultantpaymentdetail` (
  `Amount` decimal(10,0) DEFAULT NULL,
  `Pay_Date` date DEFAULT NULL,
  `File_No` varchar(100) NOT NULL,
  PRIMARY KEY (`File_No`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `feetransaction` (
  `FEE_ID` int(11) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `USER` varchar(100) DEFAULT NULL,
  `AMOUNT` double DEFAULT NULL,
  `AMOUNT_TRANSACTION_TYPE` varchar(100) DEFAULT NULL,
  `File_No` varchar(100) DEFAULT NULL,
  KEY `FEE_ID` (`FEE_ID`),
  KEY `File_No` (`File_No`),
  CONSTRAINT `feetransaction_ibfk_1` FOREIGN KEY (`FEE_ID`) REFERENCES `feediscountheadmaster` (`Head_Id`),
  CONSTRAINT `feetransaction_ibfk_2` FOREIGN KEY (`File_no`) REFERENCES `studentdetail` (`File_No`),
  CONSTRAINT `feetransaction_ibfk_3` FOREIGN KEY (`File_No`) REFERENCES `studentdetail` (`File_No`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `hostelallocation` (
  `Wing` varchar(200) DEFAULT NULL,
  `Floor` varchar(100) DEFAULT NULL,
  `Block` varchar(100) DEFAULT NULL,
  `Name` varchar(200) DEFAULT NULL,
  `File_No` varchar(200) NOT NULL,
  `Room_No` varchar(200) NOT NULL,
  PRIMARY KEY (`File_No`),
  KEY `Room_No` (`Room_No`),
  CONSTRAINT `hostelallocation_ibfk_1` FOREIGN KEY (`Room_No`) REFERENCES `roomtypedetail` (`Room_No`),
  CONSTRAINT `hostelallocation_ibfk_2` FOREIGN KEY (`File_No`) REFERENCES `studentdetail` (`File_No`),
  CONSTRAINT `hostelallocation_ibfk_3` FOREIGN KEY (`Room_No`) REFERENCES `roomtypedetail` (`Room_No`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `hostelreservation` (
  `File_No` varchar(200) NOT NULL,
  `Fee_Paid` bit(1) DEFAULT NULL,
  `Type_Code` varchar(200) NOT NULL,
  `Allocation_Status` varchar(220) DEFAULT NULL,
  `Is_Active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`File_No`),
  KEY `Type_Code` (`Type_Code`),
  CONSTRAINT `hostelreservation_ibfk_1` FOREIGN KEY (`Type_Code`) REFERENCES `hostelinventory` (`Type_Code`),
  CONSTRAINT `hostelreservation_ibfk_2` FOREIGN KEY (`File_No`) REFERENCES `studentdetail` (`File_No`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `modulelog` (
  `WORK_FLOW_OPERATION` varchar(200) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `OPERATION` varchar(100) DEFAULT NULL,
  `ERROR_MESSAGE` varchar(2000) DEFAULT NULL,
  `ENTITY_ID` int(11) DEFAULT NULL,
  `DATE` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `qualificationsubjectdtl` (
  `Subject_Id` int(11) NOT NULL,
  `Qualification_Id` int(11) NOT NULL,
  `Marks_Obtained` decimal(10,0) DEFAULT NULL,
  `Max_Marks` decimal(10,0) DEFAULT NULL,
  `File_No` varchar(100) NOT NULL,
  PRIMARY KEY (`Subject_Id`,`File_No`),
  KEY `Qualification_Id` (`Qualification_Id`),
  KEY `File_No` (`File_No`),
  CONSTRAINT `qualificationsubjectdtl_ibfk_1` FOREIGN KEY (`Qualification_Id`) REFERENCES `qualificationmaster` (`Id`),
  CONSTRAINT `qualificationsubjectdtl_ibfk_2` FOREIGN KEY (`File_No`) REFERENCES `studentdetail` (`File_No`),
  CONSTRAINT `qualificationsubjectdtl_ibfk_3` FOREIGN KEY (`Subject_Id`) REFERENCES `subjectmaster` (`Subject_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `seq` (
  `name` varchar(20) DEFAULT NULL,
  `val` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




CREATE TABLE `studentfeestaging` (
  `FILE_NO` varchar(100) DEFAULT NULL,
  `SEMESTER` int(11) DEFAULT NULL,
  `FEE_GENERATED` char(1) DEFAULT NULL,
  `Academic_Year` varchar(20) DEFAULT NULL,
  `Amount` double DEFAULT NULL,
  `Created_By` varchar(50) DEFAULT NULL,
  `Updated_By` varchar(50) DEFAULT NULL,
  `Approved` bit(1) DEFAULT NULL,
  `Modified_Date` date DEFAULT NULL,
  `Created_Date` date DEFAULT NULL,
  `Feehead_Id` int(11) DEFAULT NULL,
  KEY `FILE_NO` (`FILE_NO`),
  KEY `Feehead_Id` (`Feehead_Id`),
  CONSTRAINT `studentfeestaging_ibfk_1` FOREIGN KEY (`FILE_NO`) REFERENCES `studentdetail` (`File_No`),
  CONSTRAINT `studentfeestaging_ibfk_2` FOREIGN KEY (`Feehead_Id`) REFERENCES `feediscountheadmaster` (`Head_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `transportallocation` (
  `File_No` varchar(200) NOT NULL,
  `Vehicle_Id` varchar(100) NOT NULL,
  PRIMARY KEY (`File_No`),
  CONSTRAINT `transportallocation_ibfk_1` FOREIGN KEY (`File_No`) REFERENCES `studentdetail` (`File_No`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `transportreservation` (
  `File_No` varchar(100) NOT NULL,
  `Fee_Paid` bit(1) DEFAULT NULL,
  `Route_Code` varchar(100) NOT NULL,
  `Allocation_Status` varchar(100) DEFAULT NULL,
  `Is_Active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`File_No`),
  KEY `Route_Code` (`Route_Code`),
  CONSTRAINT `transportreservation_ibfk_1` FOREIGN KEY (`Route_Code`) REFERENCES `transportmaster` (`Route_Code`),
  CONSTRAINT `transportreservation_ibfk_2` FOREIGN KEY (`File_No`) REFERENCES `studentdetail` (`File_No`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `vehicledetail` (
  `Type` varchar(100) DEFAULT NULL,
  `Capacity` varchar(200) DEFAULT NULL,
  `Vehicle_No` varchar(200) DEFAULT NULL,
  `Route_Code` varchar(100) NOT NULL,
  `Vehicle_Id` varchar(100) NOT NULL,
  PRIMARY KEY (`Vehicle_Id`),
  KEY `Route_Code` (`Route_Code`),
  CONSTRAINT `vehicledetail_ibfk_1` FOREIGN KEY (`Route_Code`) REFERENCES `transportmaster` (`Route_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `workflowfieldmapping` (
  `WORK_FLOW_STEP_ID` varchar(200) DEFAULT NULL,
  `WORK_FLOW_ID` varchar(200) DEFAULT NULL,
  `VISIBLE` bit(1) DEFAULT NULL,
  `VALID_VALUE` varchar(200) DEFAULT NULL,
  `TYPE` varchar(200) DEFAULT NULL,
  `TITLE` varchar(200) DEFAULT NULL,
  `MASTER_DATA_CODE` varchar(200) DEFAULT NULL,
  `MANDATORY_IND` bit(1) DEFAULT NULL,
  `FIELD_DESC_ID` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

