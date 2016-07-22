# SCIM service


<a name="tpsvc-master-datapwn-comoverview"></a>
## Overview
The service to manage users for all Talend products.


### Version information
*Version* : 2.0


### Contact information
*Contact* : TPSVC Team


### License information
*License* : Apache License Version 2.0  
*License URL* : https://github.com/Talend/tdf-platform-services#license


### URI scheme
*Host* : tpsvc-master.datapwn.com  
*BasePath* : /


### Tags

* service-provider-config-controller : Service Provider Config Controller
* resource-type-controller : Resource Type Controller
* user-controller : User Controller
* group-controller : Group Controller
* me-controller : Me Controller
* schema-controller : Schema Controller




<a name="tpsvc-master-datapwn-compaths"></a>
## Resources

<a name="tpsvc-master-datapwn-comgroup-controller_resource"></a>
### Group-controller
Group Controller


<a name="tpsvc-master-datapwn-comcreategroupusingpost"></a>
#### createGroup
```
POST /Groups
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Body**|**group**  <br>*required*|Group of users|[Group](definitions.md#tpsvc-master-datapwn-comgroup)||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[Group](definitions.md#tpsvc-master-datapwn-comgroup)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/Groups"
```


###### Request body
```
json :
{
  "displayName" : "string",
  "externalId" : "string",
  "id" : "string",
  "members" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "meta" : {
    "attributes" : [ "string" ],
    "created" : "string",
    "lastModified" : "string",
    "location" : "string",
    "resourceType" : "string",
    "version" : "string"
  },
  "schemas" : [ "string" ]
}
```


##### Example HTTP response

###### Response 200
```
json :
{
  "displayName" : "string",
  "externalId" : "string",
  "id" : "string",
  "members" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "meta" : {
    "attributes" : [ "string" ],
    "created" : "string",
    "lastModified" : "string",
    "location" : "string",
    "resourceType" : "string",
    "version" : "string"
  },
  "schemas" : [ "string" ]
}
```


<a name="tpsvc-master-datapwn-comsearchgroupusingget"></a>
#### searchGroup
```
GET /Groups
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Query**|**requestParameters**  <br>*optional*|Map with search parameters|ref||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[MappingJacksonValue](definitions.md#tpsvc-master-datapwn-commappingjacksonvalue)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/Groups"
```


###### Request query
```
json :
{
  "requestParameters" : "ref"
}
```


##### Example HTTP response

###### Response 200
```
json :
{
  "filters" : "[tpsvc-master-datapwn-comfilterprovider](#tpsvc-master-datapwn-comfilterprovider)",
  "jsonpFunction" : "string",
  "value" : "object"
}
```


<a name="tpsvc-master-datapwn-comsearchgroupusingpost"></a>
#### searchGroup
```
POST /Groups/.search
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Body**|**requestParameters**  <br>*optional*|SearchRequest with search parameters|[SearchRequest](definitions.md#tpsvc-master-datapwn-comsearchrequest)||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[MappingJacksonValue](definitions.md#tpsvc-master-datapwn-commappingjacksonvalue)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/Groups/.search"
```


###### Request body
```
json :
{
  "attributes" : [ "string" ],
  "count" : 0,
  "excludedAttributes" : [ "string" ],
  "filter" : "string",
  "schemas" : [ "string" ],
  "sortBy" : "string",
  "sortOrder" : "string",
  "startIndex" : 0
}
```


##### Example HTTP response

###### Response 200
```
json :
{
  "filters" : "[tpsvc-master-datapwn-comfilterprovider](#tpsvc-master-datapwn-comfilterprovider)",
  "jsonpFunction" : "string",
  "value" : "object"
}
```


<a name="tpsvc-master-datapwn-comgetgroupusingget"></a>
#### getGroup
```
GET /Groups/{id}
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Path**|**id**  <br>*required*|Group id|string||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[Group](definitions.md#tpsvc-master-datapwn-comgroup)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/Groups/string"
```


##### Example HTTP response

###### Response 200
```
json :
{
  "displayName" : "string",
  "externalId" : "string",
  "id" : "string",
  "members" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "meta" : {
    "attributes" : [ "string" ],
    "created" : "string",
    "lastModified" : "string",
    "location" : "string",
    "resourceType" : "string",
    "version" : "string"
  },
  "schemas" : [ "string" ]
}
```


<a name="tpsvc-master-datapwn-comreplacegroupusingput"></a>
#### replaceGroup
```
PUT /Groups/{id}
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Path**|**id**  <br>*required*|Group id|string||
|**Body**|**group**  <br>*required*|Group of users|[Group](definitions.md#tpsvc-master-datapwn-comgroup)||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[Group](definitions.md#tpsvc-master-datapwn-comgroup)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/Groups/string"
```


###### Request body
```
json :
{
  "displayName" : "string",
  "externalId" : "string",
  "id" : "string",
  "members" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "meta" : {
    "attributes" : [ "string" ],
    "created" : "string",
    "lastModified" : "string",
    "location" : "string",
    "resourceType" : "string",
    "version" : "string"
  },
  "schemas" : [ "string" ]
}
```


