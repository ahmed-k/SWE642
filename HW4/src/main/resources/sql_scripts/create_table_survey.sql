CREATE TABLE SURVEY
(
  ROW_ID number(6) NOT NULL,
  STUDENT_ID varchar2(10) NOT NULL,
  FIRST_NAME varchar2(50) NOT NULL,
  LAST_NAME varchar2(50) NOT NULL,
  CITY varchar2(50),
  STREET varchar2(50) NOT NULL,
  ZIP varchar2(5) NOT NULL,
  telephone varchar2(10),
  email varchar2(100),
  url varchar2(250),
  referral_source varchar2(100),
  campus varchar2(250),
  graduation_month varchar2(15),
  graduation_year varchar2(4),
  recommendation_likelihood varchar2(15),
  additional_comments  varchar2(1000)
);
ALTER TABLE SURVEY ADD(
CONSTRAINT  survey_pk PRIMARY KEY (ROW_ID)
);
CREATE SEQUENCE survey_pk_seq;
CREATE OR REPLACE TRIGGER survey_autoinc_trigger
BEFORE INSERT ON survey
For Each ROW
  BEGIN
    SELECT survey_pk_seq.nextval
    INTO :new.ROW_ID
    FROM DUAL;
  END ;
ALTER TABLE SURVEY
ADD CONSTRAINT uniqueness_constraint UNIQUE(STUDENT_ID);