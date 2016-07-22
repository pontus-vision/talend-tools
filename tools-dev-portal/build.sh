#!/bin/bash
cd slate && bundle config build.nokogiri --use-system-libraries && bundle install && cd ..
echo "create array"
IFS='|' read -r -a array <<< $SERVICES_DOC
cat /slate/source/index.html.md_template > "/slate/source/index.html.md"
for index in "${!array[@]}"
do
    echo "$index ${array[index]}"
    export SERVICE_TCP_ADDR="$(echo ${array[index]} | awk -F/ '{print $3}' | awk -F: '{print $1}')" 
    export SERVICE_TCP_PORT="$(echo ${array[index]} | awk -F/ '{print $3}' | awk -F: '{print $2}')"
    [ -z "$SERVICE_TCP_PORT" ] && export SERVICE_TCP_PORT=80
    echo "Wait for the service ${SERVICE_TCP_ADDR} on port ${SERVICE_TCP_PORT} to be ready ..."
    ./wait-for-it.sh -h $SERVICE_TCP_ADDR -p $SERVICE_TCP_PORT
    curl ${array[index]}  > swagger.json 
    echo "Convert the swagger.json from ${SERVICE_TCP_ADDR} into Markdown"
    mkdir temp
    cat config.properties > /temp/config.properties
    echo "swagger2markup.anchorPrefix=${SERVICE_TCP_ADDR}" >> "/temp/config.properties"
    cat /temp/config.properties
    java -jar swagger2markup-cli-1.0.0.jar convert -c /temp/config.properties -i $(pwd)/swagger.json -d temp
    rm swagger.json
    cat temp/overview.md > slate/source/includes/_${SERVICE_TCP_ADDR}_service.md
    cat temp/paths.md >> slate/source/includes/_${SERVICE_TCP_ADDR}_service.md
    cat temp/definitions.md >> slate/source/includes/_${SERVICE_TCP_ADDR}_service.md
    cat temp/security.md >> slate/source/includes/_${SERVICE_TCP_ADDR}_service.md    
    rm -Rf temp
    echo "Update the index.html.md to include ${SERVICE_TCP_ADDR} documentation"
    echo "  - ${SERVICE_TCP_ADDR}_service" >> "/slate/source/index.html.md"
done

echo "Add search and close the index.html.md"
echo "search: true" >> "/slate/source/index.html.md"
echo "---" >> "/slate/source/index.html.md"

cd /slate/source/ 
bundle exec middleman build --clean
rm /slate/build/index.html.md_template