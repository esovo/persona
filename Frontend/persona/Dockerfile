FROM node:18.14.2-alpine
# FROM node:18
WORKDIR /app_f
COPY package*.json ./
RUN npm install
COPY . .
RUN npx next build
EXPOSE 3000 
CMD [ "npm", "start" ]