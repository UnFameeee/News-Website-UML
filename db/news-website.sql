
DROP DATABASE NEWS_WEBSITE;

CREATE DATABASE NEWS_WEBSITE;
USE NEWS_WEBSITE;

CREATE TABLE member(
	accountId NVARCHAR(300),
    email NVARCHAR(500),
    username NVARCHAR(500),
    password NVARCHAR(500),
    dateCreated NVARCHAR(500),
    role NVARCHAR(500),
    PRIMARY KEY(accountId)
);

CREATE TABLE viewer(
	accountId NVARCHAR(300),
	viewerId NVARCHAR(300),
	name NVARCHAR(500),
    gender NVARCHAR(100),
    phone NVARCHAR(500),
    address NVARCHAR(500),
    birthDate NVARCHAR(500),
    profileImg NVARCHAR(500),
    PRIMARY KEY(viewerId), 
    FOREIGN KEY(accountId) references member(accountId)
);

CREATE TABLE content_creator(
	accountId NVARCHAR(300),
	creatorId NVARCHAR(300),
    name NVARCHAR(500),
    gender NVARCHAR(100),
    phone NVARCHAR(500),
    address NVARCHAR(500),
    birthDate NVARCHAR(500),
    profileImg NVARCHAR(500),
    PRIMARY KEY(creatorId),
    FOREIGN KEY(accountId) references member(accountId)
);

CREATE TABLE censor(
	accountId NVARCHAR(300),
	censorId NVARCHAR(300),
    name NVARCHAR(500),
    gender NVARCHAR(100),
    phone NVARCHAR(500),
    address NVARCHAR(500),
    birthDate NVARCHAR(500),
    profileImg NVARCHAR(500),
	PRIMARY KEY(censorId),
    FOREIGN KEY(accountId) references member(accountId)
);

CREATE TABLE category(
	categoryId NVARCHAR(300),
    categoryName NVARCHAR(500),
    description NVARCHAR(2000),
    PRIMARY KEY(categoryId)
);

CREATE TABLE article(
	articleId NVARCHAR(300),
    title NVARCHAR(500),
    brief NVARCHAR(100),
    content NVARCHAR(500),
    author NVARCHAR(500),
    status NVARCHAR(500),
    categoryId NVARCHAR(300),
    PRIMARY KEY(articleId),
    FOREIGN KEY(categoryId) references category(categoryId)
);

CREATE TABLE collection(
	collectionId NVARCHAR(300),
    collectionName NVARCHAR(500),
    viewerId NVARCHAR(500) references viewer(viewerId),
    PRIMARY KEY(collectionId, viewerId),
    FOREIGN KEY(viewerId) references viewer(viewerId)
);

CREATE TABLE favorite_article(
	articleId NVARCHAR(300) references article(articleId),
    viewerId NVARCHAR(300) references viewer(viewerId),
    collectionId NVARCHAR(300) references collection(collectionId),
    PRIMARY KEY(collectionId, viewerId, articleId),
    FOREIGN KEY(articleId) references article(articleId),
    FOREIGN KEY(viewerId) references viewer(viewerId),
    FOREIGN KEY(collectionId) references collection(collectionId)
);