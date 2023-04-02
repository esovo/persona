FROM node:18.12.1-alpine
# FROM node:18
WORKDIR /app_f
COPY package*.json ./
RUN npm cache clean --force && npm install --force
COPY . .
RUN npm run build
EXPOSE 3000 
CMD [ "npm", "start" ]
