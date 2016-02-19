
create sequence seq_city;
create sequence seq_hotel;
create sequence seq_review;


CREATE TABLE "public"."city" (
  "id" int8 DEFAULT nextval('seq_city'::regclass) NOT NULL,
  "country" varchar(255) COLLATE "default" NOT NULL,
  "map" varchar(255) COLLATE "default" NOT NULL,
  "name" varchar(255) COLLATE "default" NOT NULL,
  "state" varchar(255) COLLATE "default" NOT NULL,
  CONSTRAINT "pk_city" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE)
;

CREATE TABLE "public"."hotel" (
  "id" int8 DEFAULT nextval('seq_hotel'::regclass) NOT NULL,
  "address" varchar(255) COLLATE "default" NOT NULL,
  "name" varchar(255) COLLATE "default" NOT NULL,
  "zip" varchar(255) COLLATE "default" NOT NULL,
  "city_id" int8 NOT NULL,
  CONSTRAINT "pk_hotel" PRIMARY KEY ("id"),
  CONSTRAINT "fk_hotel_cityid" FOREIGN KEY ("city_id") REFERENCES "public"."city" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT "uk_hotel_cityid_name" UNIQUE ("city_id", "name")
)
WITH (OIDS=FALSE)
;

CREATE TABLE "public"."review" (
"id" int8 DEFAULT nextval('seq_review'::regclass) NOT NULL,
"check_in_date" date NOT NULL,
"details" varchar(5000) COLLATE "default" NOT NULL,
"idx" int4 NOT NULL,
"rating" int4 NOT NULL,
"title" varchar(255) COLLATE "default" NOT NULL,
"trip_type" int4 NOT NULL,
"hotel_id" int8 NOT NULL,
CONSTRAINT "pk_review" PRIMARY KEY ("id"),
CONSTRAINT "fk_review_hotelid" FOREIGN KEY ("hotel_id") REFERENCES "public"."hotel" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION
)
WITH (OIDS=FALSE)
;


ALTER TABLE city ALTER COLUMN id SET DEFAULT nextval('seq_city'::regclass);
ALTER TABLE hotel ALTER COLUMN id SET DEFAULT nextval('seq_hotel'::regclass);
ALTER TABLE review ALTER COLUMN id SET DEFAULT nextval('seq_review'::regclass);
