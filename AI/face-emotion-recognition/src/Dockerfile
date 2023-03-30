FROM node:18.14.2-alpine
# FROM node:18
WORKDIR /app_ff
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
EXPOSE 4000
CMD [ "npm", "start" ]
