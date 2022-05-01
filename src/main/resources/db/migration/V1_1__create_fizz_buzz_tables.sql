CREATE TABLE fizz_buzz (
    first_multiple           int     NOT NULL,
    second_multiple          int     NOT NULL,
    request_limit            int     NOT NULL,
    first_multiple_response  varchar NOT NULL,
    second_multiple_response varchar NOT NULL,
    request_count            int     NOT NULL,
    PRIMARY KEY (first_multiple, second_multiple, request_limit, first_multiple_response, second_multiple_response)
)