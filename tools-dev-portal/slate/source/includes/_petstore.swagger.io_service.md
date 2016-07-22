# Swagger Petstore


<a name="petstore-swagger-iooverview"></a>
## Overview
This is a sample server Petstore server.  You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).  For this sample, you can use the api key `special-key` to test the authorization filters.


### Version information
*Version* : 1.0.0


### Contact information
*Contact Email* : apiteam@swagger.io


### License information
*License* : Apache 2.0  
*License URL* : http://www.apache.org/licenses/LICENSE-2.0.html  
*Terms of service* : http://swagger.io/terms/


### URI scheme
*Host* : petstore.swagger.io  
*BasePath* : /v2  
*Schemes* : HTTP


### Tags

* pet : Everything about your Pets
* store : Access to Petstore orders
* user : Operations about user




<a name="petstore-swagger-iopaths"></a>
## Resources

<a name="petstore-swagger-iopet_resource"></a>
### Pet
Everything about your Pets


<a name="petstore-swagger-ioaddpet"></a>
#### Add a new pet to the store
```
POST /pet
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Body**|**body**  <br>*required*|Pet object that needs to be added to the store|[Pet](definitions.md#petstore-swagger-iopet)||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**405**|Invalid input|No Content|


##### Consumes

* `application/json`
* `application/xml`


##### Produces

* `application/xml`
* `application/json`


##### Security

|Type|Name|Scopes|
|---|---|---|
|**oauth2**|**[petstore_auth](security.md#petstore-swagger-iopetstore_auth)**|write:pets,read:pets|


##### Example HTTP request

###### Request path
```
json :
"/pet"
```


###### Request body
```
json :
{
  "id" : 0,
  "category" : {
    "id" : 0,
    "name" : "string"
  },
  "name" : "doggie",
  "photoUrls" : [ "string" ],
  "tags" : [ {
    "id" : 0,
    "name" : "string"
  } ],
  "status" : "string"
}
```


<a name="petstore-swagger-ioupdatepet"></a>
#### Update an existing pet
```
PUT /pet
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Body**|**body**  <br>*required*|Pet object that needs to be added to the store|[Pet](definitions.md#petstore-swagger-iopet)||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**400**|Invalid ID supplied|No Content|
|**404**|Pet not found|No Content|
|**405**|Validation exception|No Content|


##### Consumes

* `application/json`
* `application/xml`


##### Produces

* `application/xml`
* `application/json`


##### Security

|Type|Name|Scopes|
|---|---|---|
|**oauth2**|**[petstore_auth](security.md#petstore-swagger-iopetstore_auth)**|write:pets,read:pets|


##### Example HTTP request

###### Request path
```
json :
"/pet"
```


###### Request body
```
json :
{
  "id" : 0,
  "category" : {
    "id" : 0,
    "name" : "string"
  },
  "name" : "doggie",
  "photoUrls" : [ "string" ],
  "tags" : [ {
    "id" : 0,
    "name" : "string"
  } ],
  "status" : "string"
}
```


<a name="petstore-swagger-iofindpetsbystatus"></a>
#### Finds Pets by status
```
GET /pet/findByStatus
```


##### Description
Multiple status values can be provided with comma separated strings


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Query**|**status**  <br>*required*|Status values that need to be considered for filter|< enum (available, pending, sold) > array(multi)||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|successful operation|< [Pet](definitions.md#petstore-swagger-iopet) > array|
|**400**|Invalid status value|No Content|


##### Produces

* `application/xml`
* `application/json`


##### Security

|Type|Name|Scopes|
|---|---|---|
|**oauth2**|**[petstore_auth](security.md#petstore-swagger-iopetstore_auth)**|write:pets,read:pets|


##### Example HTTP request

###### Request path
```
json :
"/pet/findByStatus"
```


###### Request query
```
json :
{
  "status" : "string"
}
```


##### Example HTTP response

###### Response 200
```
json :
"array"
```

Caution : 
This operation is deprecated.


<a name="petstore-swagger-iofindpetsbytags"></a>
#### Finds Pets by tags
```
GET /pet/findByTags
```


##### Description
Muliple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Query**|**tags**  <br>*required*|Tags to filter by|< string > array(multi)||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|successful operation|< [Pet](definitions.md#petstore-swagger-iopet) > array|
|**400**|Invalid tag value|No Content|


##### Produces

* `application/xml`
* `application/json`


##### Security

|Type|Name|Scopes|
|---|---|---|
|**oauth2**|**[petstore_auth](security.md#petstore-swagger-iopetstore_auth)**|write:pets,read:pets|


##### Example HTTP request

###### Request path
```
json :
"/pet/findByTags"
```


###### Request query
```
json :
{
  "tags" : "string"
}
```


##### Example HTTP response

###### Response 200
```
json :
"array"
```


<a name="petstore-swagger-ioupdatepetwithform"></a>
#### Updates a pet in the store with form data
```
POST /pet/{petId}
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Path**|**petId**  <br>*required*|ID of pet that needs to be updated|integer(int64)||
|**FormData**|**name**  <br>*optional*|Updated name of the pet|string||
|**FormData**|**status**  <br>*optional*|Updated status of the pet|string||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**405**|Invalid input|No Content|


##### Consumes

* `application/x-www-form-urlencoded`


##### Produces

* `application/xml`
* `application/json`


##### Security

|Type|Name|Scopes|
|---|---|---|
|**oauth2**|**[petstore_auth](security.md#petstore-swagger-iopetstore_auth)**|write:pets,read:pets|


##### Example HTTP request

###### Request path
```
json :
"/pet/0"
```


###### Request formData
```
json :
"string"
```


<a name="petstore-swagger-iogetpetbyid"></a>
#### Find pet by ID
```
GET /pet/{petId}
```


##### Description
Returns a single pet


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Path**|**petId**  <br>*required*|ID of pet to return|integer(int64)||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|successful operation|[Pet](definitions.md#petstore-swagger-iopet)|
|**400**|Invalid ID supplied|No Content|
|**404**|Pet not found|No Content|


##### Produces

* `application/xml`
* `application/json`


##### Security

|Type|Name|Scopes|
|---|---|---|
|**apiKey**|**[api_key](security.md#petstore-swagger-ioapi_key)**||


##### Example HTTP request

###### Request path
```
json :
"/pet/0"
```


##### Example HTTP response

###### Response 200
```
json :
{
  "id" : 0,
  "category" : {
    "id" : 0,
    "name" : "string"
  },
  "name" : "doggie",
  "photoUrls" : [ "string" ],
  "tags" : [ {
    "id" : 0,
    "name" : "string"
  } ],
  "status" : "string"
}
```


<a name="petstore-swagger-iodeletepet"></a>
#### Deletes a pet
```
DELETE /pet/{petId}
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Header**|**api_key**  <br>*optional*||string||
|**Path**|**petId**  <br>*required*|Pet id to delete|integer(int64)||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**400**|Invalid ID supplied|No Content|
|**404**|Pet not found|No Content|


##### Produces

* `application/xml`
* `application/json`


##### Security

|Type|Name|Scopes|
|---|---|---|
|**oauth2**|**[petstore_auth](security.md#petstore-swagger-iopetstore_auth)**|write:pets,read:pets|


##### Example HTTP request

###### Request path
```
json :
"/pet/0"
```


###### Request header
```
json :
"string"
```


<a name="petstore-swagger-iouploadfile"></a>
#### uploads an image
```
POST /pet/{petId}/uploadImage
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Path**|**petId**  <br>*required*|ID of pet to update|integer(int64)||
|**FormData**|**additionalMetadata**  <br>*optional*|Additional data to pass to server|string||
|**FormData**|**file**  <br>*optional*|file to upload|file||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|successful operation|[ApiResponse](definitions.md#petstore-swagger-ioapiresponse)|


##### Consumes

* `multipart/form-data`


##### Produces

* `application/json`


##### Security

|Type|Name|Scopes|
|---|---|---|
|**oauth2**|**[petstore_auth](security.md#petstore-swagger-iopetstore_auth)**|write:pets,read:pets|


##### Example HTTP request

###### Request path
```
json :
"/pet/0/uploadImage"
```


###### Request formData
```
json :
"file"
```


##### Example HTTP response

###### Response 200
```
json :
{
  "code" : 0,
  "type" : "string",
  "message" : "string"
}
```


<a name="petstore-swagger-iostore_resource"></a>
### Store
Access to Petstore orders


<a name="petstore-swagger-iogetinventory"></a>
#### Returns pet inventories by status
```
GET /store/inventory
```


##### Description
Returns a map of status codes to quantities


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|successful operation|< string, integer(int32) > map|


##### Produces

* `application/json`


##### Security

|Type|Name|Scopes|
|---|---|---|
|**apiKey**|**[api_key](security.md#petstore-swagger-ioapi_key)**||


##### Example HTTP request

###### Request path
```
json :
"/store/inventory"
```


##### Example HTTP response

###### Response 200
```
json :
"object"
```


<a name="petstore-swagger-ioplaceorder"></a>
#### Place an order for a pet
```
POST /store/order
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Body**|**body**  <br>*required*|order placed for purchasing the pet|[Order](definitions.md#petstore-swagger-ioorder)||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|successful operation|[Order](definitions.md#petstore-swagger-ioorder)|
|**400**|Invalid Order|No Content|


##### Produces

* `application/xml`
* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/store/order"
```


###### Request body
```
json :
{
  "id" : 0,
  "petId" : 0,
  "quantity" : 0,
  "shipDate" : "string",
  "status" : "string",
  "complete" : true
}
```


##### Example HTTP response

###### Response 200
```
json :
{
  "id" : 0,
  "petId" : 0,
  "quantity" : 0,
  "shipDate" : "string",
  "status" : "string",
  "complete" : true
}
```


<a name="petstore-swagger-iogetorderbyid"></a>
#### Find purchase order by ID
```
GET /store/order/{orderId}
```


##### Description
For valid response try integer IDs with value >= 1 and <= 10. Other values will generated exceptions


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Path**|**orderId**  <br>*required*|ID of pet that needs to be fetched|integer(int64)||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|successful operation|[Order](definitions.md#petstore-swagger-ioorder)|
|**400**|Invalid ID supplied|No Content|
|**404**|Order not found|No Content|


##### Produces

* `application/xml`
* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/store/order/0"
```


##### Example HTTP response

###### Response 200
```
json :
{
  "id" : 0,
  "petId" : 0,
  "quantity" : 0,
  "shipDate" : "string",
  "status" : "string",
  "complete" : true
}
```


<a name="petstore-swagger-iodeleteorder"></a>
#### Delete purchase order by ID
```
DELETE /store/order/{orderId}
```


##### Description
For valid response try integer IDs with positive integer value. Negative or non-integer values will generate API errors


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Path**|**orderId**  <br>*required*|ID of the order that needs to be deleted|integer(int64)||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**400**|Invalid ID supplied|No Content|
|**404**|Order not found|No Content|


##### Produces

* `application/xml`
* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/store/order/0"
```


<a name="petstore-swagger-iouser_resource"></a>
### User
Operations about user


<a name="petstore-swagger-iocreateuser"></a>
#### Create user
```
POST /user
```


##### Description
This can only be done by the logged in user.


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Body**|**body**  <br>*required*|Created user object|[User](definitions.md#petstore-swagger-iouser)||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**default**|successful operation|No Content|


##### Produces

* `application/xml`
* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/user"
```


###### Request body
```
json :
{
  "id" : 0,
  "username" : "string",
  "firstName" : "string",
  "lastName" : "string",
  "email" : "string",
  "password" : "string",
  "phone" : "string",
  "userStatus" : 0
}
```


<a name="petstore-swagger-iocreateuserswitharrayinput"></a>
#### Creates list of users with given input array
```
POST /user/createWithArray
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Body**|**body**  <br>*required*|List of user object|< [User](definitions.md#petstore-swagger-iouser) > array||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**default**|successful operation|No Content|


##### Produces

* `application/xml`
* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/user/createWithArray"
```


###### Request body
```
json :
[ {
  "id" : 0,
  "username" : "string",
  "firstName" : "string",
  "lastName" : "string",
  "email" : "string",
  "password" : "string",
  "phone" : "string",
  "userStatus" : 0
} ]
```


<a name="petstore-swagger-iocreateuserswithlistinput"></a>
#### Creates list of users with given input array
```
POST /user/createWithList
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Body**|**body**  <br>*required*|List of user object|< [User](definitions.md#petstore-swagger-iouser) > array||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**default**|successful operation|No Content|


##### Produces

* `application/xml`
* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/user/createWithList"
```


###### Request body
```
json :
[ {
  "id" : 0,
  "username" : "string",
  "firstName" : "string",
  "lastName" : "string",
  "email" : "string",
  "password" : "string",
  "phone" : "string",
  "userStatus" : 0
} ]
```


<a name="petstore-swagger-iologinuser"></a>
#### Logs user into the system
```
GET /user/login
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Query**|**password**  <br>*required*|The password for login in clear text|string||
|**Query**|**username**  <br>*required*|The user name for login|string||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|successful operation  <br>**Headers** :   <br>`X-Rate-Limit` (integer(int32)) : calls per hour allowed by the user.  <br>`X-Expires-After` (string(date-time)) : date in UTC when token expires.|string|
|**400**|Invalid username/password supplied|No Content|


##### Produces

* `application/xml`
* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/user/login"
```


###### Request query
```
json :
{
  "password" : "string",
  "username" : "string"
}
```


##### Example HTTP response

###### Response 200
```
json :
"string"
```


<a name="petstore-swagger-iologoutuser"></a>
#### Logs out current logged in user session
```
GET /user/logout
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**default**|successful operation|No Content|


##### Produces

* `application/xml`
* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/user/logout"
```


<a name="petstore-swagger-iogetuserbyname"></a>
#### Get user by user name
```
GET /user/{username}
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Path**|**username**  <br>*required*|The name that needs to be fetched. Use user1 for testing.|string||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|successful operation|[User](definitions.md#petstore-swagger-iouser)|
|**400**|Invalid username supplied|No Content|
|**404**|User not found|No Content|


##### Produces

* `application/xml`
* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/user/string"
```


##### Example HTTP response

###### Response 200
```
json :
{
  "id" : 0,
  "username" : "string",
  "firstName" : "string",
  "lastName" : "string",
  "email" : "string",
  "password" : "string",
  "phone" : "string",
  "userStatus" : 0
}
```


<a name="petstore-swagger-ioupdateuser"></a>
#### Updated user
```
PUT /user/{username}
```


##### Description
This can only be done by the logged in user.


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Path**|**username**  <br>*required*|name that need to be updated|string||
|**Body**|**body**  <br>*required*|Updated user object|[User](definitions.md#petstore-swagger-iouser)||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**400**|Invalid user supplied|No Content|
|**404**|User not found|No Content|


##### Produces

* `application/xml`
* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/user/string"
```


###### Request body
```
json :
{
  "id" : 0,
  "username" : "string",
  "firstName" : "string",
  "lastName" : "string",
  "email" : "string",
  "password" : "string",
  "phone" : "string",
  "userStatus" : 0
}
```


<a name="petstore-swagger-iodeleteuser"></a>
#### Delete user
```
DELETE /user/{username}
```


##### Description
This can only be done by the logged in user.


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Path**|**username**  <br>*required*|The name that needs to be deleted|string||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**400**|Invalid username supplied|No Content|
|**404**|User not found|No Content|


##### Produces

* `application/xml`
* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/user/string"
```




<a name="petstore-swagger-iodefinitions"></a>
## Definitions

<a name="petstore-swagger-ioapiresponse"></a>
### ApiResponse

|Name|Description|Schema|
|---|---|---|
|**code**  <br>*optional*|**Example** : `0`|integer(int32)|
|**message**  <br>*optional*|**Example** : `"string"`|string|
|**type**  <br>*optional*|**Example** : `"string"`|string|


<a name="petstore-swagger-iocategory"></a>
### Category

|Name|Description|Schema|
|---|---|---|
|**id**  <br>*optional*|**Example** : `0`|integer(int64)|
|**name**  <br>*optional*|**Example** : `"string"`|string|


<a name="petstore-swagger-ioorder"></a>
### Order

|Name|Description|Schema|
|---|---|---|
|**complete**  <br>*optional*|**Default** : `false`  <br>**Example** : `true`|boolean|
|**id**  <br>*optional*|**Example** : `0`|integer(int64)|
|**petId**  <br>*optional*|**Example** : `0`|integer(int64)|
|**quantity**  <br>*optional*|**Example** : `0`|integer(int32)|
|**shipDate**  <br>*optional*|**Example** : `"string"`|string(date-time)|
|**status**  <br>*optional*|Order Status  <br>**Example** : `"string"`|enum (placed, approved, delivered)|


<a name="petstore-swagger-iopet"></a>
### Pet

|Name|Description|Schema|
|---|---|---|
|**category**  <br>*optional*|**Example** : `"[petstore-swagger-iocategory](#petstore-swagger-iocategory)"`|[Category](definitions.md#petstore-swagger-iocategory)|
|**id**  <br>*optional*|**Example** : `0`|integer(int64)|
|**name**  <br>*required*|**Example** : `"doggie"`|string|
|**photoUrls**  <br>*required*|**Example** : `[ "string" ]`|< string > array|
|**status**  <br>*optional*|pet status in the store  <br>**Example** : `"string"`|enum (available, pending, sold)|
|**tags**  <br>*optional*|**Example** : `[ "[petstore-swagger-iotag](#petstore-swagger-iotag)" ]`|< [Tag](definitions.md#petstore-swagger-iotag) > array|


<a name="petstore-swagger-iotag"></a>
### Tag

|Name|Description|Schema|
|---|---|---|
|**id**  <br>*optional*|**Example** : `0`|integer(int64)|
|**name**  <br>*optional*|**Example** : `"string"`|string|


<a name="petstore-swagger-iouser"></a>
### User

|Name|Description|Schema|
|---|---|---|
|**email**  <br>*optional*|**Example** : `"string"`|string|
|**firstName**  <br>*optional*|**Example** : `"string"`|string|
|**id**  <br>*optional*|**Example** : `0`|integer(int64)|
|**lastName**  <br>*optional*|**Example** : `"string"`|string|
|**password**  <br>*optional*|**Example** : `"string"`|string|
|**phone**  <br>*optional*|**Example** : `"string"`|string|
|**userStatus**  <br>*optional*|User Status  <br>**Example** : `0`|integer(int32)|
|**username**  <br>*optional*|**Example** : `"string"`|string|




<a name="petstore-swagger-iosecurityscheme"></a>
## Security

<a name="petstore-swagger-ioapi_key"></a>
### api_key
*Type* : apiKey  
*Name* : api_key  
*In* : HEADER


<a name="petstore-swagger-iopetstore_auth"></a>
### petstore_auth
*Type* : oauth2  
*Flow* : implicit  
*Token URL* : http://petstore.swagger.io/oauth/dialog


|Name|Description|
|---|---|
|write:pets|modify pets in your account|
|read:pets|read your pets|



