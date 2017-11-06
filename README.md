[![Build Status](https://travis-ci.org/EDumdum/iso-7064-java.svg?branch=master)](https://travis-ci.org/EDumdum/iso-7064-java)
[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/Edumdum/iso-7064-java/master/LICENSE)

# ISO-7064

Implementation of [ISO 7064](https://en.wikipedia.org/wiki/ISO_7064) used in validation of format like [IBAN](https://en.wikipedia.org/wiki/International_Bank_Account_Number), [LEI](https://en.wikipedia.org/wiki/Legal_Entity_Identifier), ..

## Installation

Install using [maven](https://maven.apache.org/).

### Local repository

Retrieve sources and install it into your maven repository using following commands:
```bash
git clone git@github.com:EDumdum/iso-7064-java.git
cd iso-7064-java
mvn clean install
```

Reference it into your pom.xml:
```maven
<dependencies>
    <dependency>
        <groupId>org.edumdum.iso</groupId>
        <artifactId>iso7064</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

### [JitPack](https://jitpack.io/)

In your pom.xml, add a new repository:
```maven
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Then reference it, still in your pom.xml:
```maven
<dependencies>
    <dependency>
        <groupId>com.github.EDumdum</groupId>
        <artifactId>iso-7064-java</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

## Usage

Into your class, add the following import:
```java
import org.edumdum.iso.Iso7064;
```

Then, you can simple call the static methods. See API section for more informations about the methods.
```
Iso7064.compute('969500KSV493XWY0PS'); // 54
Iso7064.computeWithoutCheck('7245005WBNJAFHBD0S'); // 55
```

## API

### `compute(String rawValue)` -> `int`

Check requirements.  
Letters are replaced into the string by digits, according to ISO 7064.  
Then the string is interpreted into a number.

@param rawValue
- must be not `null`
- must respect format `^[0-9A-Z]{1,}$`

@return Modulo 97 of the interpreted number

@throws IllegalArgumentException 

### `computeWithtoutCheck(String rawValue)`-> `int`

Does **NOT** check requirements.  
Letters are replaced into the string by digits, according to ISO 7064.  
Then the string is interpreted into a number.

**Note:** Use this method for faster performance if you already did the requirements checks in your code.

@param rawValue
- must be not `null`
- must respect format `^[0-9A-Z]{1,}$`

@return Modulo 97 of the interpreted number
