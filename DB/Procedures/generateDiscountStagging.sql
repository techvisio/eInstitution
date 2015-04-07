CREATE DEFINER = 'root'@'localhost'
PROCEDURE einstitution.generateDiscountStagging(In v_file_no varchar(100))
BEGIN

declare v_fee_head	int;
declare v_amount	decimal;
declare v_percent	decimal;
declare v_parent_head_id	int;
declare done int;
declare v_parent_amount int;

declare cur1 cursor for select FeeHead_Id,Amount,Percent from admissiondiscountdtl where file_no=v_file_no;
declare continue handler for not found set done=1;

    set done = 0;
    open cur1;
    igmLoop: loop
        fetch cur1 into v_fee_head,v_amount,v_percent;
        if done = 1 then leave igmLoop; end if;
        if(v_amount>0) THEN
        insert into studentfeestaging(FILE_NO,
SEMESTER,
Amount,
Created_By,
created_date,
Feehead_Id)
select sd.File_No,sd.Semester,v_amount,'SYSTEM',now(),v_fee_head from studentdetail sd 
where sd.File_No=v_file_no;

[elseIf (v_percent>0) then  


select FDH.parent_type_id into v_parent_head_id from FeeDiscountHeadMaster FDH where FD.fee_Head_Id = v_fee_head;

set v_amount=(select fd.amout into v_parent_amount from feedetailmaster fd join studentDetail sd join on sd.session_id=fd.session_id and sd.centre_id=fd.centre_id and sd.shift_id=fd.shift_id and sd.course_id=fd.course and sd.branch_id=fd.branch where sd.file_No=v_file_no and fd.fee_head_id=v_parent_head_id)*v_percent/100;  



 insert into studentfeestaging(FILE_NO,
SEMESTER,
Amount,
Created_By,
created_date,
Feehead_Id)
select sd.File_No,sd.Semester,v_amount,'SYSTEM',now(),v_fee_head from studentdetail sd 
where sd.File_No=v_file_no;
]

end if;
           end loop igmLoop;
    close cur1;
END