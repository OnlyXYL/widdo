# 安装 babelnet-api
mvn install:install-file \
  -Dfile=lib/babelnet-api-5.2.jar \
  -DgroupId=org.babelnet \
  -DartifactId=babelnet-api \
  -Dversion=5.2 \
  -Dpackaging=jar

# 安装 lcl-jlt
mvn install:install-file \
  -Dfile=lib/lcl-jlt-2.6.4.jar \
  -DgroupId=it.uniroma1.lcl.jlt \
  -DartifactId=lcl-jlt \
  -Dversion=2.6.4 \
  -Dpackaging=jar