# Knowledge Base untuk Lumen Robot Friends

## Convert Yago2s data to MongoDB

Download and extract Yago2s `yago2s_tsv.7z` bundle, or at least the following Yago2s datasets:

1. yagoLabels.tsv (.7z)
2. yagoFacts.tsv (.7z)
3. yagoLiteralFacts.tsv (.7z)

Copy `core/src/main/resources/META-INF/hotel.LumenSysConfig.dev.xmi` to `cli/src/main/resources/META-INF/hotel.LumenSysConfig.xmi`
(optionally configure at least MongoDB URI, which by default should be okay for development).

    mvn -pl .,core,cli -DskipTests install; mvn -DskipTests -pl cli compile dependency:copy-dependencies
    # TODO: currently Linux only
    ./yagolabelstomongo /media/ceefour/passport/databank/yago2s/yagoLabels.tsv
    ./yagofactstomongo /media/ceefour/passport/databank/yago2s/yagoFacts.tsv
    ./yagoliteralfactstomongo /media/ceefour/passport/databank/yago2s/yagoLiteralFacts.tsv

## Deploying

Hendy's self note:

First, Maven install soluvas-framework + soluvas-web + relex-id + (clean) lumen-kb

then:

    cd ~/git/lumen-kb/app/target/id.ac.itb.ee.lskk.lumen.app-0.1.0-SNAPSHOT
    rsync -vPa . ceefour@luna3:tomcat7_ceefour_prd/webapps/lumen/
    rsync --del -vPa WEB-INF/lib/ ceefour@luna3:tomcat7_ceefour_prd/webapps/lumen/WEB-INF/lib/

then you go to server and:

    touch ~/tomcat7_ceefour_prd/webapps/lumen/WEB-INF/web.xml

then you tail:

    tail -F ~/lumen_lumen_prd/_hlog/lumen.log
