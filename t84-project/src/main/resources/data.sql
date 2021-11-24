/*Roles*/
insert ignore into roles (id, create_at, description, name)
values (1,'2021-10-15 14:42:34.679000','user','ROLE_USER');
insert ignore into roles (id, create_at, description, name)
values (2,'2021-10-15 14:42:34.679000','admin','ROLE_ADMIN');

/*Users*/
/*Normal users*/
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (1,'2021-10-07 14:42:34.679000',false,'juan@email.com','Juan','Perez','$2a$10$XzXpyzilrFCLAbfUT3Fx6OBGVlA1FG.5ha1LfI3np9KFL2cbgwzDO','https://loremflickr.com/100/100',1);
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (2,'2021-10-08 14:42:34.679000',false,'alice@email.com','Alice','Bryant','$2a$12$GCghuCkoaMB9X0y2qKLE/.7NM44ybHGsvB4K/i5b8i31HcPmTeQmW','https://loremflickr.com/100/100',1);
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (3,'2021-10-09 14:42:34.679000',false,'sol@email.com','Sol','Rodriguez','$2a$10$U5.wzp0HB/O0gz.o7NmPCe4yFgYjn8XiM487uYCN4W2L5OviiVNlu','https://loremflickr.com/100/100',1);
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (4,'2021-10-10 14:42:34.679000',false,'daisy@email.com','Daisy','Simmons','$2a$12$w0dqeO9Lq07e8CZ/LvoRvuc0n7UJfYf8NtxTrv.XDM42Qbk0TC4L.','https://loremflickr.com/100/100',1);
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (5,'2021-10-11 14:42:34.679000',false,'alan@email.com','Alan','Paz','$2a$10$J5J.NzCG9j/dkWIUyLRYP.lwIGKZKxgRT03h2E/boQspaNz1Vy4YC','https://loremflickr.com/100/100',1);
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (6,'2021-10-12 14:42:34.679000',false,'luke@email.com','Luke','Clark','$2a$12$idhh47wbsYncAVca69ALPO8GNPiSRZvJAE6Cm6TmB2ZPzShe5e2lm','https://loremflickr.com/100/100',1);
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (7,'2021-10-13 14:42:34.679000',false,'oscar@email.com','Oscar','Gonzalez','$2a$10$mblgEEeQDLFr2Ba/Lo6E7ukScDWFiLR0bzurll8wyuqOQ.jbjL4V6','https://loremflickr.com/100/100',1);
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (8,'2021-10-14 14:42:34.679000',false,'david@email.com','David','Watson','$2a$12$u.k9WaXDPtCl8puYFFksuOmUCC.vkOM5bI4dep/yFzKlAMoA9LjtW','https://loremflickr.com/100/100',1);
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (9,'2021-10-15 14:42:34.679000',false,'silvia@email.com','Silvia','Ortiz','$2a$10$8lVbjAHNMzM9bq46PgFweeT4Z/8Qqvbc4bITqjPI711Oj/wacgzuS','https://loremflickr.com/100/100',1);
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (10,'2021-10-16 14:42:34.679000',false,'megan@email.com','Megan','Hunter','$2a$12$m3Rft4VeimsF/ht1jwOwKev7Y07obKNSL07eDUorzm7chE2Q27coq','https://loremflickr.com/100/100',1);

/*Admin users*/
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (11,'2021-10-07 14:42:34.679000',false,'roberto@email.com','Roberto','Gomez','$2a$10$U1.9K4LTY3F.TDymxo1TfeOpbowJWUaXxzrtH9I5u3PY1vIdvk1Oq','https://loremflickr.com/100/100',2);
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (12,'2021-10-08 14:42:34.679000',false,'abigail@email.com','Abigail','Holmes','$2a$12$WnedHbA.l7e4sQlpAzczc.miwfWPvMx9amRZ0CXbY0EHkDPQqz0rm','https://loremflickr.com/100/100',2);
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (13,'2021-10-09 14:42:34.679000',false,'maria@email.com','Maria','Rodriguez','$2a$10$NTUe.cCCZah1P9nFXooJR.xPvaJFD8M61FG5n0y.syuZkdGGOtILW','https://loremflickr.com/100/100',2);
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (14,'2021-10-10 14:42:34.679000',false,'jacob@email.com','Jacob','King','$2a$12$.TY87MxCrRhD5B.PIXEyU.rgZ13/LxHmUT/bhRKZu6ExjOLniIiAy','https://loremflickr.com/100/100',2);
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (15,'2021-10-11 14:42:34.679000',false,'juana@email.com','Juana','Martinez','$2a$10$yTkYY66qGfT23CCyrKVngOnBQzMCeZdKcxLrr28iVT8mgWUFmLtE2','https://loremflickr.com/100/100',2);
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (16,'2021-10-12 14:42:34.679000',false,'william@email.com','William','Cole','$2a$12$4CFG/f7lxup8cmt1ggfYSee6Mth07jZyh7azmfv7gBh2vwuUhFsfe','https://loremflickr.com/100/100',2);
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (17,'2021-10-13 14:42:34.679000',false,'leonel@email.com','Leonel','Gonzalez','$2a$10$RlckcWaQe4ARy6Qm9X83nOibds8tD2YHqTotS8GfTIowvoTvPbOf6','https://loremflickr.com/100/100',2);
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (18,'2021-10-14 14:42:34.679000',false,'amelia@email.com','Amelia','Thompson','$2a$12$M3dXaPka4BJ48EzXMakrge.1NJldAdEIA3URkMgbbziH9o2E6c8K6','https://loremflickr.com/100/100',2);
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (19,'2021-10-15 14:42:34.679000',false,'luisa@email.com','Luisa','Torres','$2a$10$NHRLQdhAudZDQT.aqkzV9.kkOcV/7Yhq0lCn8QmW5gMEN8trmGDqO','https://loremflickr.com/100/100',2);
insert ignore into users (id, create_at, deleted, email, first_name, last_name, password, photo, role_id)
values (20,'2021-10-16 14:42:34.679000',false,'charles@email.com','Charles','Walker','$2a$12$1BDJUP6.QZOVzNgaZfijwunJ1Bbhlb.2sFoYY3N28c87sONBmTYE2','https://loremflickr.com/100/100',2);

/*Activities*/
insert ignore into activities (id,content,created_at,deleted,image,name,updated_at)
values (1,'actividad 1','2021-10-16 14:42:34.679000',false,'activity1.png','activity 1', null);
insert ignore into activities (id,content,created_at,deleted,image,name,updated_at)
values (2,'actividad 2','2021-10-16 14:42:34.679000' ,false,'activity2.png','activity 2', null);
insert ignore into activities (id,content,created_at,deleted,image,name,updated_at)
values (3,'actividad 3','2021-10-16 14:42:34.679000' ,false,'activity3.png','activity 3', null);
insert ignore into activities (id,content,created_at,deleted,image,name,updated_at)
values(4,'actividad 4','2021-10-16 14:42:34.679000' ,false,'activity4.png','activity 4', null);
insert ignore into activities (id,content,created_at,deleted,image,name,updated_at)
values(5,'actividad 5','2021-10-16 14:42:34.679000' ,false,'activity5.png','activity 5', null);
