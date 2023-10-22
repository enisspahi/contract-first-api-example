# Contract First API Example

This repository contains an OpenAPI 3 specification on `contract-first` basis.
Open api spec is converted into a static html page to be served on [GitHub pages](https://enisspahi.github.io/contract-first-api-example/).

## Below are some examples of publishing single page API Docs with multiple tools:

### Publishing API Docs as a single page API Doc using [Redocly](https://redocly.com/)

1. Ensure `redocly` is installed. Instructions: https://redocly.com/docs/redoc/deployment/cli/
2. Run cli to generate single page API Doc
   `redocly build-docs api/src/main/resources/recipes-api.yaml -o docs/redoc/index.html`
3. Observe generated `docs/redoc/index.html` page

**[github-actions job](https://github.com/enisspahi/contract-first-api-example/actions/workflows/api-docs-with-redoc.yml)** can trigger the API Docs generation and publishes the generated docs to GitHub pages.

### Publishing API Docs as a single page API Doc using [Rapidoc](https://rapidocweb.com/)

1. Run the following command to copy the up-to-date Open API Spec under `docs/rapidoc/`
   `cp api/src/main/resources/recipes-api.yaml docs/rapidoc/`
2. Run and observe `docs/rapidoc/index.html` page

**[github-actions job](https://github.com/enisspahi/contract-first-api-example/actions/workflows/api-docs-with-rapidoc.yml)** can trigger the API Docs generation and publishes the generated docs to GitHub pages.

### Publishing API Docs as a single page API Doc using [Stoplight Elements](https://stoplight.io/open-source/elements)

1. Run the following command to copy the up-to-date Open API Spec under `docs/elements/`
   `cp api/src/main/resources/recipes-api.yaml docs/elements/`
2. Run and observe `docs/elements/index.html` page

**[github-actions job](https://github.com/enisspahi/contract-first-api-example/actions/workflows/api-docs-with-elements.yml)** can trigger the API Docs generation and publishes the generated docs to GitHub pages. 

### Publishing API Docs as [Hugo Dockdock Theme](https://docdock.vjeantet.fr/) based static html page 

1. Run the following command to generate markdown add add it to contents
   `widdershins --search false --language_tabs 'shell:Shell' --summary api/src/main/resources/recipes-api.yaml -o docs/hugo-docdock/content/api_reference/_index.md`
2. Run the following command to add OpenAPI Spec for embedded html
   `cp api/src/main/resources/recipes-api.yaml docs/hugo-docdock/static/`
3. Change directory
   `cd docs/hugo-docdock`
4. Start Hugo on local
   `hugo server`

