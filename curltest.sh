echo DIRECT
curl -k --cert ./cert1.pem --key ./privkey1.pem --cacert ./ca.pem https://mtlstester.homelab.fynesy.com
echo
echo FACADE
curl -k --cert ./cert1.pem --key ./privkey1.pem --cacert ./ca.pem https://mtlstesterfacade.homelab.fynesy.com
echo 
echo FACADE NO CERT
curl -k https://mtlstesterfacade.homelab.fynesy.com
echo
