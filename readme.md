##mTLS testing on tPCF


java application to print out incoming request client http header


### Create SCG app facade
```
cf create-service p.gateway standard mtlstester-facade -c '{"host":"mtlstesterfacade"}
```

### Bind to service with client cert header filter enabled
```
cf bind-service mtlstester mtlstester-facade -c '{"routes": [{"path": "/**", "filters": ["ClientCertificateHeader=testclient.fynesy.com"] }] }' 
```

this will insist request carries a client cert with that CN

provide cert and key then use 'curltest.sh' to validate gateway filter.
