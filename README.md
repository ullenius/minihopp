# MiniHopp URL Shortener

A Java URL Shortener microservice. SQL and NoSQL (redis) support.

It's my implementation of freeCodeCamp's project.

MiniHopp is named after Lille Skutt's son in the Swedish comic Bamse. First
public release was 2019-02-19.

## Some features
* 100% Java
* REST web microservice
* Supports SQL (using JPA and object-relational mapping)
* NoSQL support (using Redis)
* JSON


## Usage

`POST [project_url]/minihopp/` to obtain a JSON-object containing the Short URL and the original long format URL.

`GET [project_url]/minihopp/short_url_id` to be re-directed to the corresponding URL.


## Development
MiniHopp uses various tools to ease the development process
* Maven is used to resolve dependencies and build MiniHopp
* Jedis is used for Redis-support
* Java EE 8 is used for SQL (EclipseLink)
* RestEasy is used for JAX-RS

## Building
Run
```
mvn package
```
to build the WAR-file.

* You need to set up your database manually through your application server of choice. 

* The JNDI-name is: *'java:/MiniHoppDatabase'*

* MiniHopp works fine using WildFly.

## Using Redis
MiniHopp uses Jedis as its Redis-client. By default the SQL-implementation is
used (dependency injection). To use the Redis implementation simply swap the 
```
@Default
@Alternative
```
annotations in `RedisShortURL.java` and `SQLShortURL.java`

* Redis uses *localhost* as the default hostname
* The standard Redis database is used (#0)
* If you wish to use a remote Redis-server I suggest tunneling it through SSH
that is: `ssh -v -L 6379:localhost:6379 REDIS_SERVER -N`

## Author
Created and written by A.D Ullenius <anosh@anosh.se> in 2019. See LICENCE


## Contributing
If you have added a feature or fixed a bug in MiniHopp please submit a pull request as follows:

* Fork the project
* Write the code for your feature or bug fix
* Commit and do not mess with version or history
* Submit a pull request

Thanks for sharing!

### Full project description
[freeCodeCamp source](https://thread-paper.glitch.me/) - read 2019-02-13

API Project: URL Shortener Microservice
User Story:

    I can POST a URL to [project_url]/api/shorturl/new and I will receive a shortened URL in the JSON response.
    Example : {"original_url":"www.google.com","short_url":1}
    If I pass an invalid URL that doesn't follow the http(s)://www.example.com(/more/routes) format, the JSON response will contain an error like {"error":"invalid URL"}
    HINT: to be sure that the submitted url points to a valid site you can use the function dns.lookup(host, cb) from the dns core module.
    When I visit the shortened URL, it will redirect me to my original link.

Short URL Creation

example: `POST [project_url]/api/shorturl/new - https://en.wikipedia.org/wiki/Main_Page`
