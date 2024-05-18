# e-order-manager-api
e-order-manager-ui: https://github.com/Jocho14/e-order-manager-ui
e-order-manager-stipe-server: https://github.com/Jocho14/e-order-manager-stripe-server
# Deploy to PROD:
az login (currently only Mateusz can do it)
az spring app deploy --resource-group=ProjektGrupowy --service=asa-fm35od6bfz
s2y-consumption --name=demo --artifact-path=target/e-order-manager-
api-0.0.1-SNAPSHOT.jar
