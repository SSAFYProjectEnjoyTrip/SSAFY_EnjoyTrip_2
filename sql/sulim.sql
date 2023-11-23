
ALTER TABLE board
ADD COLUMN star INT,
ADD COLUMN rfile VARCHAR(255);

select * from board;
select * from member;

delete from board where articleNo = 1;