##### Example HTTP response

###### Response 200
```
json :
{
  "displayName" : "string",
  "externalId" : "string",
  "id" : "string",
  "members" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "meta" : {
    "attributes" : [ "string" ],
    "created" : "string",
    "lastModified" : "string",
    "location" : "string",
    "resourceType" : "string",
    "version" : "string"
  },
  "schemas" : [ "string" ]
}
```


<a name="tpsvc-master-datapwn-comdeletegroupusingdelete"></a>
#### deleteGroup
```
DELETE /Groups/{id}
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Path**|**id**  <br>*required*|Group id|string||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|No Content|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
json :
"/Groups/string"
```


<a name="tpsvc-master-datapwn-comme-controller_resource"></a>
### Me-controller
Me Controller


<a name="tpsvc-master-datapwn-comgetcurrentuserusingget"></a>
#### getCurrentUser
```
GET /Me
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Header**|**Authorization**  <br>*required*|Authorization header|string||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[User](definitions.md#tpsvc-master-datapwn-comuser)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/Me"
```


###### Request header
```
json :
"string"
```


##### Example HTTP response

###### Response 200
```
json :
{
  "active" : true,
  "addresses" : [ {
    "$ref" : "string",
    "country" : "string",
    "display" : "string",
    "formatted" : "string",
    "locality" : "string",
    "operation" : "string",
    "postalCode" : "string",
    "primary" : true,
    "region" : "string",
    "streetAddress" : "string",
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "displayName" : "string",
  "emails" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "entitlements" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "extensions" : {
    "string" : "[tpsvc-master-datapwn-comextension](#tpsvc-master-datapwn-comextension)"
  },
  "externalId" : "string",
  "groups" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "id" : "string",
  "ims" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "locale" : "string",
  "meta" : {
    "attributes" : [ "string" ],
    "created" : "string",
    "lastModified" : "string",
    "location" : "string",
    "resourceType" : "string",
    "version" : "string"
  },
  "name" : {
    "familyName" : "string",
    "formatted" : "string",
    "givenName" : "string",
    "honorificPrefix" : "string",
    "honorificSuffix" : "string",
    "middleName" : "string"
  },
  "nickName" : "string",
  "password" : "string",
  "phoneNumbers" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "photos" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "preferredLanguage" : "string",
  "profileUrl" : "string",
  "roles" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "schemas" : [ "string" ],
  "timezone" : "string",
  "title" : "string",
  "userName" : "string",
  "userType" : "string",
  "x509Certificates" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ]
}
```


<a name="tpsvc-master-datapwn-comresource-type-controller_resource"></a>
### Resource-type-controller
Resource Type Controller


<a name="tpsvc-master-datapwn-comretrieveallresourcetypesusingget"></a>
#### Retrieve supported resource types
```
GET /ResourceTypes
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< [ResourceType](definitions.md#tpsvc-master-datapwn-comresourcetype) > array|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/ResourceTypes"
```


##### Example HTTP response

###### Response 200
```
json :
"array"
```


<a name="tpsvc-master-datapwn-comretrieveresourcetypebyidusingget"></a>
#### Retrieve Resource type by id
```
GET /ResourceTypes/{id}
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Path**|**id**  <br>*required*|Resource type id|string||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResourceType](definitions.md#tpsvc-master-datapwn-comresourcetype)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/ResourceTypes/string"
```


##### Example HTTP response

###### Response 200
```
json :
{
  "description" : "string",
  "endpoint" : "string",
  "id" : "string",
  "meta" : {
    "attributes" : [ "string" ],
    "created" : "string",
    "lastModified" : "string",
    "location" : "string",
    "resourceType" : "string",
    "version" : "string"
  },
  "name" : "string",
  "schemas" : [ "string" ]
}
```


<a name="tpsvc-master-datapwn-comschema-controller_resource"></a>
### Schema-controller
Schema Controller


<a name="tpsvc-master-datapwn-comretrieveallschemasusingget"></a>
#### Retrieve supported schemas
```
GET /Schemas
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|string|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/Schemas"
```


##### Example HTTP response

###### Response 200
```
json :
"string"
```


<a name="tpsvc-master-datapwn-comretrieveschemabyidusingget"></a>
#### Retrieve schema by id
```
GET /Schemas/{id}
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Path**|**id**  <br>*required*|Schema id|string||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|string|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/Schemas/string"
```


##### Example HTTP response

###### Response 200
```
json :
"string"
```


<a name="tpsvc-master-datapwn-comservice-provider-config-controller_resource"></a>
### Service-provider-config-controller
Service Provider Config Controller


<a name="tpsvc-master-datapwn-comgetconfigusingget"></a>
#### getConfig
```
GET /ServiceProviderConfig
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ServiceProviderConfig](definitions.md#tpsvc-master-datapwn-comserviceproviderconfig)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/ServiceProviderConfig"
```


##### Example HTTP response

