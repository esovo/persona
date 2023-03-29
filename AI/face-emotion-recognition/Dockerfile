FROM node:18.14.2-alpine
# FROM node:18
WORKDIR /app_h
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
EXPOSE 3001 
CMD [ "npm", "start" ]
