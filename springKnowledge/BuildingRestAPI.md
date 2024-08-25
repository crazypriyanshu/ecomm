### API
Application     => an interface for application
Programming 
Interface      => contract definition

Talk to 3rd party in a way that they can understand

Whenever 2 machines are talking to each other they should have well-defined contract: 
1. Where to send
2. What to send
3. What will be received ?

API => set of methods or endpoints that are provided by another system to use


### Rest API's

> SOLID design principles for API's
> Set of good practices around API design
> how should API's be structured

RE = Representation
S  = State
T  = Transfer

API's should be defined around resources -> entities that API aims to affect

> REST is an architectural style for building distributed systems based on hypermedia. 
> REST is independent of any underlying protocol and is not necessarily tied to HTTP. 
> However, most common REST API implementations use HTTP as the application protocol, and we're focusing on designing REST APIs for HTTP.

All endpoints should be pointing to the name of resources that they are affecting

#### Rest API's are stateless
> Every request should be self-sufficient and not depend upon previous request, that may have been sent(idempotent)
> 
Every API should be :
1. self sufficient
2. independent

Every request should contain all data needed by the server to serve the request


#### Advantage of REST
1. It makes the systems scale very well as they are stateless
2. Servers are not maintaining any info within it
3. API's shouldn't have 1:1 mapping with Db tables
4. REST API's has no mandate on exchange format - JSON, XML



### RESTful web API design

Most modern web application exposes web APIs that client can use to interact with application.
A well-designed API should support: 
1. Platform independence
2. Service evolution


The HTTP protocol defines a number of methods that assign semantic meaning to a request. 
The common HTTP methods used by most RESTful web APIs are:

1. **GET** retrieves a representation of the resource at the specified URI. The body of the response message contains the details of the requested resource.
2. **POST** creates a new resource at the specified URI. The body of the request message provides the details of the new resource. Note that POST can also be used to trigger operations that don't actually create resources.
3. **PUT** either creates or replaces the resource at the specified URI. The body of the request message specifies the resource to be created or updated.
4. **PATCH** performs a partial update of a resource. The request body specifies the set of changes to apply to the resource.
5. **DELETE** removes the resource at the specified URI.


## Diff between POST, PUT and PATCH

##### POST
A POST request **creates** a resource. 
The server assigns a URI for the new resource, and returns that URI to the client. 
In the REST model, we frequently apply POST requests to collections.
The new resource is added to the collection. 
A POST request **can also be used to submit data for processing to an existing resource, without any new resource being created**

##### PUT
A PUT request **creates a resource** or **updates an existing resource**. 
The client specifies the URI for the resource. 
The request body contains a complete representation of the resource. 
If a resource with this URI already exists, it is replaced. 
Otherwise a new resource is created, if the server supports doing so. 

PUT requests are most frequently applied to resources that are individual items, such as a specific customer, rather than collections. 

**A server might support updates but not creation via PUT**. 

Whether to support creation via PUT depends on whether the client can meaningfully assign a URI to a resource before it exists.
If not, then use POST to create resources and PUT or PATCH to update

##### PATCH

A PATCH request **performs a partial update to an existing resource**. 
The client specifies the URI for the resource. 
The request body specifies a set of changes to apply to the resource. 
This can be more efficient than using PUT, because the client only sends the changes, not the entire representation of the resource. 

**Technically PATCH can also create a new resource** (by specifying a set of updates to a "null" resource), if the server supports this

----------------------------------------------------------------------------------------------------------------------------------
PUT requests must be idempotent. 
If a client submits the same PUT request multiple times, the results should always be the same (the same resource will be modified with the same values). 

POST and PATCH requests are not guaranteed to be idempotent.

## Http Semantics

#### Media types:
In the HTTP protocol, formats are specified through the use of media types, also called MIME types. 
For non-binary data, most web APIs support **JSON (media type = application/json)** and possibly **XML (media type = application/xml)**

The Content-Type header in a request or response specifies the format of the representation.
Here is an example of a POST request that includes JSON data:

`POST https://adventure-works.com/orders HTTP/1.1
**Content-Type: application/json**; charset=utf-8
Content-Length: 57
{"Id":1,"Name":"Gizmo","Category":"Widgets","Price":1.99}`

> If the server doesn't support the media type, it should return HTTP status code 415 (Unsupported Media Type).

A client request can include an Accept header that contains a list of media types the client will accept from the server in the response message.
For example:

`GET https://adventure-works.com/orders/2 HTTP/1.1
Accept: application/json`
If the server cannot match any of the media types listed, it should return HTTP status code 406 (Not Acceptable)

#### GET methods :

A successful GET method typically returns HTTP **status code 200 (OK)**. 
If the **resource cannot be found**, the method should return **404 (Not Found)**

#### POST methods :
If a POST method creates a new resource, it returns HTTP **status code 201 (Created)**. 
The URI of the new resource is included in the Location header of the response.
**The response body contains a representation of the resource.**

If the method does some processing but does not create a new resource, 
the method can return HTTP status code 200 and include the result of the operation in the response body. 

Alternatively, **if there is no result to return**, the method can return **HTTP status code 204 (No Content) with no response body**.

If the **client puts invalid data into the request**, the server should return HTTP **status code 400 (Bad Request)**. 
The response body can contain additional information about the error or a link to a URI that provides more details

If the request was fulfilled but there is no response body included in the HTTP response, 
then it should return **HTTP status code 204 (No Content);** 
for example, a search operation yielding no matches might be implemented with this behavior.

