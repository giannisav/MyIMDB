INSERT INTO user (user_id, firstname, lastname, email, username, password, role)
VALUES (1, 'RandomName1', 'RandomLastname1', 'test1@mail.com', 'Spock', 'pass1234', 'USER');
INSERT INTO user (user_id, firstname, lastname, email, username, password, role)
VALUES (2, 'RandomName2', 'RandomLastname2', 'test2@mail.com', 'Princess Amidala', 'pass1234', 'USER');
INSERT INTO user (user_id, firstname, lastname, email, username, password, role)
VALUES (3, 'RandomName3', 'RandomLastname3', 'test3@mail.com', 'Picard', 'pass1234', 'USER');
INSERT INTO user (user_id, firstname, lastname, email, username, password, role)
VALUES (4, 'RandomName4', 'RandomLastname4', 'test4@mail.com', 'Count Dooku', 'pass1234', 'USER');


INSERT INTO movie (movie_id, title, director, year_of_publication, description, user, likes, dislikes, version,
                   created_at)
VALUES (1, 'Batman: The dark knight rises', 'director1', 2001, 'I am Batman', 1, 2, 0, 0, '2019-01-01');
INSERT INTO movie (movie_id, title, director, year_of_publication, description, user, likes, dislikes, version,
                   created_at)
VALUES (2, 'Superman: The man of steel', 'director2', 2002, 'I am Superman', 2, 1, 1, 0, '2019-02-02');
INSERT INTO movie (movie_id, title, director, year_of_publication, description, user, likes, dislikes, version,
                   created_at)
VALUES (3, 'Spiderman Homecoming', 'director3', 2003, 'I am Spider-man', 3, 1, 1, 0, '2019-03-03');
INSERT INTO movie (movie_id, title, director, year_of_publication, description, user, likes, dislikes, version,
                   created_at)
VALUES (4, 'Star Trek: The final frontier', 'director4', 2004, 'Live long and prosper', 1, 1, 1, 0, '2019-04-04');
INSERT INTO movie (movie_id, title, director, year_of_publication, description, user, likes, dislikes, version,
                   created_at)
VALUES (5, 'Star Wars: Empire strikes back', 'director5', 2005, 'Luke you are my son', 2, 1, 1, 0, '2019-05-05');
INSERT INTO movie (movie_id, title, director, year_of_publication, description, user, likes, dislikes, version,
                   created_at)
VALUES (6, 'The legend of Zoro', 'director6', 2006, 'Z', 3, 1, 1, 0, '2019-06-06');
INSERT INTO movie (movie_id, title, director, year_of_publication, description, user, likes, dislikes, version,
                   created_at)
VALUES (7, 'Casa de pappel', 'director7', 2007, 'Dali masks and red suits', 3, 0, 3, 0, '2019-07-07');


INSERT INTO vote (user_id, movie_id, rate)
VALUES (4, 1, 'UPVOTE');
INSERT INTO vote (user_id, movie_id, rate)
VALUES (4, 2, 'UPVOTE');
INSERT INTO vote (user_id, movie_id, rate)
VALUES (4, 3, 'UPVOTE');
INSERT INTO vote (user_id, movie_id, rate)
VALUES (4, 4, 'UPVOTE');
INSERT INTO vote (user_id, movie_id, rate)
VALUES (4, 5, 'UPVOTE');
INSERT INTO vote (user_id, movie_id, rate)
VALUES (4, 6, 'UPVOTE');
INSERT INTO vote (user_id, movie_id, rate)
VALUES (1, 3, 'DOWNVOTE');
INSERT INTO vote (user_id, movie_id, rate)
VALUES (1, 6, 'UPVOTE');
INSERT INTO vote (user_id, movie_id, rate)
VALUES (2, 1, 'UPVOTE');
INSERT INTO vote (user_id, movie_id, rate)
VALUES (2, 2, 'DOWNVOTE');
INSERT INTO vote (user_id, movie_id, rate)
VALUES (2, 3, 'DOWNVOTE');
INSERT INTO vote (user_id, movie_id, rate)
VALUES (2, 4, 'DOWNVOTE');
INSERT INTO vote (user_id, movie_id, rate)
VALUES (2, 5, 'DOWNVOTE');
INSERT INTO vote (user_id, movie_id, rate)
VALUES (2, 6, 'DOWNVOTE');
INSERT INTO vote (user_id, movie_id, rate)
VALUES (3, 6, 'DOWNVOTE');
