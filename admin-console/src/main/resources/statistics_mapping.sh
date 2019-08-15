#!/usr/bin/env bash
curl -XPUT "http://elasticsearch:9200/credit-cloud/_mapping/api-usage" -H 'Content-Type: application/json' -d'
{
  "properties": {
    "seqNum": {
      "type": "keyword"
    },
    "apiId": {
      "type": "keyword"
    },
    "apiName": {
      "type": "text"
    },
    "appId": {
      "type": "long"
    },
    "companyCode": {
      "type": "keyword"
    },
    "companyName": {
      "type": "text"
    },
    "createdAt": {
      "type": "date",
      "format": "strict_date_time||epoch_millis"
    },
    "duration": {
      "type": "long"
    },
    "resultCode": {
      "type": "keyword"
    },
    "resultMessage": {
      "type": "text"
    },
    "tenantId": {
      "type": "long"
    },
    "type": {
      "type": "long"
    }
  }
}'