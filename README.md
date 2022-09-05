## Introduction

This project is for demonstration purposes only. It demonstrates that fragments are currently not included in the generation of client code. This becomes
visible with a simple `./gradlew clean build`. The project does not contain any code whatsoever but instead wants to direct the attention towards the generated
code in _build/generated/source/graphql/main/org/example/generated_. In there are two packages, one resulting from the query file "withFragments.graphql" and
one resulting from the query file "withoutFragments.graphql". They both use the fictitious schema found in the file "schema.graphqls".

## Expectation vs Reality

To get to the point: Please notice the difference between the generated classes called "SubAttribute.kt". My expectation would be two classes with identical
content but the class generated from the query file using fragments lacks all three entities implementing the interface "SubAttribute".

### Generated Code

For your convenience, so you don't have to check out the project and generate the code, please find the code referred to in the previous section here:

**Generated Code with Fragments**
```
package org.example.generated.examplewithfragments

import com.expediagroup.graphql.client.Generated
import com.fasterxml.jackson.`annotation`.JsonSubTypes
import com.fasterxml.jackson.`annotation`.JsonTypeInfo
import com.fasterxml.jackson.`annotation`.JsonTypeInfo.As.PROPERTY
import com.fasterxml.jackson.`annotation`.JsonTypeInfo.Id.NAME
import kotlin.String

@Generated
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "__typename",
  defaultImpl = DefaultSubAttributeImplementation::class,
)
@JsonSubTypes(value = [])
public interface SubAttribute {
  public val name: String

  public val description: String

  public val tooltip: String
}

/**
 * Fallback SubAttribute implementation that will be used when unknown/unhandled type is
 * encountered.
 */
@Generated
public data class DefaultSubAttributeImplementation(
  public override val name: String,
  public override val description: String,
  public override val tooltip: String,
) : SubAttribute
```

**Generated Code without Fragments**
```
package org.example.generated.examplewithoutfragments

import com.expediagroup.graphql.client.Generated
import com.fasterxml.jackson.`annotation`.JsonSubTypes
import com.fasterxml.jackson.`annotation`.JsonTypeInfo
import com.fasterxml.jackson.`annotation`.JsonTypeInfo.As.PROPERTY
import com.fasterxml.jackson.`annotation`.JsonTypeInfo.Id.NAME
import kotlin.String

@Generated
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "__typename",
  defaultImpl = DefaultSubAttributeImplementation::class,
)
@JsonSubTypes(value = [com.fasterxml.jackson.annotation.JsonSubTypes.Type(value =
    SubEntityOne::class,
    name="SubEntityOne"),com.fasterxml.jackson.annotation.JsonSubTypes.Type(value =
    SubEntityTwo::class,
    name="SubEntityTwo"),com.fasterxml.jackson.annotation.JsonSubTypes.Type(value =
    SubEntityThree::class, name="SubEntityThree")])
public interface SubAttribute {
  public val name: String

  public val description: String

  public val tooltip: String
}

@Generated
public data class SubEntityOne(
  public override val name: String,
  public override val description: String,
  public override val tooltip: String,
  public val specialFieldOne: String?,
) : SubAttribute

@Generated
public data class SubEntityTwo(
  public override val name: String,
  public override val description: String,
  public override val tooltip: String,
  public val specialFieldTwo: String?,
) : SubAttribute

@Generated
public data class SubEntityThree(
  public override val name: String,
  public override val description: String,
  public override val tooltip: String,
  public val specialFieldThree: String?,
) : SubAttribute

/**
 * Fallback SubAttribute implementation that will be used when unknown/unhandled type is
 * encountered.
 */
@Generated
public data class DefaultSubAttributeImplementation(
  public override val name: String,
  public override val description: String,
  public override val tooltip: String,
) : SubAttribute
```
