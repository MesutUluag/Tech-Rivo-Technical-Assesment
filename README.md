
Create a very simple application consisting of one API endpoint [POST] /transformation.
The endpoint accepts the following example json as a body:

{
"name": "hello world",
"items": ["foo_bar", "bar", "bar", "baz"]
}

The endpoint should:
1. validate that all of the “items” are letters only and return 400 (Bad Request) status code if this
   constraint is violated
2. eliminate all duplicate “items”
3. convert all snake case “items” to camel case
   The endpoint should produce the following json:
   {
   "name": "hello world",
   "items": ["FooBar", "bar", "baz"],
   "timestamp": 1641384005
   }
   Where “timestamp” is the current timestamp.
