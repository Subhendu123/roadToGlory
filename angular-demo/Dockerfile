FROM node:16.20.0-alpine3.18
WORKDIR /usr/local/app/angular-demo
COPY . .
RUN npm install
#RUN npm run build
RUN npm install -g @angular/cli
#EXPOSE 4002
#CMD ["ng","serve","--host", "192.168.56.1"]
CMD ["ng","serve", "--host", "0.0.0.0", "--port", "7200"]
# Use this to run: docker run -d -p 8081:7200 subhendud/angular-demo-img:7.4