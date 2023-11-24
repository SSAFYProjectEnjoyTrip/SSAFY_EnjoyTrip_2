use `ssafyenjoy`;

ALTER TABLE board
ADD COLUMN star INT,
ADD COLUMN rfile VARCHAR(255);

select * from board;
select * from member;

delete from board where articleNo = 1;

commit;

select * from comment;

DROP TABLE IF EXISTS `ssafyenjoy`.`comment` ;

CREATE TABLE IF NOT EXISTS `ssafyenjoy`.`comment` (
  `commentNo` INT NOT NULL auto_increment,
  `userId` VARCHAR(45) NOT NULL,
  `articleNo` INT NOT NULL,
  `registerTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `commentContent` varchar(200) NOT NULL,
  PRIMARY KEY (`commentNo`)
  )
ENGINE = InnoDB;

alter table comment add foreign key(userId) references member(userId);
alter table comment add foreign key(articleNo) references board(articleNo);

insert into comment (userId, articleNo, commentContent) 
values ("ssafy", 1, "이건 댓글입니다");

insert into comment (userId, articleNo, commentContent) 
values ("admin", 2, "이건 댓글입니다");


select * from comment;

