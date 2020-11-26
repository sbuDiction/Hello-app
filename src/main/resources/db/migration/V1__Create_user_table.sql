CREATE TABLE GREETINGS
(
    id SERIAL NOT NULL PRIMARY KEY,
    first_name TEXT NOT NULL,
    time_stamp TEXT NOT NULL,
    count_time INT,
    language_greeted_in TEXT NOT NULL
)