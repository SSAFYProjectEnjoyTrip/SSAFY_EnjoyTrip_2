-- MySQL Script generated by MySQL Workbench
-- Fri Nov  3 12:19:25 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ssafyenjoy
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ssafyenjoy` ;

-- -----------------------------------------------------
-- Schema ssafyenjoy
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafyenjoy` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ssafyenjoy` ;

-- -----------------------------------------------------
-- Table `ssafyenjoy`.`member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyenjoy`.`member` ;

CREATE TABLE IF NOT EXISTS `ssafyenjoy`.`member` (
  `userId` VARCHAR(45) NOT NULL,
  `userName` VARCHAR(45) NOT NULL,
  `userPwd` VARCHAR(45) NOT NULL,
  `emailId` VARCHAR(45) NOT NULL,
  `emailDomain` VARCHAR(45) NOT NULL,
  `joinDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `token` varchar(1000) null default null,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB;

insert into member (userId, userName, userPwd, emailId, emailDomain) values ("ssafy", "ssafy", "ssafy", "ssafy", "google.com");
select * from member;

delete from member;

-- -----------------------------------------------------
-- Table `ssafyenjoy`.`board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyenjoy`.`board` ;

CREATE TABLE IF NOT EXISTS `ssafyenjoy`.`board` (
  `articleNo` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(45) NOT NULL,
  `subject` VARCHAR(45) NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  `hit` INT DEFAULT 0,
  `registerTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`articleNo`),
  INDEX `userId_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `userId`
    FOREIGN KEY (`userId`)
    REFERENCES `ssafyenjoy`.`member` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

insert into board (articleNo, userId, subject, content) values (1, "ssafy", "hi", "bye");
select * from board;

-- -----------------------------------------------------
-- Table `ssafyenjoy`.`comment`
-- -----------------------------------------------------
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

insert into comment (commentNo, userId, articleNo, commentContent) values (1, "ssafy", 1, "이건 댓글입니다");

select * from comment;
--
-- Table structure for table `dongcode`
--

DROP TABLE IF EXISTS `dongcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dongcode` (
  `dongCode` varchar(10) NOT NULL,
  `sidoName` varchar(30) DEFAULT NULL,
  `gugunName` varchar(30) DEFAULT NULL,
  `dongName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`dongCode`),
  KEY `sidoName` (`sidoName`),
  CONSTRAINT `dongcode_ibfk_1` FOREIGN KEY (`sidoName`) REFERENCES `sidocode` (`sidoName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dongcode`
--

LOCK TABLES `dongcode` WRITE;
/*!40000 ALTER TABLE `dongcode` DISABLE KEYS */;
INSERT INTO `dongcode` VALUES ('1111010100','서울특별시','종로구','청운동'),('1111010200','서울특별시','종로구','신교동'),('1111010300','서울특별시','종로구','궁정동'),('1111010400','서울특별시','종로구','효자동'),('1111010500','서울특별시','종로구','창성동'),('1111010600','서울특별시','종로구','통의동'),('1111010700','서울특별시','종로구','적선동'),('1111010800','서울특별시','종로구','통인동'),('1111010900','서울특별시','종로구','누상동'),('1111011000','서울특별시','종로구','누하동'),('1111011100','서울특별시','종로구','옥인동'),('1111011200','서울특별시','종로구','체부동'),('1111011300','서울특별시','종로구','필운동'),('1111011400','서울특별시','종로구','내자동'),('1111011500','서울특별시','종로구','사직동'),('1111011600','서울특별시','종로구','도렴동'),('1111011700','서울특별시','종로구','당주동'),('1111011800','서울특별시','종로구','내수동'),('1111011900','서울특별시','종로구','세종로'),('1111012000','서울특별시','종로구','신문로1가'),('1111012100','서울특별시','종로구','신문로2가'),('1111012200','서울특별시','종로구','청진동'),('1111012300','서울특별시','종로구','서린동'),('1111012400','서울특별시','종로구','수송동'),('1111012500','서울특별시','종로구','중학동'),('1111012600','서울특별시','종로구','종로1가'),('1111012700','서울특별시','종로구','공평동'),('1111012800','서울특별시','종로구','관훈동'),('1111012900','서울특별시','종로구','견지동'),('1111013000','서울특별시','종로구','와룡동'),('1111013100','서울특별시','종로구','권농동'),('1111013200','서울특별시','종로구','운니동'),('1111013300','서울특별시','종로구','익선동'),('1111013400','서울특별시','종로구','경운동'),('1111013500','서울특별시','종로구','관철동'),('1111013600','서울특별시','종로구','인사동'),('1111013700','서울특별시','종로구','낙원동'),('1111013800','서울특별시','종로구','종로2가'),('1111013900','서울특별시','종로구','팔판동'),('1111014000','서울특별시','종로구','삼청동'),('1111014100','서울특별시','종로구','안국동'),('1111014200','서울특별시','종로구','소격동'),('1111014300','서울특별시','종로구','화동'),('1111014400','서울특별시','종로구','사간동'),('1111014500','서울특별시','종로구','송현동'),('1111014600','서울특별시','종로구','가회동'),('1111014700','서울특별시','종로구','재동'),('1111014800','서울특별시','종로구','계동'),('1111014900','서울특별시','종로구','원서동'),('1111015000','서울특별시','종로구','훈정동'),('1111015100','서울특별시','종로구','묘동'),('1111015200','서울특별시','종로구','봉익동'),('1111015300','서울특별시','종로구','돈의동'),('1111015400','서울특별시','종로구','장사동'),('1111015500','서울특별시','종로구','관수동'),('1111015600','서울특별시','종로구','종로3가'),('1111015700','서울특별시','종로구','인의동'),('1111015800','서울특별시','종로구','예지동'),('1111015900','서울특별시','종로구','원남동'),('1111016000','서울특별시','종로구','연지동'),('1111016100','서울특별시','종로구','종로4가'),('1111016200','서울특별시','종로구','효제동'),('1111016300','서울특별시','종로구','종로5가'),('1111016400','서울특별시','종로구','종로6가'),('1111016500','서울특별시','종로구','이화동'),('1111016600','서울특별시','종로구','연건동'),('1111016700','서울특별시','종로구','충신동'),('1111016800','서울특별시','종로구','동숭동'),('1111016900','서울특별시','종로구','혜화동'),('1111017000','서울특별시','종로구','명륜1가'),('1111017100','서울특별시','종로구','명륜2가'),('1111017200','서울특별시','종로구','명륜4가'),('1111017300','서울특별시','종로구','명륜3가'),('1111017400','서울특별시','종로구','창신동'),('1111017500','서울특별시','종로구','숭인동'),('1111017600','서울특별시','종로구','교남동'),('1111017700','서울특별시','종로구','평동'),('1111017800','서울특별시','종로구','송월동'),('1111017900','서울특별시','종로구','홍파동'),('1111018000','서울특별시','종로구','교북동'),('1111018100','서울특별시','종로구','행촌동'),('1111018200','서울특별시','종로구','구기동'),('1111018300','서울특별시','종로구','평창동'),('1111018400','서울특별시','종로구','부암동'),('1111018500','서울특별시','종로구','홍지동'),('1111018600','서울특별시','종로구','신영동'),('1111018700','서울특별시','종로구','무악동'),('1114010100','서울특별시','중구','무교동'),('1114010200','서울특별시','중구','다동'),('1114010300','서울특별시','중구','태평로1가'),('1114010400','서울특별시','중구','을지로1가'),('1114010500','서울특별시','중구','을지로2가'),('1114010600','서울특별시','중구','남대문로1가'),('1114010700','서울특별시','중구','삼각동'),('1114010800','서울특별시','중구','수하동'),('1114010900','서울특별시','중구','장교동'),('1114011000','서울특별시','중구','수표동'),('1114011100','서울특별시','중구','소공동'),('1114011200','서울특별시','중구','남창동'),('1114011300','서울특별시','중구','북창동'),('1114011400','서울특별시','중구','태평로2가'),('1114011500','서울특별시','중구','남대문로2가'),('1114011600','서울특별시','중구','남대문로3가'),('1114011700','서울특별시','중구','남대문로4가'),('1114011800','서울특별시','중구','남대문로5가'),('1114011900','서울특별시','중구','봉래동1가'),('1114012000','서울특별시','중구','봉래동2가'),('1114012100','서울특별시','중구','회현동1가'),('1114012200','서울특별시','중구','회현동2가'),('1114012300','서울특별시','중구','회현동3가'),('1114012400','서울특별시','중구','충무로1가'),('1114012500','서울특별시','중구','충무로2가'),('1114012600','서울특별시','중구','명동1가'),('1114012700','서울특별시','중구','명동2가'),('1114012800','서울특별시','중구','남산동1가'),('1114012900','서울특별시','중구','남산동2가'),('1114013000','서울특별시','중구','남산동3가'),('1114013100','서울특별시','중구','저동1가'),('1114013200','서울특별시','중구','충무로4가'),('1114013300','서울특별시','중구','충무로5가'),('1114013400','서울특별시','중구','인현동2가'),('1114013500','서울특별시','중구','예관동'),('1114013600','서울특별시','중구','묵정동'),('1114013700','서울특별시','중구','필동1가'),('1114013800','서울특별시','중구','필동2가'),('1114013900','서울특별시','중구','필동3가'),('1114014000','서울특별시','중구','남학동'),('1114014100','서울특별시','중구','주자동'),('1114014200','서울특별시','중구','예장동'),('1114014300','서울특별시','중구','장충동1가'),('1114014400','서울특별시','중구','장충동2가'),('1114014500','서울특별시','중구','광희동1가'),('1114014600','서울특별시','중구','광희동2가'),('1114014700','서울특별시','중구','쌍림동'),('1114014800','서울특별시','중구','을지로6가'),('1114014900','서울특별시','중구','을지로7가'),('1114015000','서울특별시','중구','을지로4가'),('1114015100','서울특별시','중구','을지로5가'),('1114015200','서울특별시','중구','주교동'),('1114015300','서울특별시','중구','방산동'),('1114015400','서울특별시','중구','오장동'),('1114015500','서울특별시','중구','을지로3가'),('1114015600','서울특별시','중구','입정동'),('1114015700','서울특별시','중구','산림동'),('1114015800','서울특별시','중구','충무로3가'),('1114015900','서울특별시','중구','초동'),('1114016000','서울특별시','중구','인현동1가'),('1114016100','서울특별시','중구','저동2가'),('1114016200','서울특별시','중구','신당동'),('1114016300','서울특별시','중구','흥인동'),('1114016400','서울특별시','중구','무학동'),('1114016500','서울특별시','중구','황학동'),('1114016600','서울특별시','중구','서소문동'),('1114016700','서울특별시','중구','정동'),('1114016800','서울특별시','중구','순화동'),('1114016900','서울특별시','중구','의주로1가'),('1114017000','서울특별시','중구','충정로1가'),('1114017100','서울특별시','중구','중림동'),('1114017200','서울특별시','중구','의주로2가'),('1114017300','서울특별시','중구','만리동1가'),('1114017400','서울특별시','중구','만리동2가'),('1117010100','서울특별시','용산구','후암동'),('1117010200','서울특별시','용산구','용산동2가'),('1117010300','서울특별시','용산구','용산동4가'),('1117010400','서울특별시','용산구','갈월동'),('1117010500','서울특별시','용산구','남영동'),('1117010600','서울특별시','용산구','용산동1가'),('1117010700','서울특별시','용산구','동자동'),('1117010800','서울특별시','용산구','서계동'),('1117010900','서울특별시','용산구','청파동1가'),('1117011000','서울특별시','용산구','청파동2가'),('1117011100','서울특별시','용산구','청파동3가'),('1117011200','서울특별시','용산구','원효로1가'),('1117011300','서울특별시','용산구','원효로2가'),('1117011400','서울특별시','용산구','신창동'),('1117011500','서울특별시','용산구','산천동'),('1117011600','서울특별시','용산구','청암동'),('1117011700','서울특별시','용산구','원효로3가'),('1117011800','서울특별시','용산구','원효로4가'),('1117011900','서울특별시','용산구','효창동'),('1117012000','서울특별시','용산구','도원동'),('1117012100','서울특별시','용산구','용문동'),('1117012200','서울특별시','용산구','문배동'),('1117012300','서울특별시','용산구','신계동'),('1117012400','서울특별시','용산구','한강로1가'),('1117012500','서울특별시','용산구','한강로2가'),('1117012600','서울특별시','용산구','용산동3가'),('1117012700','서울특별시','용산구','용산동5가'),('1117012800','서울특별시','용산구','한강로3가'),('1117012900','서울특별시','용산구','이촌동'),('1117013000','서울특별시','용산구','이태원동'),('1117013100','서울특별시','용산구','한남동'),('1117013200','서울특별시','용산구','동빙고동'),('1117013300','서울특별시','용산구','서빙고동'),('1117013400','서울특별시','용산구','주성동'),('1117013500','서울특별시','용산구','용산동6가'),('1117013600','서울특별시','용산구','보광동'),('1120010100','서울특별시','성동구','상왕십리동'),('1120010200','서울특별시','성동구','하왕십리동'),('1120010300','서울특별시','성동구','홍익동'),('1120010400','서울특별시','성동구','도선동'),('1120010500','서울특별시','성동구','마장동'),('1120010600','서울특별시','성동구','사근동'),('1120010700','서울특별시','성동구','행당동'),('1120010800','서울특별시','성동구','응봉동'),('1120010900','서울특별시','성동구','금호동1가'),('1120011000','서울특별시','성동구','금호동2가'),('1120011100','서울특별시','성동구','금호동3가'),('1120011200','서울특별시','성동구','금호동4가'),('1120011300','서울특별시','성동구','옥수동'),('1120011400','서울특별시','성동구','성수동1가'),('1120011500','서울특별시','성동구','성수동2가'),('1120011800','서울특별시','성동구','송정동'),('1120012200','서울특별시','성동구','용답동'),('1121510100','서울특별시','광진구','중곡동'),('1121510200','서울특별시','광진구','능동'),('1121510300','서울특별시','광진구','구의동'),('1121510400','서울특별시','광진구','광장동'),('1121510500','서울특별시','광진구','자양동'),('1121510700','서울특별시','광진구','화양동'),('1121510900','서울특별시','광진구','군자동'),('1123010100','서울특별시','동대문구','신설동'),('1123010200','서울특별시','동대문구','용두동'),('1123010300','서울특별시','동대문구','제기동'),('1123010400','서울특별시','동대문구','전농동'),('1123010500','서울특별시','동대문구','답십리동'),('1123010600','서울특별시','동대문구','장안동'),('1123010700','서울특별시','동대문구','청량리동'),('1123010800','서울특별시','동대문구','회기동'),('1123010900','서울특별시','동대문구','휘경동'),('1123011000','서울특별시','동대문구','이문동'),('1126010100','서울특별시','중랑구','면목동'),('1126010200','서울특별시','중랑구','상봉동'),('1126010300','서울특별시','중랑구','중화동'),('1126010400','서울특별시','중랑구','묵동'),('1126010500','서울특별시','중랑구','망우동'),('1126010600','서울특별시','중랑구','신내동'),('1129010100','서울특별시','성북구','성북동'),('1129010200','서울특별시','성북구','성북동1가'),('1129010300','서울특별시','성북구','돈암동'),('1129010400','서울특별시','성북구','동소문동1가'),('1129010500','서울특별시','성북구','동소문동2가'),('1129010600','서울특별시','성북구','동소문동3가'),('1129010700','서울특별시','성북구','동소문동4가'),('1129010800','서울특별시','성북구','동소문동5가'),('1129010900','서울특별시','성북구','동소문동6가'),('1129011000','서울특별시','성북구','동소문동7가'),('1129011100','서울특별시','성북구','삼선동1가'),('1129011200','서울특별시','성북구','삼선동2가'),('1129011300','서울특별시','성북구','삼선동3가'),('1129011400','서울특별시','성북구','삼선동4가'),('1129011500','서울특별시','성북구','삼선동5가'),('1129011600','서울특별시','성북구','동선동1가'),('1129011700','서울특별시','성북구','동선동2가'),('1129011800','서울특별시','성북구','동선동3가'),('1129011900','서울특별시','성북구','동선동4가'),('1129012000','서울특별시','성북구','동선동5가'),('1129012100','서울특별시','성북구','안암동1가'),('1129012200','서울특별시','성북구','안암동2가'),('1129012300','서울특별시','성북구','안암동3가'),('1129012400','서울특별시','성북구','안암동4가'),('1129012500','서울특별시','성북구','안암동5가'),('1129012600','서울특별시','성북구','보문동4가'),('1129012700','서울특별시','성북구','보문동5가'),('1129012800','서울특별시','성북구','보문동6가'),('1129012900','서울특별시','성북구','보문동7가'),('1129013000','서울특별시','성북구','보문동1가'),('1129013100','서울특별시','성북구','보문동2가'),('1129013200','서울특별시','성북구','보문동3가'),('1129013300','서울특별시','성북구','정릉동'),('1129013400','서울특별시','성북구','길음동'),('1129013500','서울특별시','성북구','종암동'),('1129013600','서울특별시','성북구','하월곡동'),('1129013700','서울특별시','성북구','상월곡동'),('1129013800','서울특별시','성북구','장위동'),('1129013900','서울특별시','성북구','석관동'),('1130510100','서울특별시','강북구','미아동'),('1130510200','서울특별시','강북구','번동'),('1130510300','서울특별시','강북구','수유동'),('1130510400','서울특별시','강북구','우이동'),('1132010500','서울특별시','도봉구','쌍문동'),('1132010600','서울특별시','도봉구','방학동'),('1132010700','서울특별시','도봉구','창동'),('1132010800','서울특별시','도봉구','도봉동'),('1135010200','서울특별시','노원구','월계동'),('1135010300','서울특별시','노원구','공릉동'),('1135010400','서울특별시','노원구','하계동'),('1135010500','서울특별시','노원구','상계동'),('1135010600','서울특별시','노원구','중계동'),('1138010100','서울특별시','은평구','수색동'),('1138010200','서울특별시','은평구','녹번동'),('1138010300','서울특별시','은평구','불광동'),('1138010400','서울특별시','은평구','갈현동'),('1138010500','서울특별시','은평구','구산동'),('1138010600','서울특별시','은평구','대조동'),('1138010700','서울특별시','은평구','응암동'),('1138010800','서울특별시','은평구','역촌동'),('1138010900','서울특별시','은평구','신사동'),('1138011000','서울특별시','은평구','증산동'),('1138011400','서울특별시','은평구','진관동'),('1141010100','서울특별시','서대문구','충정로2가'),('1141010200','서울특별시','서대문구','충정로3가'),('1141010300','서울특별시','서대문구','합동'),('1141010400','서울특별시','서대문구','미근동'),('1141010500','서울특별시','서대문구','냉천동'),('1141010600','서울특별시','서대문구','천연동'),('1141010700','서울특별시','서대문구','옥천동'),('1141010800','서울특별시','서대문구','영천동'),('1141010900','서울특별시','서대문구','현저동'),('1141011000','서울특별시','서대문구','북아현동'),('1141011100','서울특별시','서대문구','홍제동'),('1141011200','서울특별시','서대문구','대현동'),('1141011300','서울특별시','서대문구','대신동'),('1141011400','서울특별시','서대문구','신촌동'),('1141011500','서울특별시','서대문구','봉원동'),('1141011600','서울특별시','서대문구','창천동'),('1141011700','서울특별시','서대문구','연희동'),('1141011800','서울특별시','서대문구','홍은동'),('1141011900','서울특별시','서대문구','북가좌동'),('1141012000','서울특별시','서대문구','남가좌동'),('1144010100','서울특별시','마포구','아현동'),('1144010200','서울특별시','마포구','공덕동'),('1144010300','서울특별시','마포구','신공덕동'),('1144010400','서울특별시','마포구','도화동'),('1144010500','서울특별시','마포구','용강동'),('1144010600','서울특별시','마포구','토정동'),('1144010700','서울특별시','마포구','마포동'),('1144010800','서울특별시','마포구','대흥동'),('1144010900','서울특별시','마포구','염리동'),('1144011000','서울특별시','마포구','노고산동'),('1144011100','서울특별시','마포구','신수동'),('1144011200','서울특별시','마포구','현석동'),('1144011300','서울특별시','마포구','구수동'),('1144011400','서울특별시','마포구','창전동'),('1144011500','서울특별시','마포구','상수동'),('1144011600','서울특별시','마포구','하중동'),('1144011700','서울특별시','마포구','신정동'),('1144011800','서울특별시','마포구','당인동'),('1144012000','서울특별시','마포구','서교동'),('1144012100','서울특별시','마포구','동교동'),('1144012200','서울특별시','마포구','합정동'),('1144012300','서울특별시','마포구','망원동'),('1144012400','서울특별시','마포구','연남동'),('1144012500','서울특별시','마포구','성산동'),('1144012600','서울특별시','마포구','중동'),('1144012700','서울특별시','마포구','상암동'),('1147010100','서울특별시','양천구','신정동'),('1147010200','서울특별시','양천구','목동'),('1147010300','서울특별시','양천구','신월동'),('1150010100','서울특별시','강서구','염창동'),('1150010200','서울특별시','강서구','등촌동'),('1150010300','서울특별시','강서구','화곡동'),('1150010400','서울특별시','강서구','가양동'),('1150010500','서울특별시','강서구','마곡동'),('1150010600','서울특별시','강서구','내발산동'),('1150010700','서울특별시','강서구','외발산동'),('1150010800','서울특별시','강서구','공항동'),('1150010900','서울특별시','강서구','방화동'),('1150011000','서울특별시','강서구','개화동'),('1150011100','서울특별시','강서구','과해동'),('1150011200','서울특별시','강서구','오곡동'),('1150011300','서울특별시','강서구','오쇠동'),('1153010100','서울특별시','구로구','신도림동'),('1153010200','서울특별시','구로구','구로동'),('1153010300','서울특별시','구로구','가리봉동'),('1153010600','서울특별시','구로구','고척동'),('1153010700','서울특별시','구로구','개봉동'),('1153010800','서울특별시','구로구','오류동'),('1153010900','서울특별시','구로구','궁동'),('1153011000','서울특별시','구로구','온수동'),('1153011100','서울특별시','구로구','천왕동'),('1153011200','서울특별시','구로구','항동'),('1154510100','서울특별시','금천구','가산동'),('1154510200','서울특별시','금천구','독산동'),('1154510300','서울특별시','금천구','시흥동'),('1156010100','서울특별시','영등포구','영등포동'),('1156010200','서울특별시','영등포구','영등포동1가'),('1156010300','서울특별시','영등포구','영등포동2가'),('1156010400','서울특별시','영등포구','영등포동3가'),('1156010500','서울특별시','영등포구','영등포동4가'),('1156010600','서울특별시','영등포구','영등포동5가'),('1156010700','서울특별시','영등포구','영등포동6가'),('1156010800','서울특별시','영등포구','영등포동7가'),('1156010900','서울특별시','영등포구','영등포동8가'),('1156011000','서울특별시','영등포구','여의도동'),('1156011100','서울특별시','영등포구','당산동1가'),('1156011200','서울특별시','영등포구','당산동2가'),('1156011300','서울특별시','영등포구','당산동3가'),('1156011400','서울특별시','영등포구','당산동4가'),('1156011500','서울특별시','영등포구','당산동5가'),('1156011600','서울특별시','영등포구','당산동6가'),('1156011700','서울특별시','영등포구','당산동'),('1156011800','서울특별시','영등포구','도림동'),('1156011900','서울특별시','영등포구','문래동1가'),('1156012000','서울특별시','영등포구','문래동2가'),('1156012100','서울특별시','영등포구','문래동3가'),('1156012200','서울특별시','영등포구','문래동4가'),('1156012300','서울특별시','영등포구','문래동5가'),('1156012400','서울특별시','영등포구','문래동6가'),('1156012500','서울특별시','영등포구','양평동1가'),('1156012600','서울특별시','영등포구','양평동2가'),('1156012700','서울특별시','영등포구','양평동3가'),('1156012800','서울특별시','영등포구','양평동4가'),('1156012900','서울특별시','영등포구','양평동5가'),('1156013000','서울특별시','영등포구','양평동6가'),('1156013100','서울특별시','영등포구','양화동'),('1156013200','서울특별시','영등포구','신길동'),('1156013300','서울특별시','영등포구','대림동'),('1156013400','서울특별시','영등포구','양평동'),('1159010100','서울특별시','동작구','노량진동'),('1159010200','서울특별시','동작구','상도동'),('1159010300','서울특별시','동작구','상도1동'),('1159010400','서울특별시','동작구','본동'),('1159010500','서울특별시','동작구','흑석동'),('1159010600','서울특별시','동작구','동작동'),('1159010700','서울특별시','동작구','사당동'),('1159010800','서울특별시','동작구','대방동'),('1159010900','서울특별시','동작구','신대방동'),('1162010100','서울특별시','관악구','봉천동'),('1162010200','서울특별시','관악구','신림동'),('1162010300','서울특별시','관악구','남현동'),('1165010100','서울특별시','서초구','방배동'),('1165010200','서울특별시','서초구','양재동'),('1165010300','서울특별시','서초구','우면동'),('1165010400','서울특별시','서초구','원지동'),('1165010600','서울특별시','서초구','잠원동'),('1165010700','서울특별시','서초구','반포동'),('1165010800','서울특별시','서초구','서초동'),('1165010900','서울특별시','서초구','내곡동'),('1165011000','서울특별시','서초구','염곡동'),('1165011100','서울특별시','서초구','신원동'),('1168010100','서울특별시','강남구','역삼동'),('1168010300','서울특별시','강남구','개포동'),('1168010400','서울특별시','강남구','청담동'),('1168010500','서울특별시','강남구','삼성동'),('1168010600','서울특별시','강남구','대치동'),('1168010700','서울특별시','강남구','신사동'),('1168010800','서울특별시','강남구','논현동'),('1168011000','서울특별시','강남구','압구정동'),('1168011100','서울특별시','강남구','세곡동'),('1168011200','서울특별시','강남구','자곡동'),('1168011300','서울특별시','강남구','율현동'),('1168011400','서울특별시','강남구','일원동'),('1168011500','서울특별시','강남구','수서동'),('1168011800','서울특별시','강남구','도곡동'),('1171010100','서울특별시','송파구','잠실동'),('1171010200','서울특별시','송파구','신천동'),('1171010300','서울특별시','송파구','풍납동'),('1171010400','서울특별시','송파구','송파동'),('1171010500','서울특별시','송파구','석촌동'),('1171010600','서울특별시','송파구','삼전동'),('1171010700','서울특별시','송파구','가락동'),('1171010800','서울특별시','송파구','문정동'),('1171010900','서울특별시','송파구','장지동'),('1171011100','서울특별시','송파구','방이동'),('1171011200','서울특별시','송파구','오금동'),('1171011300','서울특별시','송파구','거여동'),('1171011400','서울특별시','송파구','마천동'),('1174010100','서울특별시','강동구','명일동'),('1174010200','서울특별시','강동구','고덕동'),('1174010300','서울특별시','강동구','상일동'),('1174010500','서울특별시','강동구','길동'),('1174010600','서울특별시','강동구','둔촌동'),('1174010700','서울특별시','강동구','암사동'),('1174010800','서울특별시','강동구','성내동'),('1174010900','서울특별시','강동구','천호동'),('1174011000','서울특별시','강동구','강일동');
/*!40000 ALTER TABLE `dongcode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guguncode`
--

DROP TABLE IF EXISTS `guguncode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guguncode` (
  `gugunCode` varchar(10) NOT NULL,
  `gugunName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`gugunCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guguncode`
--

LOCK TABLES `guguncode` WRITE;
/*!40000 ALTER TABLE `guguncode` DISABLE KEYS */;
INSERT INTO `guguncode` VALUES ('1111000000','종로구'),('1114000000','중구'),('1117000000','용산구'),('1120000000','성동구'),('1121500000','광진구'),('1123000000','동대문구'),('1126000000','중랑구'),('1129000000','성북구'),('1130500000','강북구'),('1132000000','도봉구'),('1135000000','노원구'),('1138000000','은평구'),('1141000000','서대문구'),('1144000000','마포구'),('1147000000','양천구'),('1150000000','강서구'),('1153000000','구로구'),('1154500000','금천구'),('1156000000','영등포구'),('1159000000','동작구'),('1162000000','관악구'),('1165000000','서초구'),('1168000000','강남구'),('1171000000','송파구'),('1174000000','강동구'),('2611000000','중구'),('2614000000','서구'),('2617000000','동구'),('2620000000','영도구'),('2623000000','부산진구'),('2626000000','동래구'),('2629000000','남구'),('2632000000','북구'),('2635000000','해운대구'),('2638000000','사하구'),('2641000000','금정구'),('2644000000','강서구'),('2647000000','연제구'),('2650000000','수영구'),('2653000000','사상구'),('2671000000','기장군'),('2711000000','중구'),('2714000000','동구'),('2717000000','서구'),('2720000000','남구'),('2723000000','북구'),('2726000000','수성구'),('2729000000','달서구'),('2771000000','달성군'),('2811000000','중구'),('2814000000','동구'),('2817700000','미추홀구'),('2818500000','연수구'),('2820000000','남동구'),('2823700000','부평구'),('2824500000','계양구'),('2826000000','서구'),('2871000000','강화군'),('2872000000','옹진군'),('2911000000','동구'),('2914000000','서구'),('2915500000','남구'),('2917000000','북구'),('2920000000','광산구'),('3011000000','동구'),('3014000000','중구'),('3017000000','서구'),('3020000000','유성구'),('3023000000','대덕구'),('3111000000','중구'),('3114000000','남구'),('3117000000','동구'),('3120000000','북구'),('3171000000','울주군'),('3611000000','세종특별자치시'),('4111000000','수원시'),('4111100000','수원시 장안구'),('4111300000','수원시 권선구'),('4111500000','수원시 팔달구'),('4111700000','수원시 영통구'),('4113000000','성남시'),('4113100000','성남시 수정구'),('4113300000','성남시 중원구'),('4113500000','성남시 분당구'),('4115000000','의정부시'),('4117000000','안양시'),('4117100000','안양시 만안구'),('4117300000','안양시 동안구'),('4119000000','부천시'),('4121000000','광명시'),('4122000000','평택시'),('4125000000','동두천시'),('4127000000','안산시'),('4127100000','안산시 상록구'),('4127300000','안산시 단원구'),('4128000000','고양시'),('4128100000','고양시 덕양구'),('4128500000','고양시 일산동구'),('4128700000','고양시 일산서구'),('4129000000','과천시'),('4131000000','구리시'),('4136000000','남양주시'),('4137000000','오산시'),('4139000000','시흥시'),('4141000000','군포시'),('4143000000','의왕시'),('4145000000','하남시'),('4146000000','용인시'),('4146100000','용인시 처인구'),('4146300000','용인시 기흥구'),('4146500000','용인시 수지구'),('4148000000','파주시'),('4150000000','이천시'),('4155000000','안성시'),('4157000000','김포시'),('4159000000','화성시'),('4161000000','광주시'),('4163000000','양주시'),('4165000000','포천시'),('4167000000','여주시'),('4180000000','연천군'),('4182000000','가평군'),('4183000000','양평군'),('4211000000','춘천시'),('4213000000','원주시'),('4215000000','강릉시'),('4217000000','동해시'),('4219000000','태백시'),('4221000000','속초시'),('4223000000','삼척시'),('4272000000','홍천군'),('4273000000','횡성군'),('4275000000','영월군'),('4276000000','평창군'),('4277000000','정선군'),('4278000000','철원군'),('4279000000','화천군'),('4280000000','양구군'),('4281000000','인제군'),('4282000000','고성군'),('4283000000','양양군'),('4311000000','청주시'),('4311100000','청주시 상당구'),('4311200000','청주시 서원구'),('4311300000','청주시 흥덕구'),('4311400000','청주시 청원구'),('4313000000','충주시'),('4315000000','제천시'),('4372000000','보은군'),('4373000000','옥천군'),('4374000000','영동군'),('4374500000','증평군'),('4375000000','진천군'),('4376000000','괴산군'),('4377000000','음성군'),('4380000000','단양군'),('4413000000','천안시'),('4413100000','천안시 동남구'),('4413300000','천안시 서북구'),('4415000000','공주시'),('4418000000','보령시'),('4420000000','아산시'),('4421000000','서산시'),('4423000000','논산시'),('4425000000','계룡시'),('4427000000','당진시'),('4471000000','금산군'),('4476000000','부여군'),('4477000000','서천군'),('4479000000','청양군'),('4480000000','홍성군'),('4481000000','예산군'),('4482500000','태안군'),('4511000000','전주시'),('4511100000','전주시 완산구'),('4511300000','전주시 덕진구'),('4513000000','군산시'),('4514000000','익산시'),('4518000000','정읍시'),('4519000000','남원시'),('4521000000','김제시'),('4571000000','완주군'),('4572000000','진안군'),('4573000000','무주군'),('4574000000','장수군'),('4575000000','임실군'),('4577000000','순창군'),('4579000000','고창군'),('4580000000','부안군'),('4611000000','목포시'),('4613000000','여수시'),('4615000000','순천시'),('4617000000','나주시'),('4623000000','광양시'),('4671000000','담양군'),('4672000000','곡성군'),('4673000000','구례군'),('4677000000','고흥군'),('4678000000','보성군'),('4679000000','화순군'),('4680000000','장흥군'),('4681000000','강진군'),('4682000000','해남군'),('4683000000','영암군'),('4684000000','무안군'),('4686000000','함평군'),('4687000000','영광군'),('4688000000','장성군'),('4689000000','완도군'),('4690000000','진도군'),('4691000000','신안군'),('4711000000','포항시'),('4711100000','포항시 남구'),('4711300000','포항시 북구'),('4713000000','경주시'),('4715000000','김천시'),('4717000000','안동시'),('4719000000','구미시'),('4721000000','영주시'),('4723000000','영천시'),('4725000000','상주시'),('4728000000','문경시'),('4729000000','경산시'),('4772000000','군위군'),('4773000000','의성군'),('4775000000','청송군'),('4776000000','영양군'),('4777000000','영덕군'),('4782000000','청도군'),('4783000000','고령군'),('4784000000','성주군'),('4785000000','칠곡군'),('4790000000','예천군'),('4792000000','봉화군'),('4793000000','울진군'),('4794000000','울릉군'),('4812000000','창원시'),('4812100000','창원시 의창구'),('4812300000','창원시 성산구'),('4812500000','창원시 마산합포구'),('4812700000','창원시 마산회원구'),('4812900000','창원시 진해구'),('4817000000','진주시'),('4822000000','통영시'),('4824000000','사천시'),('4825000000','김해시'),('4827000000','밀양시'),('4831000000','거제시'),('4833000000','양산시'),('4872000000','의령군'),('4873000000','함안군'),('4874000000','창녕군'),('4882000000','고성군'),('4884000000','남해군'),('4885000000','하동군'),('4886000000','산청군'),('4887000000','함양군'),('4888000000','거창군'),('4889000000','합천군'),('5011000000','제주시'),('5013000000','서귀포시');
/*!40000 ALTER TABLE `guguncode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sidocode`
--

DROP TABLE IF EXISTS `sidocode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sidocode` (
  `sidoCode` varchar(10) NOT NULL,
  `sidoName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`sidoCode`),
  UNIQUE KEY `sidoName` (`sidoName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sidocode`
--

LOCK TABLES `sidocode` WRITE;
/*!40000 ALTER TABLE `sidocode` DISABLE KEYS */;
INSERT INTO `sidocode` VALUES ('4200000000','강원도'),('4100000000','경기도'),('4800000000','경상남도'),('4700000000','경상북도'),('2900000000','광주광역시'),('2700000000','대구광역시'),('3000000000','대전광역시'),('2600000000','부산광역시'),('1100000000','서울특별시'),('3611000000','세종특별자치시'),('3100000000','울산광역시'),('2800000000','인천광역시'),('4600000000','전라남도'),('4500000000','전라북도'),('5000000000','제주특별자치도'),('4400000000','충청남도'),('4300000000','충청북도');
/*!40000 ALTER TABLE `sidocode` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- -----------------------------------------------------
-- Table `ssafyenjoy`.`Sido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyenjoy`.`sido` ;

CREATE TABLE IF NOT EXISTS `ssafyenjoy`.`sido` (
  `sidoCode` INT NOT NULL,
  `sidoName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`sidoCode`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafyenjoy`.`Gugun`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyenjoy`.`gugun` ;

CREATE TABLE IF NOT EXISTS `ssafyenjoy`.`gugun` (
  `sidoCode` INT NOT NULL,
  `gugunCode` INT NOT NULL,
  `gugunName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`gugunCode`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ssafyenjoy`.`Marker`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyenjoy`.`attraction_info` ;

CREATE TABLE IF NOT EXISTS `ssafyenjoy`.`attraction_info` (
  `contentTypeId` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `addr1` VARCHAR(45) NOT NULL,
  `addr2` VARCHAR(45) NULL,
  `firstImage` VARCHAR(45) NULL,
  `readCount` INT NOT NULL,
  `latitude` DOUBLE NOT NULL,
  `longitude` DOUBLE NOT NULL,
  `sidoCode` INT NOT NULL,
  `gugunCode` INT NOT NULL
  )
ENGINE = InnoDB;

insert into sido (sidoCode, sidoName) values (1, "서울");

select * from sido;


insert into gugun (sidoCode, gugunCode, gugunName) values (1, 1, 'tlqk');

delete from gugun;

select * from gugun;

insert into attraction_info (contentTypeId, title, addr1, addr2, firstImage, readCount, latitude, longitude, sidoCode, gugunCode) values (1, "Ehfkdl", "또라이", "13반", "근성맨", 2, 3.088, 1.008, 1, 1);

select * from attraction_info;

insert into member(userId, userName, userPwd, emailId, emailDomain, joinDate)
values('ssafy', '김싸피', '1234', 'ssafy', '@naver.com', '2000-10-10');

insert into member(userId, userName, userPwd, emailId, emailDomain, joinDate)
values('admin', '관리자', '1234', 'admin', '@naver.com', '2000-10-10');

insert into board(userId, subject, content)
values('ssafy', '제목', '내용');

insert into board(userId, subject, content)
values('admin', '공지1', '내용1');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIattraction_infoQUE_CHECKS=@OLD_UNIQUE_CHECKS;