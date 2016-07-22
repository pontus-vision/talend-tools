BUILDER_NAME = talend/dev_portal_builder
BUILDER_VERSION = 0.1
DOC_NAME = talend/dev_portal
DOC_VERSION = 0.1
REGISTRY = https://registry.datapwn.com

.PHONY: all build_docker_doc_builder test tag_latest release ssh

all: build

build_docker_builder:
	docker build -f BuildDockerfile -t $(BUILDER_NAME):$(BUILDER_VERSION) --rm .

builder_tag_latest: build_docker_builder
	docker tag $(BUILDER_NAME):$(BUILDER_VERSION) $(BUILDER_NAME):latest

builder_push: builder_tag_latest
	@if ! docker images $(BUILDER_NAME) | awk '{ print $$2 }' | grep -q -F $(BUILDER_VERSION); then echo "$(BUILDER_NAME) version $(BUILDER_VERSION) is not yet built. Please run 'make build'"; false; fi
	docker push $(REGISTRY)/$(BUILDER_NAME)

get_latest_builder:
	docker pull $(REGISTRY)/$(BUILDER_NAME)

build_doc: 
	docker run -v $(shell pwd)/slate:/slate $(BUILDER_NAME) ./build.sh
	docker build -t $(DOC_NAME):$(DOC_VERSION) --rm .