###### Response 200
```
json :
{
  "authenticationSchemas" : [ {
    "description" : "string",
    "documentationUrl" : "string",
    "name" : "string",
    "specUrl" : "string"
  } ],
  "bulk" : {
    "supported" : true
  },
  "changePassword" : {
    "supported" : true
  },
  "etag" : {
    "supported" : true
  },
  "filter" : {
    "maxResults" : 0,
    "supported" : true
  },
  "patch" : {
    "supported" : true
  },
  "schemas" : [ "string" ],
  "sort" : {
    "supported" : true
  },
  "xmlDataFormat" : {
    "supported" : true
  }
}
```


<a name="tpsvc-master-datapwn-comuser-controller_resource"></a>
### User-controller
User Controller


<a name="tpsvc-master-datapwn-comcreateuserusingpost"></a>
#### createUser
```
POST /Users
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Body**|**user**  <br>*required*|User to create|[User](definitions.md#tpsvc-master-datapwn-comuser)||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[User](definitions.md#tpsvc-master-datapwn-comuser)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/Users"
```


###### Request body
```
json :
{
  "active" : true,
  "addresses" : [ {
    "$ref" : "string",
    "country" : "string",
    "display" : "string",
    "formatted" : "string",
    "locality" : "string",
    "operation" : "string",
    "postalCode" : "string",
    "primary" : true,
    "region" : "string",
    "streetAddress" : "string",
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "displayName" : "string",
  "emails" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "entitlements" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "extensions" : {
    "string" : "[tpsvc-master-datapwn-comextension](#tpsvc-master-datapwn-comextension)"
  },
  "externalId" : "string",
  "groups" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "id" : "string",
  "ims" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "locale" : "string",
  "meta" : {
    "attributes" : [ "string" ],
    "created" : "string",
    "lastModified" : "string",
    "location" : "string",
    "resourceType" : "string",
    "version" : "string"
  },
  "name" : {
    "familyName" : "string",
    "formatted" : "string",
    "givenName" : "string",
    "honorificPrefix" : "string",
    "honorificSuffix" : "string",
    "middleName" : "string"
  },
  "nickName" : "string",
  "password" : "string",
  "phoneNumbers" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "photos" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "preferredLanguage" : "string",
  "profileUrl" : "string",
  "roles" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "schemas" : [ "string" ],
  "timezone" : "string",
  "title" : "string",
  "userName" : "string",
  "userType" : "string",
  "x509Certificates" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ]
}
```


##### Example HTTP response

###### Response 200
```
json :
{
  "active" : true,
  "addresses" : [ {
    "$ref" : "string",
    "country" : "string",
    "display" : "string",
    "formatted" : "string",
    "locality" : "string",
    "operation" : "string",
    "postalCode" : "string",
    "primary" : true,
    "region" : "string",
    "streetAddress" : "string",
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "displayName" : "string",
  "emails" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "entitlements" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "extensions" : {
    "string" : "[tpsvc-master-datapwn-comextension](#tpsvc-master-datapwn-comextension)"
  },
  "externalId" : "string",
  "groups" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "id" : "string",
  "ims" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "locale" : "string",
  "meta" : {
    "attributes" : [ "string" ],
    "created" : "string",
    "lastModified" : "string",
    "location" : "string",
    "resourceType" : "string",
    "version" : "string"
  },
  "name" : {
    "familyName" : "string",
    "formatted" : "string",
    "givenName" : "string",
    "honorificPrefix" : "string",
    "honorificSuffix" : "string",
    "middleName" : "string"
  },
  "nickName" : "string",
  "password" : "string",
  "phoneNumbers" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "photos" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "preferredLanguage" : "string",
  "profileUrl" : "string",
  "roles" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "schemas" : [ "string" ],
  "timezone" : "string",
  "title" : "string",
  "userName" : "string",
  "userType" : "string",
  "x509Certificates" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ]
}
```


<a name="tpsvc-master-datapwn-comsearchuserusingget"></a>
#### searchUser
```
GET /Users
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Query**|**requestParameters**  <br>*optional*|Map with search parameters|ref||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[MappingJacksonValue](definitions.md#tpsvc-master-datapwn-commappingjacksonvalue)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/Users"
```


###### Request query
```
json :
{
  "requestParameters" : "ref"
}
```


##### Example HTTP response

###### Response 200
```
json :
{
  "filters" : "[tpsvc-master-datapwn-comfilterprovider](#tpsvc-master-datapwn-comfilterprovider)",
  "jsonpFunction" : "string",
  "value" : "object"
}
```


<a name="tpsvc-master-datapwn-comsearchuserusingpost"></a>
#### searchUser
```
POST /Users/.search
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Body**|**requestParameters**  <br>*optional*|SearchRequest with search parameters|[SearchRequest](definitions.md#tpsvc-master-datapwn-comsearchrequest)||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[MappingJacksonValue](definitions.md#tpsvc-master-datapwn-commappingjacksonvalue)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/Users/.search"
```


###### Request body
```
json :
{
  "attributes" : [ "string" ],
  "count" : 0,
  "excludedAttributes" : [ "string" ],
  "filter" : "string",
  "schemas" : [ "string" ],
  "sortBy" : "string",
  "sortOrder" : "string",
  "startIndex" : 0
}
```


