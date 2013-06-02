 CREATE TABLE login (
	 id serial PRIMARY KEY,
	 name varchar(55),
	 pass text
 );
 
 CREATE TABLE authority (
	 id serial PRIMARY KEY,
	 loginId integer REFERENCES login (id),
	 roleName varchar(15)
 );
 
 ALTER TABLE login ADD CONSTRAINT un_login UNIQUE (name);
 
 CREATE TABLE exam (
	 id serial PRIMARY KEY,
	 examName text UNIQUE
 );

 create TABLE examEntry (
	id serial PRIMARY KEY,
	examId integer REFERENCES exam (id)
 );

 create TABLE word (
	id serial PRIMARY KEY,
	examEntryId integer REFERENCES examEntry (id),
	wordText text,
	lang integer
 );