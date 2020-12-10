#!/bin/sh
git add -A
git commit -m "$1"
npm version patch -m "$1"
npm publish
git push origin master