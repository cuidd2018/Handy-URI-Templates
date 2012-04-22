# Handy URI Templates

This project is an implementation of [RFC6570](http://tools.ietf.org/html/rfc6570) written in Java. 


## Basic Usage

Using the library is simple:
	
	String uri = 
		UriTemplate.create("/{foo:1}{/foo,thing*}{?test1, test2}")
				   .set("foo", "one")
				   .set("test1", "query")
				   .set("test2", "blah")
				   .set("thing", "A test")
				   .expand();


This will result in the following URI:

	"/o/one/A%20test?test1=query&test2=blah"

## Java types

The basic types that the URI template engine supports are as follows:

* arrays
* java.lang.String
* java.util.List<Object>
* java.util.Map<String, Object>	


Values that are not Strings are rendered into the URI will have its `toString()` method called. Java objects can be treated as composite objects (as name/value pairs). 

## Composite Values

The URI Template spec supports [composite values](http://tools.ietf.org/html/rfc6570#section-2.4.2) where the variable may be a list of values an associative array of (name, value) pairs. Handy URI templates always treats lists as java.util.List and name/value pairs as a java.util.Map.  

## POJOs as Composite Values

A VarExploder is invoked when an explode modifier "*" is encountered within a variable name within a URI template expression and the replacement value is a complex type, such a some type of POJO. For most use cases, the DefaultVarExploder will be sufficient. Please refer to the DefaultVarExploder JavaDoc for more details on how it works.

Should the DefaultVarExploder not be suitable for your needs, custom VarExploder implementations can be added by rolling your own implementation. A custom VarExploder implementation can be registered in one of two ways. By wrapping your object in your VarExploder:

	UriTemplate.create("/mapper{?address*}").set("address", new MyCustomVarExploder(address)).expand();
 
Or by annotating your object with the ExplodeWith annotation:

	@ExplodeWith(MyCustomVarExploder.class)
	public class Address {...
 
Note: VarExploder implementations are ONLY invoked when the explode modifier "*" is declared in the URI Template expression. If the variable declaration does not specify the explode modifier, the Object.toString() method is invoked. Usually, this is not the behavior one is looking for.

The DefaultVarExploder is a VarExploder implementation that takes in a Java object and extracts the properties for use in a URI Template. Given the following URI template expression:

	/mapper{?address*}
 
And this Java object for an address:

	Address address = new Address();
	address.setState("CA");
	address.setCity("Newport Beach");
	String result = UriTemplate.create("/mapper{?address*}").set("address", address).expand();
	
The expanded URI will be:

	/mapper?city=Newport%20Beach&state=CA
 
The DefaultVarExploder breaks down the object properties as follows:

* All properties that contain a non-null return value will be included
* Getters or fields annotated with UriTransient will NOT included in the list
* By default, the property name is used as the label in the URI. This can be overridden by placing the @VarName annotation on the field or getter method and specifying a name.
* Field level annotation take priority of getter annotations
* Property names are sorted in alphabetical order
