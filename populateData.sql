set @admin = 1;
set @writer = 2;
set @fw = 1;
set @circuit = 2;
set @class = 3;
set @wods = 4;
set @events = 5;
set @tips = 6;

insert into `Page` (Title, Content, UserId)
	values ("About Us", "We make web pages, this time about a CrossFit gym", @admin);
insert into `Page` (Title, Content, UserId)
	values ("Contact",
		'
		<h3>Super Duper Title</h3>

		<img src="http://placekitten.com/g/500/300" alt="Kitten">

		<p>"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."</p>

		<ol>
		  <li>Coffee</li>
		  <li>Tea</li>
		  <li>Milk</li>
		</ol>

		<ul>
		  <li>Coffee</li>
		  <li>Tea</li>
		  <li>Milk</li>
		</ul>

		<p>"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."</p>
		',
		 @admin); -- test html tags
insert into `Page` (Title, Content, UserId)
	values ("Writer Page", "Just a page created by another user", @admin + 1);
insert into `Nav` (PageId, Position, MenuName)
	values (1, 1, "About");
insert into `Nav` (PageId, Position, MenuName)
	values (2, 3, "Contact Us");
insert into `Nav` (PageId, Position, MenuName)
	values (3, 2, "Writers Page");
insert into `Post` (UserId, Title, Content, NumOfViews, StartDate, EndDate, CategoryId, Queued)
	values (@admin, "Admin first post", "Content of admin\'s first free weights post", 0, '2016-11-28', '2116-11-28', @fw, false);
insert into `Post` (UserId, Title, Content, NumOfViews, StartDate, EndDate, CategoryId, Queued)
	values (@admin, "Admin second post", "Content of admin\'s second post, this one to circuit", 0, '2016-11-28', '2116-11-28', @fw + 1, false);
insert into `Post` (UserId, Title, Content, NumOfViews, StartDate, EndDate, CategoryId, Queued)
	values (@writer, "Writer first post", "Content of writer\'s first post", 0, '2016-11-28', '2116-11-28', @class, false);
insert into `Post` (UserId, Title, Content, NumOfViews, StartDate, EndDate, CategoryId, Queued)
	values (@writer, "Writer second post", "Content of writer\'s second post, start as queued", 0, '2016-11-28', '2116-11-28', @fw, true);
insert into `Post` (UserId, Title, Content, NumOfViews, StartDate, EndDate, CategoryId, Queued)
	values (@admin, "Awesome Blog Post", '<h3>Super Duper Title</h3><img src="http://placekitten.com/g/500/300" alt="Kitten"><p>"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."</p><ol><li>Coffee</li><li>Tea</li><li>Milk</li></ol><ul><li>Coffee</li><li>Tea</li><li>Milk</li></ul><p>"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."</p>',	35, '2016-11-20', '2116-11-28', @tips, false);
insert into `Post` (UserId, Title, Content, NumOfViews, StartDate, EndDate, CategoryId, Queued)
	values (@admin, "Sweet Stuff",
		'
		<h3>Super Duper</h3>

		<img src="http://placekitten.com/g/500/300" alt="Kitten">

		<p>"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."</p>

		<ol>
		  <li>Coffee</li>
		  <li>Tea</li>
		  <li>Milk</li>
		</ol>

		<ul>
		  <li>Coffee</li>
		  <li>Tea</li>
		  <li>Milk</li>
		</ul>

		<p>"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."</p>
		',
		35, '2016-11-18', '2116-11-28', @events, false);
insert into `Post` (UserId, Title, Content, NumOfViews, StartDate, EndDate, CategoryId, Queued)
	values (@admin, "AWESOME MUSCLES",
		'<h3>Super MUSCLE MAN</h3>

		<img src="http://placekitten.com/g/500/300" alt="Kitten">

		<p>"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."</p>

		<ol>
		  <li>Coffee</li>
		  <li>Tea</li>
		  <li>Milk</li>
		</ol>

		<ul>
		  <li>Coffee</li>
		  <li>Tea</li>
		  <li>Milk</li>
		</ul>

		<p>"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."</p>
		',
		35, '2016-11-15', '2116-11-28', @wods, false);
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
insert into `PostHashtag` (PostId, Hashtag)
	values (5, "#weights");
insert into `PostHashtag` (PostId, Hashtag)
	values (6, "#weights");
insert into `PostHashtag` (PostId, Hashtag)
	values (7, "#weights");
insert into `PostHashtag` (PostId, Hashtag)
	values (7, "#super");
