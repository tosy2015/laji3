#!bash
git pull
ps aux | grep -i "tosylabbot" | awk '{print $2}' | xargs sudo kill
mvn clean
mvn package
#nohup java -server -XX:+UseG1GC -Xmx10m -Xms10m -jar /root/laji3/nettytest/target/nettytest-1.jar  >/dev/null 2>&1 &
#nohup java -server -XX:+UseG1GC -Xmx10m -Xms10m -jar /root/laji3/nettytest/target/nettytest-1.jar  >/dev/null 2>log &
nohup java -server -XX:+UseG1GC -Xmx10m -Xms10m -jar /root/laji3/tosylabbot/target/tosylabbot-1.0.jar
