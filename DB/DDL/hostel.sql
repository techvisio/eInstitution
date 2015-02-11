


/* Create Foreign Keys */
ALTER TABLE hostelallocation
ADD FOREIGN KEY (File_No)
REFERENCES studentdetail(File_No)
;


ALTER TABLE HostelReservation
	
  ADD FOREIGN KEY (File_No)
	REFERENCES StudentDetail (File_No)
;

ALTER TABLE HOSTELRESERVATION MODIFY COLUMN Fee_Paid BIT;

ALTER TABLE hostelallocation
ADD FOREIGN KEY (ROOM_NO)
REFERENCES roomtypedetail (ROOM_NO);



/* Alter primary key*/
ALTER TABLE  HostelAllocation ADD PRIMARY KEY(File_No);



ALTER TABLE  HOSTELRESERVATION ADD PRIMARY KEY(File_No);

ALTER TABLE  ROOMTYPEDETAIL ADD PRIMARY KEY(ROOM_NO);

/*#########################################################################*/


/*11-feb-2015 add new column(Allocation_Status) in hostelReservation/also done in transport table manually*/

alter table hostelreservation add column (Allocation_Status varchar(220));


/* add new column(Is_Active bit) in hostelReservation/also done in transport table manually*/

alter table hostelreservation add column (Is_Active bit);


 
/*#####################################################################################*/

/*11-feb-2015 room avalable in hostel*/
SELECT HI.PRICE,HI.THRESHOLD,HI.TYPE_CODE,COUNT(HR.TYPE_CODE )AS RESERVED_ROOM,HI.THRESHOLD-COUNT(HR.TYPE_CODE ) AS AVAILABLE FROM hostelinventory HI JOIN hostelreservation HR ON HI.Type_Code = HR.Type_Code GROUP BY HR.Type_Code;

/* ###############################################################################################*/

/*11-feb-2015 ModuleLog */

CREATE TABLE MODULELOG 
(WORK_FLOW_OPERATION VARCHAR(200),
USER_ID INTEGER,
OPERATION VARCHAR(100),
ERROR_MESSAGE VARCHAR(2000),
ENTITY_ID INTEGER,
DATE DATE);

/* #########################################################################################*/

/*11-feb-2015 TaskAndFollowup*/

CREATE TABLE taskandfollowup 
(USER_ID VARCHAR(220), 
TASK_ID INTEGER, 
TASK_ENTRY VARCHAR, 
STATUS VARCHAR(220), 
ROLE VARCHAR(220), 
REMARK VARCHAR(220), 
REMARK VARCHAR(500), 
PARENTTASK_ID INTEGER, 
DUE_DATE DATE);

/*11-feb-2015 Add primary key in taskandfollowup table*/
ALTER TABLE taskandfollowup ADD PRIMARY KEY (TASK_ID);

/* #########################################################################################*/

/*11-feb-2015 WorkFlowFieldMapping */

CREATE TABLE workflowfieldmapping 
(WORK_FLOW_STEP_ID VARCHAR(200),
 WORK_FLOW_ID VARCHAR(200),
 VISIBLE BIT,
 VALID_VALUE VARCHAR(200),
 TYPE VARCHAR(200),
 TITLE VARCHAR(200),
 MASTER_DATA_CODE VARCHAR(200),
 MANDATORY_IND BIT,
 FIELD_DESC_ID VARCHAR(200));
