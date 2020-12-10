#!/bin/sh
tsc -p tsconfig.json
npx uglifyjs dist/app.js -o dist/app.js -c --source-map