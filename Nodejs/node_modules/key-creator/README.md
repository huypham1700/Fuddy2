# [key-creator](https://www.npmjs.com/package/key-creator) [![Build Status](https://travis-ci.org/MuhammedBeraKoc/key-creator.svg?branch=master)](https://travis-ci.org/MuhammedBeraKoc/key-creator) [![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0) [![Generic badge](https://img.shields.io/badge/version-stable-brightgreen.svg)](https://shields.io/)  [![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-blueviolet.svg)](https://GitHub.com/Naereen/StrapDown.js/graphs/commit-activity)
key-creator is a mini library to generate secure keys or passwords when developing your apps.
# Documentation
key-creator is fully documented. It is rewritten with TypeScript to make the code less error-prone and maintainable.
# Example
To install the library from NPM packages write below to the terminal:

`npm install key-creator`

You can start using library just including it like below:
```js
const key = require('key-creator');
```
Here is a example about how to generate a key for your app and use it:
```js
const key = require('key-creator');

class User {

    constructor(name) {
        this.id = key.generate();
        this.name = name;
    }

    printUser() {
        console.log(`@${this.id}[${this.name}]`);
    }
}

user = new User('Edward Burke');
user.printUser();
```

This example will create an ID field for the user and assign it using the `generate` function.


## Licence 
key-creator is **GNU Licensed**.