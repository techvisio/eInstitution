


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

#########################################################################


/* add new column(Allocation_Status) in hostelReservation/also done in transport table manually*/

alter table hostelreservation add column (Allocation_Status varchar(220));


/* add new column(Is_Active bit) in hostelReservation/also done in transport table manually*/

alter table hostelreservation add column (Is_Active bit);


 

#######################################################################################
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