##### Example HTTP response

###### Response 200
```
json :
{
  "filters" : "[tpsvc-master-datapwn-comfilterprovider](#tpsvc-master-datapwn-comfilterprovider)",
  "jsonpFunction" : "string",
  "value" : "object"
}
```


<a name="tpsvc-master-datapwn-comgetuserusingget"></a>
#### getUser
```
GET /Users/{id}
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Path**|**id**  <br>*required*|User id|string||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[User](definitions.md#tpsvc-master-datapwn-comuser)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/Users/string"
```


##### Example HTTP response

###### Response 200
```
json :
{
  "active" : true,
  "addresses" : [ {
    "$ref" : "string",
    "country" : "string",
    "display" : "string",
    "formatted" : "string",
    "locality" : "string",
    "operation" : "string",
    "postalCode" : "string",
    "primary" : true,
    "region" : "string",
    "streetAddress" : "string",
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "displayName" : "string",
  "emails" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "entitlements" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "extensions" : {
    "string" : "[tpsvc-master-datapwn-comextension](#tpsvc-master-datapwn-comextension)"
  },
  "externalId" : "string",
  "groups" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "id" : "string",
  "ims" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "locale" : "string",
  "meta" : {
    "attributes" : [ "string" ],
    "created" : "string",
    "lastModified" : "string",
    "location" : "string",
    "resourceType" : "string",
    "version" : "string"
  },
  "name" : {
    "familyName" : "string",
    "formatted" : "string",
    "givenName" : "string",
    "honorificPrefix" : "string",
    "honorificSuffix" : "string",
    "middleName" : "string"
  },
  "nickName" : "string",
  "password" : "string",
  "phoneNumbers" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "photos" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "preferredLanguage" : "string",
  "profileUrl" : "string",
  "roles" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "schemas" : [ "string" ],
  "timezone" : "string",
  "title" : "string",
  "userName" : "string",
  "userType" : "string",
  "x509Certificates" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ]
}
```


