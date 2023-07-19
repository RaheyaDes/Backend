--------------------------------------------------------
--  File created - Wednesday-July-19-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table USUARIO
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."USUARIO" 
   (	"LOGIN" VARCHAR2(20 BYTE), 
	"PASSWORD" VARCHAR2(100 BYTE), 
	"NOMBRE" VARCHAR2(50 BYTE), 
	"CLIENTE" FLOAT(126), 
	"EMAIL" VARCHAR2(50 BYTE), 
	"FECHAALTA" DATE DEFAULT SYSDATE, 
	"FECHABAJA" DATE, 
	"STATUS" CHAR(1 BYTE) DEFAULT 'A', 
	"INTENTOS" FLOAT(126) DEFAULT 0, 
	"FECHAREVOCADO" DATE, 
	"FECHA_VIGENCIA" DATE, 
	"NO_ACCESO" NUMBER(*,0), 
	"APELLIDO_PATERNO" VARCHAR2(50 BYTE), 
	"APELLIDO_MATERNO" VARCHAR2(50 BYTE), 
	"AREA" NUMBER(4,0), 
	"FECHAMODIFICACION" DATE DEFAULT SYSDATE
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into SYSTEM.USUARIO
SET DEFINE OFF;
Insert into SYSTEM.USUARIO (LOGIN,PASSWORD,NOMBRE,CLIENTE,EMAIL,FECHAALTA,FECHABAJA,STATUS,INTENTOS,FECHAREVOCADO,FECHA_VIGENCIA,NO_ACCESO,APELLIDO_PATERNO,APELLIDO_MATERNO,AREA,FECHAMODIFICACION) values ('NesDes','YxjkdBR/N38K1vKbcf//A1xIWlLqDgXIkXH2fDR3hvk=',null,543221,null,to_date('19-JUL-23','DD-MON-RR'),null,'B',0,null,null,1,'Torre','Lopez',3,to_date('19-JUL-23','DD-MON-RR'));
Insert into SYSTEM.USUARIO (LOGIN,PASSWORD,NOMBRE,CLIENTE,EMAIL,FECHAALTA,FECHABAJA,STATUS,INTENTOS,FECHAREVOCADO,FECHA_VIGENCIA,NO_ACCESO,APELLIDO_PATERNO,APELLIDO_MATERNO,AREA,FECHAMODIFICACION) values ('DanielMon','0KcxvlHop58FtIAFf+57N/vtXvv/EvethNapdLcvaE0=','Daniel',543221,'dan.mon@example.com',to_date('19-JUL-23','DD-MON-RR'),null,'A',0,null,null,2,'Monroy','Rodriguez',3,null);
--------------------------------------------------------
--  DDL for Index PK_USUARIO
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."PK_USUARIO" ON "SYSTEM"."USUARIO" ("LOGIN") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table USUARIO
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."USUARIO" ADD CONSTRAINT "PK_USUARIO" PRIMARY KEY ("LOGIN")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;