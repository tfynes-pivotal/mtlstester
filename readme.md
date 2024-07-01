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

This will insist request carries a client cert with that CN

Provide cert and key then use 'curltest.sh' to validate gateway filter.
![image](https://github.com/tfynes-pivotal/mtlstester/assets/6810491/297638fc-e2d7-4730-a8b1-f912c09a006b)


Gateway Configuration
![image](https://github.com/tfynes-pivotal/mtlstester/assets/6810491/993bc90b-1c24-46a3-90d8-5f7372310abf)


When a call hits the gateway without the appropriate client cert in the mTLS handshake - result is a 401
![image](https://github.com/tfynes-pivotal/mtlstester/assets/6810491/7486ec5c-5ae5-45b5-a012-fe46e3483e2a)
