References:-

https://spring.io/guides/gs/accessing-vault/

vault server --dev --dev-root-token-id="00000000-0000-0000-0000-000000000000"

$ export export VAULT_TOKEN="00000000-0000-0000-0000-000000000000"
$ export VAULT_ADDR="http://127.0.0.1:8200"

vault kv put secret/map-search map_api_key=foobar
vault kv get -field=map_api_key secret/map-search
vault secrets list
vault kv get secret/map-search

GET http://localhost:8200/v1/sys/init

POST http://127.0.0.1:8200/v1/secret/data/foo

{
  "options": {
      "cas": 0
  },
  "data": {
      "foo": "bar"
    }
}

POST http://127.0.0.1:8200/v1/secret/data/mapApiKey

{
  "options": {
      "cas": 0
  },
  "data": {
      "mapApiKey": "keyvalue"
    }
}