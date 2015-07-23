# --- !Ups
ALTER TABLE student ADD COLUMN field VARCHAR(255) DEFAULT 'TEST'




# --- !Downs
ALTER TABLE student DROP COLUMN field