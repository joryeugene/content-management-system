set @admin = 1;
set @writer = 2;
set @fw = 1;
set @circuit = 2;
set @class = 3;

insert into `Page` (Title, Content, UserId)
	values ("About Us", "We make web pages, this time about a CrossFit gym", @admin);
insert into `Page` (Title, Content, UserId)
	values ("Contact", "If you have access to this, you likely know how to contact us", @admin);
insert into `Page` (Title, Content, UserId)
	values ("Writer Page", "Just a page created by another user", @admin + 1);
insert into `Nav` (PageId, Position, MenuName)
	values (1, 2, "About");
insert into `Nav` (PageId, Position, MenuName)
	values (2, 3, "Contact Us");
insert into `Nav` (PageId, Position, MenuName)
	values (3, 1, "Writers Page");
insert into `Post` (UserId, Title, Content, NumOfViews, StartDate, EndDate, CategoryId, Queued)
	values (@admin, "Admin first post", "Content of admin\'s first free weights post", 0, '2016-11-28', '2116-11-28', @fw, false);
insert into `Post` (UserId, Title, Content, NumOfViews, StartDate, EndDate, CategoryId, Queued)
	values (@admin, "Admin second post", "Content of admin\'s second post, this one to circuit", 0, '2016-11-28', '2116-11-28', @fw + 1, false);
insert into `Post` (UserId, Title, Content, NumOfViews, StartDate, EndDate, CategoryId, Queued)
	values (@writer, "Writer first post", "Content of writer\'s first post", 0, '2016-11-28', '2116-11-28', @fw, false);
insert into `Post` (UserId, Title, Content, NumOfViews, StartDate, EndDate, CategoryId, Queued)
	values (@writer, "Writer second post", "Content of writer\'s second post, start as queued", 0, '2016-11-28', '2116-11-28', @fw, true);
insert into `PostHashtag` (PostId, Hashtag)
	values (1, "#admin");
insert into `PostHashtag` (PostId, Hashtag)
	values (2, "#admin");
insert into `PostHashtag` (PostId, Hashtag)
	values (2, "#weights");
insert into `PostHashtag` (PostId, Hashtag)
	values (3, "#writer");
insert into `PostHashtag` (PostId, Hashtag)
	values (4, "#writer");
insert into `PostHashtag` (PostId, Hashtag)
	values (4, "#queued");
insert into `PostHashtag` (PostId, Hashtag)
	values (4, "#weights");
