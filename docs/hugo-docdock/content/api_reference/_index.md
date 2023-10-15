---
title: Recipes API v1.0
language_tabs:
  - shell: Shell
language_clients:
  - shell: ""
toc_footers: []
includes: []
search: false
highlight_theme: darkula
headingLevel: 2

---

<!-- Generator: Widdershins v4.0.1 -->

<h1 id="recipes-api">Recipes API v1.0</h1>

> Scroll down for code samples, example requests and responses. Select a language for code samples from the tabs above or the mobile navigation menu.

Recipes API - API First example

Base URLs:

* <a href="http://localhost:8080">http://localhost:8080</a>

<h1 id="recipes-api-recipes-api">Recipes API</h1>

## List all recipes

<a id="opIdfindRecipes"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/recipes \
  -H 'Accept: application/json'

```

`GET /recipes`

Shows recipes based on search criteria

<h3 id="list-all-recipes-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|title|query|string|false|none|
|ingredients|query|array[string]|false|none|
|nutritionFacts|query|array[string]|false|none|

#### Enumerated Values

|Parameter|Value|
|---|---|
|nutritionFacts|LOW_CALORIE|
|nutritionFacts|HIGH_CALORIE|
|nutritionFacts|HIGH_PROTEIN|
|nutritionFacts|CARBS|

> Example responses

> 200 Response

```json
[
  {
    "title": "Chilli sin Carne",
    "ingredients": [
      {
        "name": "Kidney beans",
        "quantity": 250,
        "unit": "grams"
      }
    ],
    "preparationTime": 30,
    "cookingTime": 15,
    "servings": 4,
    "instructions": [
      "string"
    ],
    "nutritionFacts": [
      "LOW_CALORIE"
    ]
  }
]
```

<h3 id="list-all-recipes-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|Internal Server Error|[Error](#schemaerror)|

<h3 id="list-all-recipes-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|*anonymous*|[[Recipe](#schemarecipe)]|false|none|none|
|» title|string|false|none|Title of the recipe|
|» ingredients|[[Ingredient](#schemaingredient)]|false|none|List of ingredients|
|»» name|string|false|none|Name of the ingredient|
|»» quantity|number(double)|false|none|Quantity depending on unit|
|»» unit|string|false|none|Quantifying unit of the ingredient (grams, milliliters, count, tablespoon)|
|» preparationTime|integer(int32)|false|none|Preparation time (in minutes)|
|» cookingTime|integer(int32)|false|none|Cooking time (in minutes)|
|» servings|integer(int32)|false|none|Served for persons|
|» instructions|[string]|false|none|Step by step instructions|
|» nutritionFacts|[string]|false|none|Nutrition facts|

<aside class="success">
This operation does not require authentication
</aside>

## Create a recipe

<a id="opIdcreateRecipe"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/recipes \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json'

```

`POST /recipes`

Creates a recipe and stores it

> Body parameter

```json
{
  "title": "Chilli sin Carne",
  "ingredients": [
    {
      "name": "Kidney beans",
      "quantity": 250,
      "unit": "grams"
    }
  ],
  "preparationTime": 30,
  "cookingTime": 15,
  "servings": 4,
  "instructions": [
    "string"
  ],
  "nutritionFacts": [
    "LOW_CALORIE"
  ]
}
```

<h3 id="create-a-recipe-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[Recipe](#schemarecipe)|true|none|

> Example responses

> 201 Response

```json
{
  "title": "Chilli sin Carne",
  "ingredients": [
    {
      "name": "Kidney beans",
      "quantity": 250,
      "unit": "grams"
    }
  ],
  "preparationTime": 30,
  "cookingTime": 15,
  "servings": 4,
  "instructions": [
    "string"
  ],
  "nutritionFacts": [
    "LOW_CALORIE"
  ]
}
```

<h3 id="create-a-recipe-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|[Recipe](#schemarecipe)|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|Internal Server Error|[Error](#schemaerror)|

<aside class="success">
This operation does not require authentication
</aside>

# Schemas

<h2 id="tocS_Error">Error</h2>
<!-- backwards compatibility -->
<a id="schemaerror"></a>
<a id="schema_Error"></a>
<a id="tocSerror"></a>
<a id="tocserror"></a>

```json
{
  "code": "UNEXPECTED",
  "message": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|code|string|false|none|none|
|message|string|false|none|none|

#### Enumerated Values

|Property|Value|
|---|---|
|code|UNEXPECTED|
|code|NOT_FOUND|

<h2 id="tocS_Ingredient">Ingredient</h2>
<!-- backwards compatibility -->
<a id="schemaingredient"></a>
<a id="schema_Ingredient"></a>
<a id="tocSingredient"></a>
<a id="tocsingredient"></a>

```json
{
  "name": "Kidney beans",
  "quantity": 250,
  "unit": "grams"
}

```

List of ingredients

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|name|string|false|none|Name of the ingredient|
|quantity|number(double)|false|none|Quantity depending on unit|
|unit|string|false|none|Quantifying unit of the ingredient (grams, milliliters, count, tablespoon)|

<h2 id="tocS_Recipe">Recipe</h2>
<!-- backwards compatibility -->
<a id="schemarecipe"></a>
<a id="schema_Recipe"></a>
<a id="tocSrecipe"></a>
<a id="tocsrecipe"></a>

```json
{
  "title": "Chilli sin Carne",
  "ingredients": [
    {
      "name": "Kidney beans",
      "quantity": 250,
      "unit": "grams"
    }
  ],
  "preparationTime": 30,
  "cookingTime": 15,
  "servings": 4,
  "instructions": [
    "string"
  ],
  "nutritionFacts": [
    "LOW_CALORIE"
  ]
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|title|string|false|none|Title of the recipe|
|ingredients|[[Ingredient](#schemaingredient)]|false|none|List of ingredients|
|preparationTime|integer(int32)|false|none|Preparation time (in minutes)|
|cookingTime|integer(int32)|false|none|Cooking time (in minutes)|
|servings|integer(int32)|false|none|Served for persons|
|instructions|[string]|false|none|Step by step instructions|
|nutritionFacts|[string]|false|none|Nutrition facts|

