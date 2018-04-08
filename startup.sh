  #!/bin/bash

echo ============================================
echo STARTING APIs TO TRANSACTIONS AND STATISTICS
echo ============================================

cd ./transactions
./mvnw spring-boot:run & echo $! > .pids.data &

sleep 10

cd ../rt-statistic
./mvnw spring-boot:run & echo $! > .pids.data &

