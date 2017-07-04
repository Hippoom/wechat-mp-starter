#!/usr/bin/env bash
openssl aes-256-cbc -K $encrypted_95de03115781_key -iv $encrypted_95de03115781_iv -in cd/codesigning.asc.enc -out cd/signingkey.asc -d
gpg --fast-import cd/signingkey.asc
./mvnw deploy -P docs -P ossrh \
    --projects !examples/core \
    --projects !examples/oauth2 \
    --settings cd/mvn.settings.xml