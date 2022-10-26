FROM bellsoft/liberica-openjdk-centos:17
ENV PROJECT_ROOT /usr/src/app
WORKDIR $PROJECT_ROOT
EXPOSE 8089
COPY ./sr-receiver.yaml ./config/receiver.yaml
COPY ./sr-receiver.jar receiver.jar
ADD ./init-yellow-receiver.sh ./init-yellow-receiver.sh
RUN ./init-yellow-receiver.sh 
CMD java -Xmx2g -Xms2g -jar receiver.jar --spring.config.name=receiver
