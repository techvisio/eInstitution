CREATE DEFINER = 'root'@'localhost'
PROCEDURE einstitution.generateDiscountStagging(In v_file_no varchar(100))
BEGIN

declare v_fee_head	int;
declare v_amount	decimal;
declare v_percent	decimal;
declare done int;

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
end if;
           end loop igmLoop;
    close cur1;
END