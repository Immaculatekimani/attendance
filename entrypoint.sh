#!/bin/bash

# Start WildFly server in the background
/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 &

# Wait for WildFly to be ready - wait 2 minutes
sleep 120

# Execute the SQL dump to populate the database
/opt/jboss/wildfly/bin/mysql -h"$MYSQL_HOST" -uroot -p"$MYSQL_PASSWORD" "$MYSQL_DATABASE" < /opt/jboss/wildfly/bin/sqldumps.sql

# Keep the script running
tail -f /dev/null