<a name="tpsvc-master-datapwn-comupdateusingput"></a>
#### update
```
PUT /Users/{id}
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Path**|**id**  <br>*required*|User id|string||
|**Body**|**user**  <br>*required*|User to replace|[User](definitions.md#tpsvc-master-datapwn-comuser)||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[User](definitions.md#tpsvc-master-datapwn-comuser)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `application/json`


##### Example HTTP request

###### Request path
```
json :
"/Users/string"
```


###### Request body
```
json :
{
  "active" : true,
  "addresses" : [ {
    "$ref" : "string",
    "country" : "string",
    "display" : "string",
    "formatted" : "string",
    "locality" : "string",
    "operation" : "string",
    "postalCode" : "string",
    "primary" : true,
    "region" : "string",
    "streetAddress" : "string",
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "displayName" : "string",
  "emails" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "entitlements" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "extensions" : {
    "string" : "[tpsvc-master-datapwn-comextension](#tpsvc-master-datapwn-comextension)"
  },
  "externalId" : "string",
  "groups" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "id" : "string",
  "ims" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "locale" : "string",
  "meta" : {
    "attributes" : [ "string" ],
    "created" : "string",
    "lastModified" : "string",
    "location" : "string",
    "resourceType" : "string",
    "version" : "string"
  },
  "name" : {
    "familyName" : "string",
    "formatted" : "string",
    "givenName" : "string",
    "honorificPrefix" : "string",
    "honorificSuffix" : "string",
    "middleName" : "string"
  },
  "nickName" : "string",
  "password" : "string",
  "phoneNumbers" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "photos" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "preferredLanguage" : "string",
  "profileUrl" : "string",
  "roles" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "schemas" : [ "string" ],
  "timezone" : "string",
  "title" : "string",
  "userName" : "string",
  "userType" : "string",
  "x509Certificates" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ]
}
```


##### Example HTTP response

###### Response 200
```
json :
{
  "active" : true,
  "addresses" : [ {
    "$ref" : "string",
    "country" : "string",
    "display" : "string",
    "formatted" : "string",
    "locality" : "string",
    "operation" : "string",
    "postalCode" : "string",
    "primary" : true,
    "region" : "string",
    "streetAddress" : "string",
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "displayName" : "string",
  "emails" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "entitlements" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "extensions" : {
    "string" : "[tpsvc-master-datapwn-comextension](#tpsvc-master-datapwn-comextension)"
  },
  "externalId" : "string",
  "groups" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "id" : "string",
  "ims" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "locale" : "string",
  "meta" : {
    "attributes" : [ "string" ],
    "created" : "string",
    "lastModified" : "string",
    "location" : "string",
    "resourceType" : "string",
    "version" : "string"
  },
  "name" : {
    "familyName" : "string",
    "formatted" : "string",
    "givenName" : "string",
    "honorificPrefix" : "string",
    "honorificSuffix" : "string",
    "middleName" : "string"
  },
  "nickName" : "string",
  "password" : "string",
  "phoneNumbers" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "photos" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "preferredLanguage" : "string",
  "profileUrl" : "string",
  "roles" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ],
  "schemas" : [ "string" ],
  "timezone" : "string",
  "title" : "string",
  "userName" : "string",
  "userType" : "string",
  "x509Certificates" : [ {
    "$ref" : "string",
    "display" : "string",
    "operation" : "string",
    "primary" : true,
    "type" : "[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)",
    "value" : "string"
  } ]
}
```


<a name="tpsvc-master-datapwn-comdeleteuserusingdelete"></a>
#### deleteUser
```
DELETE /Users/{id}
```


##### Parameters

|Type|Name|Description|Schema|Default|
|---|---|---|---|---|
|**Path**|**id**  <br>*required*|User id|string||


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**||No Content|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


##### Example HTTP request

###### Request path
```
json :
"/Users/string"
```




<a name="tpsvc-master-datapwn-comdefinitions"></a>
## Definitions

<a name="tpsvc-master-datapwn-comaddress"></a>
### Address

|Name|Description|Schema|
|---|---|---|
|**$ref**  <br>*required*|**Example** : `"string"`|string|
|**country**  <br>*required*|**Example** : `"string"`|string|
|**display**  <br>*required*|**Example** : `"string"`|string|
|**formatted**  <br>*required*|**Example** : `"string"`|string|
|**locality**  <br>*required*|**Example** : `"string"`|string|
|**operation**  <br>*required*|**Example** : `"string"`|string|
|**postalCode**  <br>*required*|**Example** : `"string"`|string|
|**primary**  <br>*required*|**Example** : `true`|boolean|
|**region**  <br>*required*|**Example** : `"string"`|string|
|**streetAddress**  <br>*required*|**Example** : `"string"`|string|
|**type**  <br>*required*|**Example** : `"[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)"`|[Type](definitions.md#tpsvc-master-datapwn-comtype)|
|**value**  <br>*required*|**Example** : `"string"`|string|


<a name="tpsvc-master-datapwn-comauthenticationschema"></a>
### AuthenticationSchema

|Name|Description|Schema|
|---|---|---|
|**description**  <br>*optional*|**Example** : `"string"`|string|
|**documentationUrl**  <br>*optional*|**Example** : `"string"`|string|
|**name**  <br>*optional*|**Example** : `"string"`|string|
|**specUrl**  <br>*optional*|**Example** : `"string"`|string|


<a name="tpsvc-master-datapwn-comemail"></a>
### Email

|Name|Description|Schema|
|---|---|---|
|**$ref**  <br>*required*|**Example** : `"string"`|string|
|**display**  <br>*required*|**Example** : `"string"`|string|
|**operation**  <br>*required*|**Example** : `"string"`|string|
|**primary**  <br>*required*|**Example** : `true`|boolean|
|**type**  <br>*required*|**Example** : `"[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)"`|[Type](definitions.md#tpsvc-master-datapwn-comtype)|
|**value**  <br>*required*|**Example** : `"string"`|string|


<a name="tpsvc-master-datapwn-comentitlement"></a>
### Entitlement

|Name|Description|Schema|
|---|---|---|
|**$ref**  <br>*required*|**Example** : `"string"`|string|
|**display**  <br>*required*|**Example** : `"string"`|string|
|**operation**  <br>*required*|**Example** : `"string"`|string|
|**primary**  <br>*required*|**Example** : `true`|boolean|
|**type**  <br>*required*|**Example** : `"[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)"`|[Type](definitions.md#tpsvc-master-datapwn-comtype)|
|**value**  <br>*required*|**Example** : `"string"`|string|


<a name="tpsvc-master-datapwn-comextension"></a>
### Extension

|Name|Description|Schema|
|---|---|---|
|**fields**  <br>*required*|**Example** : `{<br>  "string" : "[tpsvc-master-datapwn-comfield](#tpsvc-master-datapwn-comfield)"<br>}`|< string, [Field](definitions.md#tpsvc-master-datapwn-comfield) > map|
|**urn**  <br>*required*|**Example** : `"string"`|string|


<a name="d8754b92ab5cc10b6127a24b6d05b090"></a>
### ExtensionFieldType«object»

|Name|Description|Schema|
|---|---|---|
|**name**  <br>*optional*|**Example** : `"string"`|string|


<a name="tpsvc-master-datapwn-comfield"></a>
### Field

|Name|Description|Schema|
|---|---|---|
|**type**  <br>*optional*|**Example** : `"[d8754b92ab5cc10b6127a24b6d05b090](#d8754b92ab5cc10b6127a24b6d05b090)"`|[ExtensionFieldType«object»](definitions.md#d8754b92ab5cc10b6127a24b6d05b090)|
|**value**  <br>*optional*|**Example** : `"string"`|string|


<a name="tpsvc-master-datapwn-comfilter"></a>
### Filter

|Name|Description|Schema|
|---|---|---|
|**maxResults**  <br>*optional*|**Example** : `0`|integer(int32)|
|**supported**  <br>*optional*|**Example** : `true`|boolean|


<a name="tpsvc-master-datapwn-comgroup"></a>
### Group

|Name|Description|Schema|
|---|---|---|
|**displayName**  <br>*required*|**Example** : `"string"`|string|
|**externalId**  <br>*required*|**Example** : `"string"`|string|
|**id**  <br>*required*|**Example** : `"string"`|string|
|**members**  <br>*required*|**Example** : `[ "[tpsvc-master-datapwn-commemberref](#tpsvc-master-datapwn-commemberref)" ]`|< [MemberRef](definitions.md#tpsvc-master-datapwn-commemberref) > array|
|**meta**  <br>*required*|**Example** : `"[tpsvc-master-datapwn-commeta](#tpsvc-master-datapwn-commeta)"`|[Meta](definitions.md#tpsvc-master-datapwn-commeta)|
|**schemas**  <br>*required*|**Example** : `[ "string" ]`|< string > array|


<a name="tpsvc-master-datapwn-comgroupref"></a>
### GroupRef

|Name|Description|Schema|
|---|---|---|
|**$ref**  <br>*required*|**Example** : `"string"`|string|
|**display**  <br>*required*|**Example** : `"string"`|string|
|**operation**  <br>*required*|**Example** : `"string"`|string|
|**primary**  <br>*required*|**Example** : `true`|boolean|
|**type**  <br>*required*|**Example** : `"[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)"`|[Type](definitions.md#tpsvc-master-datapwn-comtype)|
|**value**  <br>*required*|**Example** : `"string"`|string|


<a name="tpsvc-master-datapwn-comim"></a>
### Im

|Name|Description|Schema|
|---|---|---|
|**$ref**  <br>*required*|**Example** : `"string"`|string|
|**display**  <br>*required*|**Example** : `"string"`|string|
|**operation**  <br>*required*|**Example** : `"string"`|string|
|**primary**  <br>*required*|**Example** : `true`|boolean|
|**type**  <br>*required*|**Example** : `"[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)"`|[Type](definitions.md#tpsvc-master-datapwn-comtype)|
|**value**  <br>*required*|**Example** : `"string"`|string|


<a name="tpsvc-master-datapwn-commappingjacksonvalue"></a>
### MappingJacksonValue

|Name|Description|Schema|
|---|---|---|
|**filters**  <br>*optional*|**Example** : `"[tpsvc-master-datapwn-comfilterprovider](#tpsvc-master-datapwn-comfilterprovider)"`|[FilterProvider](definitions.md#tpsvc-master-datapwn-comfilterprovider)|
|**jsonpFunction**  <br>*optional*|**Example** : `"string"`|string|
|**value**  <br>*optional*|**Example** : `"object"`|object|


<a name="627a5d2b37fbb699ac245b912dc131ad"></a>
### Map«string,Extension»
*Type* : < string, [Extension](definitions.md#tpsvc-master-datapwn-comextension) > map


<a name="af9009a9213b1e2b2e0617916335436a"></a>
### Map«string,Field»
*Type* : < string, [Field](definitions.md#tpsvc-master-datapwn-comfield) > map


<a name="tpsvc-master-datapwn-commemberref"></a>
### MemberRef

|Name|Description|Schema|
|---|---|---|
|**$ref**  <br>*required*|**Example** : `"string"`|string|
|**display**  <br>*required*|**Example** : `"string"`|string|
|**operation**  <br>*required*|**Example** : `"string"`|string|
|**primary**  <br>*required*|**Example** : `true`|boolean|
|**type**  <br>*required*|**Example** : `"[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)"`|[Type](definitions.md#tpsvc-master-datapwn-comtype)|
|**value**  <br>*required*|**Example** : `"string"`|string|


<a name="tpsvc-master-datapwn-commeta"></a>
### Meta

|Name|Description|Schema|
|---|---|---|
|**attributes**  <br>*required*|**Example** : `[ "string" ]`|< string > array|
|**created**  <br>*required*|**Example** : `"string"`|string(date-time)|
|**lastModified**  <br>*required*|**Example** : `"string"`|string(date-time)|
|**location**  <br>*required*|**Example** : `"string"`|string|
|**resourceType**  <br>*required*|**Example** : `"string"`|string|
|**version**  <br>*required*|**Example** : `"string"`|string|


<a name="tpsvc-master-datapwn-comname"></a>
### Name

|Name|Description|Schema|
|---|---|---|
|**familyName**  <br>*required*|**Example** : `"string"`|string|
|**formatted**  <br>*required*|**Example** : `"string"`|string|
|**givenName**  <br>*required*|**Example** : `"string"`|string|
|**honorificPrefix**  <br>*required*|**Example** : `"string"`|string|
|**honorificSuffix**  <br>*required*|**Example** : `"string"`|string|
|**middleName**  <br>*required*|**Example** : `"string"`|string|


<a name="tpsvc-master-datapwn-comphonenumber"></a>
### PhoneNumber

|Name|Description|Schema|
|---|---|---|
|**$ref**  <br>*required*|**Example** : `"string"`|string|
|**display**  <br>*required*|**Example** : `"string"`|string|
|**operation**  <br>*required*|**Example** : `"string"`|string|
|**primary**  <br>*required*|**Example** : `true`|boolean|
|**type**  <br>*required*|**Example** : `"[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)"`|[Type](definitions.md#tpsvc-master-datapwn-comtype)|
|**value**  <br>*required*|**Example** : `"string"`|string|


<a name="tpsvc-master-datapwn-comphoto"></a>
### Photo

|Name|Description|Schema|
|---|---|---|
|**$ref**  <br>*required*|**Example** : `"string"`|string|
|**display**  <br>*required*|**Example** : `"string"`|string|
|**operation**  <br>*required*|**Example** : `"string"`|string|
|**primary**  <br>*required*|**Example** : `true`|boolean|
|**type**  <br>*required*|**Example** : `"[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)"`|[Type](definitions.md#tpsvc-master-datapwn-comtype)|
|**value**  <br>*required*|**Example** : `"string"`|string|


<a name="tpsvc-master-datapwn-comresourcetype"></a>
### ResourceType

|Name|Description|Schema|
|---|---|---|
|**description**  <br>*optional*|**Example** : `"string"`|string|
|**endpoint**  <br>*optional*|**Example** : `"string"`|string|
|**id**  <br>*optional*|**Example** : `"string"`|string|
|**meta**  <br>*optional*|**Example** : `"[tpsvc-master-datapwn-commeta](#tpsvc-master-datapwn-commeta)"`|[Meta](definitions.md#tpsvc-master-datapwn-commeta)|
|**name**  <br>*optional*|**Example** : `"string"`|string|
|**schemas**  <br>*optional*|**Example** : `[ "string" ]`|< string > array|


<a name="tpsvc-master-datapwn-comrole"></a>
### Role

|Name|Description|Schema|
|---|---|---|
|**$ref**  <br>*required*|**Example** : `"string"`|string|
|**display**  <br>*required*|**Example** : `"string"`|string|
|**operation**  <br>*required*|**Example** : `"string"`|string|
|**primary**  <br>*required*|**Example** : `true`|boolean|
|**type**  <br>*required*|**Example** : `"[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)"`|[Type](definitions.md#tpsvc-master-datapwn-comtype)|
|**value**  <br>*required*|**Example** : `"string"`|string|


<a name="tpsvc-master-datapwn-comsearchrequest"></a>
### SearchRequest

|Name|Description|Schema|
|---|---|---|
|**attributes**  <br>*required*|**Example** : `[ "string" ]`|< string > array|
|**count**  <br>*required*|**Example** : `0`|integer(int32)|
|**excludedAttributes**  <br>*required*|**Example** : `[ "string" ]`|< string > array|
|**filter**  <br>*required*|**Example** : `"string"`|string|
|**schemas**  <br>*optional*|**Example** : `[ "string" ]`|< string > array|
|**sortBy**  <br>*required*|**Example** : `"string"`|string|
|**sortOrder**  <br>*required*|**Example** : `"string"`|string|
|**startIndex**  <br>*required*|**Example** : `0`|integer(int32)|


<a name="tpsvc-master-datapwn-comserviceproviderconfig"></a>
### ServiceProviderConfig

|Name|Description|Schema|
|---|---|---|
|**authenticationSchemas**  <br>*optional*|**Example** : `[ "[tpsvc-master-datapwn-comauthenticationschema](#tpsvc-master-datapwn-comauthenticationschema)" ]`|< [AuthenticationSchema](definitions.md#tpsvc-master-datapwn-comauthenticationschema) > array|
|**bulk**  <br>*optional*|**Example** : `"[tpsvc-master-datapwn-comsupported](#tpsvc-master-datapwn-comsupported)"`|[Supported](definitions.md#tpsvc-master-datapwn-comsupported)|
|**changePassword**  <br>*optional*|**Example** : `"[tpsvc-master-datapwn-comsupported](#tpsvc-master-datapwn-comsupported)"`|[Supported](definitions.md#tpsvc-master-datapwn-comsupported)|
|**etag**  <br>*optional*|**Example** : `"[tpsvc-master-datapwn-comsupported](#tpsvc-master-datapwn-comsupported)"`|[Supported](definitions.md#tpsvc-master-datapwn-comsupported)|
|**filter**  <br>*optional*|**Example** : `"[tpsvc-master-datapwn-comfilter](#tpsvc-master-datapwn-comfilter)"`|[Filter](definitions.md#tpsvc-master-datapwn-comfilter)|
|**patch**  <br>*optional*|**Example** : `"[tpsvc-master-datapwn-comsupported](#tpsvc-master-datapwn-comsupported)"`|[Supported](definitions.md#tpsvc-master-datapwn-comsupported)|
|**schemas**  <br>*optional*|**Example** : `[ "string" ]`|< string > array|
|**sort**  <br>*optional*|**Example** : `"[tpsvc-master-datapwn-comsupported](#tpsvc-master-datapwn-comsupported)"`|[Supported](definitions.md#tpsvc-master-datapwn-comsupported)|
|**xmlDataFormat**  <br>*optional*|**Example** : `"[tpsvc-master-datapwn-comsupported](#tpsvc-master-datapwn-comsupported)"`|[Supported](definitions.md#tpsvc-master-datapwn-comsupported)|


<a name="tpsvc-master-datapwn-comsupported"></a>
### Supported

|Name|Description|Schema|
|---|---|---|
|**supported**  <br>*optional*|**Example** : `true`|boolean|


<a name="tpsvc-master-datapwn-comuser"></a>
### User

|Name|Description|Schema|
|---|---|---|
|**active**  <br>*required*|**Example** : `true`|boolean|
|**addresses**  <br>*required*|**Example** : `[ "[tpsvc-master-datapwn-comaddress](#tpsvc-master-datapwn-comaddress)" ]`|< [Address](definitions.md#tpsvc-master-datapwn-comaddress) > array|
|**displayName**  <br>*required*|**Example** : `"string"`|string|
|**emails**  <br>*required*|**Example** : `[ "[tpsvc-master-datapwn-comemail](#tpsvc-master-datapwn-comemail)" ]`|< [Email](definitions.md#tpsvc-master-datapwn-comemail) > array|
|**entitlements**  <br>*required*|**Example** : `[ "[tpsvc-master-datapwn-comentitlement](#tpsvc-master-datapwn-comentitlement)" ]`|< [Entitlement](definitions.md#tpsvc-master-datapwn-comentitlement) > array|
|**extensions**  <br>*required*|**Example** : `{<br>  "string" : "[tpsvc-master-datapwn-comextension](#tpsvc-master-datapwn-comextension)"<br>}`|< string, [Extension](definitions.md#tpsvc-master-datapwn-comextension) > map|
|**externalId**  <br>*required*|**Example** : `"string"`|string|
|**groups**  <br>*required*|**Example** : `[ "[tpsvc-master-datapwn-comgroupref](#tpsvc-master-datapwn-comgroupref)" ]`|< [GroupRef](definitions.md#tpsvc-master-datapwn-comgroupref) > array|
|**id**  <br>*required*|**Example** : `"string"`|string|
|**ims**  <br>*required*|**Example** : `[ "[tpsvc-master-datapwn-comim](#tpsvc-master-datapwn-comim)" ]`|< [Im](definitions.md#tpsvc-master-datapwn-comim) > array|
|**locale**  <br>*required*|**Example** : `"string"`|string|
|**meta**  <br>*required*|**Example** : `"[tpsvc-master-datapwn-commeta](#tpsvc-master-datapwn-commeta)"`|[Meta](definitions.md#tpsvc-master-datapwn-commeta)|
|**name**  <br>*required*|**Example** : `"[tpsvc-master-datapwn-comname](#tpsvc-master-datapwn-comname)"`|[Name](definitions.md#tpsvc-master-datapwn-comname)|
|**nickName**  <br>*required*|**Example** : `"string"`|string|
|**password**  <br>*optional*|**Example** : `"string"`|string|
|**phoneNumbers**  <br>*required*|**Example** : `[ "[tpsvc-master-datapwn-comphonenumber](#tpsvc-master-datapwn-comphonenumber)" ]`|< [PhoneNumber](definitions.md#tpsvc-master-datapwn-comphonenumber) > array|
|**photos**  <br>*required*|**Example** : `[ "[tpsvc-master-datapwn-comphoto](#tpsvc-master-datapwn-comphoto)" ]`|< [Photo](definitions.md#tpsvc-master-datapwn-comphoto) > array|
|**preferredLanguage**  <br>*required*|**Example** : `"string"`|string|
|**profileUrl**  <br>*required*|**Example** : `"string"`|string|
|**roles**  <br>*required*|**Example** : `[ "[tpsvc-master-datapwn-comrole](#tpsvc-master-datapwn-comrole)" ]`|< [Role](definitions.md#tpsvc-master-datapwn-comrole) > array|
|**schemas**  <br>*required*|**Example** : `[ "string" ]`|< string > array|
|**timezone**  <br>*required*|**Example** : `"string"`|string|
|**title**  <br>*required*|**Example** : `"string"`|string|
|**userName**  <br>*required*|**Example** : `"string"`|string|
|**userType**  <br>*required*|**Example** : `"string"`|string|
|**x509Certificates**  <br>*required*|**Example** : `[ "[tpsvc-master-datapwn-comx509certificate](#tpsvc-master-datapwn-comx509certificate)" ]`|< [X509Certificate](definitions.md#tpsvc-master-datapwn-comx509certificate) > array|


<a name="tpsvc-master-datapwn-comx509certificate"></a>
### X509Certificate

|Name|Description|Schema|
|---|---|---|
|**$ref**  <br>*required*|**Example** : `"string"`|string|
|**display**  <br>*required*|**Example** : `"string"`|string|
|**operation**  <br>*required*|**Example** : `"string"`|string|
|**primary**  <br>*required*|**Example** : `true`|boolean|
|**type**  <br>*required*|**Example** : `"[tpsvc-master-datapwn-comtype](#tpsvc-master-datapwn-comtype)"`|[Type](definitions.md#tpsvc-master-datapwn-comtype)|
|**value**  <br>*required*|**Example** : `"string"`|string|





