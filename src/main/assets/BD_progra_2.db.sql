BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `User` (
	`idUser`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	NVARCHAR ( 100 ),
	`lastname`	NVARCHAR ( 100 ),
	`email`	NVARCHAR ( 100 ),
	`userName`	NVARCHAR ( 30 ) NOT NULL,
	`password`	NVARCHAR ( 30 ) NOT NULL,
	`blocked`	VARCHAR ( 1 ) DEFAULT 'N'
);
INSERT INTO `User` (idUser,name,lastname,email,userName,password,blocked) VALUES (1,'alejandra','gonzalez','a@hotmail.com','ale','123','N');
CREATE TABLE IF NOT EXISTS `MovieDetail` (
	`idMovieDetail`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`idMovie`	INTEGER NOT NULL,
	`idActor`	INTEGER NOT NULL,
	`tags`	TEXT,
	FOREIGN KEY(`idMovie`) REFERENCES `MovieDetail`(`idDirector`),
	FOREIGN KEY(`idActor`) REFERENCES `Actor`(`idActor`)
);
CREATE TABLE IF NOT EXISTS `Movie` (
	`idMovie`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	NVARCHAR ( 100 ) NOT NULL,
	`idDirector`	INTEGER NOT NULL,
	`idGenre`	INTEGER NOT NULL,
	`year`	INTEGER NOT NULL,
	`review`	INTEGER,
	`synopsis`	TEXT,
	`image`	BLOB,
	FOREIGN KEY(`idDirector`) REFERENCES `Director`(`idDirector`),
	FOREIGN KEY(`idGenre`) REFERENCES `Genre`(`idGenre`)
);
CREATE TABLE IF NOT EXISTS `Genre` (
	`idGenre`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	NVARCHAR ( 100 ) NOT NULL
);
CREATE TABLE IF NOT EXISTS `FavoriteMovies` (
	`idFavoriteMovies`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`idUser`	INTEGER NOT NULL,
	`idMovie`	INTEGER NOT NULL
);
CREATE TABLE IF NOT EXISTS `Director` (
	`idDirector`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	NVARCHAR ( 100 ) NOT NULL,
	`lastname`	NVARCHAR ( 100 )
);
CREATE TABLE IF NOT EXISTS `Comments` (
	`idComments`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`idMovie`	INTEGER NOT NULL,
	`idUser`	INTEGER NOT NULL,
	`body`	TEXT,
	`date`	DATETIME,
	FOREIGN KEY(`idMovie`) REFERENCES `Movie`(`idDirector`),
	FOREIGN KEY(`idUser`) REFERENCES `User`(`idUser`)
);
CREATE TABLE IF NOT EXISTS `Admin` (
	`idAdmin`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`userName`	NVARCHAR ( 30 ) NOT NULL,
	`password`	NVARCHAR ( 30 ) NOT NULL
);
CREATE TABLE IF NOT EXISTS `Actor` (
	`idActor`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	NVARCHAR ( 100 ) NOT NULL,
	`lastname`	NVARCHAR ( 100 )
);
COMMIT;