##### PUT methods:

If a PUT method creates a new resource, it returns **HTTP status code 201 (Created)**, as with a POST method. 
If the method updates an existing resource, it returns **either 200 (OK) or 204 (No Content).** 
In some **cases, it might not be possible to update an existing resource** consider returning HTTP **status code 409 (Conflict)**.

Consider implementing **bulk HTTP PUT operations** that can batch updates to multiple resources in a collection. 
The PUT request should specify the URI of the collection, and the request body should specify the details of the resources to be modified. 

This approach can help to reduce chattiness and improve performance.


.
##### PATCH methods :

With a PATCH request, the client sends a set of updates to an existing resource, in the form of a patch document. 
The server processes the patch document to perform the update. 

The patch document doesn't describe the whole resource, only a set of changes to apply. 

The specification for the PATCH method (RFC 5789) doesn't define a particular format for patch documents. 
The format must be inferred from the media type in the request.

JSON is probably the most common data format for web APIs. 
There are two main JSON-based patch formats, called JSON patch and JSON merge patch.


##### Delete methods :

If the delete operation is successful, the web server should respond with HTTP status code 204 (No Content), indicating that the process has been successfully handled,
but that the response body contains no further information. 
If the **resource doesn't exist**, the web server can **return HTTP 404 (Not Found)**.

##### Empty sets in message bodies

> Any time the body of a successful response is empty, the status code should be 204 (No Content). For empty sets, such as a response to a filtered request with no items, the status code should still be 204 (No Content), not 200 (OK)


### Filter and Paginate data : 

Exposing a collection of resources through a single URI can lead to applications fetching large amounts of data when only a subset of the information is required.
For example, suppose a client application needs to find all orders with a cost over a specific value. 
It might retrieve all orders from the /orders URI and then filter these orders on the client side.

Clearly this process is highly inefficient. It wastes network bandwidth and processing power on the server hosting the web API.

Instead, the API can allow passing a filter in the query string of the URI, such as `/orders?minCost=n`. 
The web API is then responsible for parsing and handling the minCost parameter in the query string and returning the filtered results on the server side.

We can use : `/orders?limit=25&offset=50`

#### Versioning a RESTful web API:

It is highly unlikely that a web API will remain static. 
As business requirements change new collections of resources may be added, the relationships between resources might change, and the structure of the data in resources might be amended. 

While updating a web API to handle new or differing requirements is a relatively straightforward process, 
you must consider the effects that such changes will have on client applications consuming the web API.

The issue is that although the developer designing and implementing a web API has full control over that API,
the developer does not have the same degree of control over client applications, 
which may be built by third-party organizations operating remotely. 

The primary imperative is to enable existing client applications to continue functioning unchanged while allowing 
new client applications to take advantage of new features and resources.

Versioning enables a web API to indicate the features and resources that it exposes, and a client application can submit requests that are directed to a specific version of a feature or resource. 
The following sections describe several different approaches, each of which has its own benefits and trade-offs.

##### No versioning
Significant changes could be represented as new resources or new links. 
Adding content to existing resources might not present a breaking change as client applications that are not expecting to see this content will ignore it.

Existing client applications might continue functioning correctly if they are capable of ignoring unrecognized fields, while new client applications can be designed to handle this new field.

However, if more radical changes to the schema of resources occur (such as removing or renaming fields) or the relationships between resources change then these may constitute breaking changes that prevent existing client applications from functioning correctly. 
In these situations, we should consider one of the following approaches.

##### URI versioning
Each time you modify the web API or change the schema of resources, you add a version number to the URI for each resource.
The previously existing URIs should continue to operate as before, returning resources that conform to their original schema

Eg. `https://adventure-works.com/v2/customers/3`

This versioning mechanism is very simple but depends on the server routing the request to the appropriate endpoint. 
However, it can become unwieldy as the web API matures through several iterations and the server has to support a number of different versions. 

Also, from a purist's point of view, in all cases the client applications are fetching the same data (customer 3), so the URI should not really be different depending on the version. 
This scheme also complicates implementation of HATEOAS as all links will need to include the version number in their URIs.

##### Query String versioning 

Rather than providing multiple URIs, we can specify the version of the resource by using a parameter within the query string appended to the HTTP request, 
such as https://adventure-works.com/customers/3?version=2. 

The version parameter should default to a meaningful value such as 1 if it is omitted by older client application

##### Header versioning

Rather than appending the version number as a query string parameter, we could implement a custom header that indicates the version of the resource.
This approach requires that the client application adds the appropriate header to any requests, although the code handling the client request could use a default value (version 1) if the version header is omitted. 

The following examples use a custom header named Custom-Header. The value of this header indicates the version of web API
`GET https://adventure-works.com/customers/3 HTTP/1.1
Custom-Header: api-version=1`
`GET https://adventure-works.com/customers/3 HTTP/1.1
Custom-Header: api-version=2`


##### Media type versioning 
When a client application sends an HTTP GET request to a web server it should stipulate the format of the content that it can handle by using an Accept header.
Frequently the purpose of the Accept header is to allow the client application to specify whether the body of the response should be XML, JSON, or some other common format that the client can parse. .

However, it is possible to define custom media types that include information enabling the client application to indicate which version of a resource it is expecting.

Eg : `GET https://adventure-works.com/customers/3 HTTP/1.1
Accept: application/vnd.adventure-works.v1+json`

