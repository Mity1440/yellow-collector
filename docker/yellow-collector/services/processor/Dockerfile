FROM bellsoft/liberica-openjdk-centos:17
ENV PROJECT_ROOT /usr/src/app
WORKDIR $PROJECT_ROOT
COPY ./sr-processor.yaml ./config/processor.yaml
COPY ./sr-processor.jar processor.jar
ADD ./init-yellow-processor.sh ./init-yellow-processor.sh
RUN ./init-yellow-processor.sh 
CMD java -Xmx2g -Xms2g -jar processor.jar --spring.config.name=processor
