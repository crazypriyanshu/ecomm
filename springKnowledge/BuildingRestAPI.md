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

1. GET retrieves a representation of the resource at the specified URI. The body of the response message contains the details of the requested resource.
2. POST creates a new resource at the specified URI. The body of the request message provides the details of the new resource. Note that POST can also be used to trigger operations that don't actually create resources.
3. PUT either creates or replaces the resource at the specified URI. The body of the request message specifies the resource to be created or updated.
4. PATCH performs a partial update of a resource. The request body specifies the set of changes to apply to the resource.
5. DELETE removes the resource at the specified URI.